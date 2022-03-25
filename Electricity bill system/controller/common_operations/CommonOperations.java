package common_operations;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import bill.Bill;
import bill.Payment;
import connection.Connection;
import connection.ConnectionFiles;
import connection.TypeOfConnection;
import consumer.Consumer;
import consumer.ConsumerFiles;
import eb.ElectricityBoard;
import files.FileDetails;
import payment_options.AdminPaymentOptions;
import payment_options.ConsumerPaymentOptions;
import validator_encrypter.Encryption;

public class CommonOperations implements ICommonOperations{
	
	ConsumerFiles consumerFiles = null;
	ConnectionFiles connFiles = null;
	
	FileDetails files = FileDetails.getFiles();
	ElectricityBoard eb = null;
	
	Encryption encrypt = new Encryption();
	ConnectionFiles connFile = null;
	public CommonOperations(ElectricityBoard eb) {
		this.eb = eb;
		this.consumerFiles = new ConsumerFiles(eb);
		this.connFiles = new ConnectionFiles(eb);
		this.connFile = new ConnectionFiles(eb);
	}
	
	@Override
	public boolean checkUserNameAvailable(String user_name, boolean isConsumer) {
		if(isConsumer) {
			return eb.getConsumerMapping().containsKey(user_name);
		}
		else {
			return eb.getAdmins().containsKey(user_name);
		}
	}

	@Override
	public boolean validatePassword(String user_name,String password, String userType) {
		if(userType.equalsIgnoreCase("admin")) {
			return this.eb.getAdmins().get(user_name).validatePassword(password);
		}
		else {
			long consumerNo = this.eb.getConsumerMapping().get(user_name);
			password = encrypt.encryptPassword(password);
			return this.eb.getConsumers().get(consumerNo).validatePassword(password);
		}
	}

	@Override
	public List<Payment> getAllPedingPayments(long connNo) {
		List<Payment> pendingPayments = this.eb.getConnections().get(connNo).getPendingPayments();
		if(pendingPayments.size() == 0) {
			return null;
		}
		return pendingPayments;
	}

	@Override
	public Bill acceptPayment(int opt, long connNo,List<Payment> pendingPayments, String paymentThrough) {
		Connection connection = this.eb.getConnections().get(connNo);
		
		try {
			Payment payment = pendingPayments.get(opt-1);
			pendingPayments.remove(opt-1);
			Bill bill = new Bill(this.eb.getBillNoSeries(),payment,paymentThrough);
			connection.setBills(bill);
//			connFile.updateConnection(connection);
			consumerFiles.updateConusumer(connection);
			return bill;
		}
		catch(Exception e) {
			return null;
		}
	}

	@Override
	public String getConnectionType(long connNo) {
		TypeOfConnection connType = this.eb.getConnections().get(connNo).getConnectionType();
		return connType+"";
	}

	@Override
	public List<TypeOfConnection> getAllConnectionTypes() {
		List<TypeOfConnection> connectionTypes = new ArrayList<TypeOfConnection>();
		for(TypeOfConnection ctype: TypeOfConnection.values())
			connectionTypes.add(ctype);
		return connectionTypes;
	}

	@Override
	public boolean isValidCustomerNo(long customerNo) {
		
		return this.eb.getConsumers().containsKey(customerNo);
	}

	@Override
	public List<Bill> getBills(long serviceNo) {
		List<Bill> bills = this.eb.getConnections().get(serviceNo).getBills();
		return bills;
	}

	@Override
	public long getConsumerNoFromUserName(String user_name) {
		long consumerNo = this.eb.getConsumerMapping().get(user_name);
		return consumerNo;
	}

	@Override
	public boolean checkIfValidConnectionNo(long connNo) {
		return this.eb.getConnections().containsKey(connNo);
	}

	@Override
	public List<AdminPaymentOptions> getAdminPaymentOptions() {
		List<AdminPaymentOptions> opts = new ArrayList<>();
		for(AdminPaymentOptions pay: AdminPaymentOptions.values()) {
			opts.add(pay);
		}
		return opts;
	}

	@Override
	public List<ConsumerPaymentOptions> getConsumerPaymentOptions() {
		List<ConsumerPaymentOptions> opts = new ArrayList<>();
		for(ConsumerPaymentOptions pay: ConsumerPaymentOptions.values()) {
			opts.add(pay);
		}
		return opts;
	}

	@Override
	public void gerenateData() {
		Consumer consumer1 = new Consumer(this.eb.getConsumerNoSeries(),"Sai", "ks.sai@gmail.com", 9787898l, "trl");
//		Consumer consumer2 = new Consumer(this.eb.getConsumerNoSeries(),"Bharath", "bharath@gmail.com", 9787848l, "Chennai");
		Connection conn1 = new Connection(this.eb.getConnNoSeries(), TypeOfConnection.Domestic,"main road trl",consumer1);
//		Connection conn2 = new Connection(this.eb.getConnNoSeries(), TypeOfConnection.LtCommercial,"1st street trt",consumer2);
		consumer1.setConnection(conn1);
		File consumerFile = files.getConsumerFile();
		File connectionFile = files.getConnectionFile();
		if(!consumerFile.exists() && !connectionFile.exists() && consumerFile.length() == 0) {
			consumerFiles.addCustomer(consumer1);
			connFiles.addConnection(conn1);
		}
		
		
	}
}
