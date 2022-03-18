package admin_operations;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import bill.Payment;
import bill_calculation_operations.CommercialBillCalculation;
import bill_calculation_operations.CottageAndTinyIndustriesCalculation;
import bill_calculation_operations.DomesticBillCalculations;
import bill_calculation_operations.GovnAidedPlacesCalculation;
import bill_calculation_operations.PowerLoomsCalculation;
import bill_calculation_operations.PrivateHostpitalInstitutionCalculation;
import bill_calculation_operations.PublicLightTownCalculation;
import bill_calculation_operations.PublicLightsVillageAndIndustrialmetroCalculation;
import bill_calculation_operations.PublicWorkshopCalculation;
import bill_calculation_operations.TemporarySupplyCalculation;
import bill_calculation_operations.CalculateBill;
import connection.Connection;
import connection.TypeOfConnection;
import consumer.Consumer;
import eb.ChangeOfConnectionRequest;
import eb.ElectricityBoard;
import eb.NewConnectionRequest;

public class AdminOperations implements IAdminOperations{
	ElectricityBoard eb = null;
	CalculateBill calculateBill = null;
	public AdminOperations(ElectricityBoard eb) {
		this.eb = eb;
	}
	
	@Override
	public boolean setReading(long connNo,long newReadings) {
		Connection conn = this.eb.getConnections().get(connNo);
		long pastReadings = conn.getCurrentUnit();
			if(pastReadings > newReadings)
				return false;
			else {
				double readings = newReadings - pastReadings;
				conn.setCurrentUnit(newReadings);
				setPendingPayment(readings, conn);
				return true;
			}
		}
	
	private void setPendingPayment(double readings, Connection conn) {
		calculateBill = getBillCaulationObj(conn);
		double payableAmount = calculateBill.calculteBillAmount(readings);
		Payment payment = new Payment(payableAmount, readings); 
		conn.setPendingPayments(payment);
	}

	private CalculateBill getBillCaulationObj(Connection conn) {
		CalculateBill billCalculate = null;
		switch (conn.getConnectionType()) {
		case Domestic: billCalculate = new DomesticBillCalculations();
			break;
		case LtCommercial: billCalculate = new CommercialBillCalculation();
			break;
		case PublicWorkshop: billCalculate = new PublicWorkshopCalculation();
			break;
		case CottageAndTinyIndustries: billCalculate = new CottageAndTinyIndustriesCalculation();
			break;
		case PowerLooms: billCalculate = new PowerLoomsCalculation();
			break;
		case PublicLightsVillageAndIndustrialmetro: billCalculate = new PublicLightsVillageAndIndustrialmetroCalculation();
			break;
		case TemporarySupply: billCalculate = new TemporarySupplyCalculation();
			break;
		case PublicLightTown: billCalculate = new PublicLightTownCalculation();
			break;
		case GovnAidedPlaces: billCalculate = new GovnAidedPlacesCalculation();
			break;
		case PrivateHostpitalInstitution: billCalculate = new PrivateHostpitalInstitutionCalculation();
			break;
		}
		return billCalculate;
	}

	@Override
	public Map<Long, List<Payment>> getNonPayers() {
		Map<Long,List<Payment>> nonPayers = new TreeMap<Long,List<Payment>>();
		for(Long connNo: this.eb.getConnections().keySet()) {
			Connection conn = this.eb.getConnections().get(connNo);
			List<Payment> list = new ArrayList<Payment>();
			for(Payment payment: conn.getPendingPayments()) {
				list.add(payment);
			}
			if(list.size() > 0) {
				nonPayers.put(connNo, list);
			}
		}
		return nonPayers;
	}

	@Override
	public String changeConnectionType(TypeOfConnection connectionType, long connNo) {
		TypeOfConnection prevConnType = this.eb.getConnections().get(connNo).getConnectionType();
		if(prevConnType == connectionType) {
			return "The connection type is already "+connectionType;
		}
		else {
			this.eb.getConnections().get(connNo).setConnectionType(connectionType);
			return "Connection type "+connectionType+" has been updated for service no "+connNo;
		}
		
		
	}

	@Override
	public String createConnectionForNewConsumer(String name,String email,long phoNo,String address,String connAddress, TypeOfConnection connType) {
		Consumer consumer = new Consumer(this.eb.getConsumerNoSeries(),name, email, phoNo, address);
		
		long serviceNo = this.eb.getConnNoSeries();
		Connection conn = new Connection(serviceNo, connType,connAddress,consumer);

		this.eb.setConnections(conn);
		this.eb.setConsumers(consumer);
		this.eb.getConsumers().get(consumer.getConsumerNO()).setConnection(conn);
		
		return "Connection has been created successfully service number is "+serviceNo+""
				+ " and consumer no is "+consumer.getConsumerNO();
	}

	@Override
	public Connection createConnectionForExistingConsumer(long customerNo,String connAddress, TypeOfConnection connType) {
		long serviceNo = this.eb.getConnNoSeries();
		Consumer consumer = this.eb.getConsumers().get(customerNo);
		Connection conn = new Connection(serviceNo, connType, connAddress, consumer);
		
		this.eb.setConnections(conn);
		this.eb.getConsumers().get(customerNo).setConnection(conn);
		
		return conn;
	}

	@Override
	public List<NewConnectionRequest> getNewConnectionRequests() {
		List<NewConnectionRequest> requests = this.eb.getNewConnRequests();
		return requests;
	}

	@Override
	public boolean addNotification(long consumerNo, int reqIndex, String status,String reqType) {
		this.eb.getConsumers().get(consumerNo).setNotifications(status);
		
//		if(reqType.equalsIgnoreCase("newConnection"))
//			this.eb.getNewConnRequests().remove(reqIndex);
//		else if(reqType.equalsIgnoreCase("changetype"))
//			this.eb.getConnChangeRequests().remove(reqIndex);
		
		return true;
	}

	@Override
	public List<ChangeOfConnectionRequest> getConnectionChangeRequests() {
		List<ChangeOfConnectionRequest> req = this.eb.getConnChangeRequests();
		return req;
	}

	@Override
	public String getUserNameFromConsumerNo(int consumerNo) {
		String user_name = this.eb.getConsumers().get(consumerNo).getUser_name();
		System.out.println("user_name in admine operations "+this.eb.getConsumers().get(consumerNo));
		return user_name;
	}

	@Override
	public Map<Long, Consumer> getAllConsumers() {
		return this.eb.getConsumers();
	}

	@Override
	public void updateStatus(NewConnectionRequest req, int index) {
//		NewConnectionRequest req = this.eb.getNewConnRequests().get(index);
		req.setStatusNo();
		if(req.getStatusNo() == 3) {
			this.eb.getNewConnRequests().remove(index);
		}
		
	}
	
}
