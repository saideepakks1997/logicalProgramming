package consumer_operations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import connection.Connection;
import connection.TypeOfConnection;
import consumer.Consumer;
import eb.ElectricityBoard;
import eb.RequestObj;
import validator_encrypter.Encryption;

public class ConsumerOperations implements IConsumerOperations{
	ElectricityBoard eb = null;
	Encryption encrypt = new Encryption();
	public ConsumerOperations(ElectricityBoard eb) {
		this.eb = eb;
	}
	@Override
	public boolean registerUser(long consumerNo, String user_name, String password) {
		password = encrypt.encryptPassword(password);
		Consumer consumer = this.eb.getConsumers().get(consumerNo);
		consumer.setUser_name(user_name);
		consumer.setPassword(password);
		
		boolean isDone = consumer.setUserNamePassword(user_name, password);
		
		this.eb.setConsumerMapping(consumer);
		return isDone;
	}
	@Override
	public boolean checkConsumerCredentials(String user_name, String password) {
		Long consumerNo = this.eb.getConsumerMapping().get(user_name);
		Consumer consumer = this.eb.getConsumers().get(consumerNo);
		password = encrypt.encryptPassword(password);
//		System.out.println("Login :- "+password);
		return consumer.validatePassword(password);
		
	}
	@Override
	public List<Connection> getConsumerConnection(long consumerNo) {
		List<Connection> connections = this.eb.getConsumers().get(consumerNo).getConnections();
		return connections;
	}
//	@Override
//	public NewConnectionRequest newConnectionRequest(long consumerNo, String address, TypeOfConnection connType) {
//		long requestNo = this.eb.getRequestNoSeries();
//		NewConnectionRequest request = new NewConnectionRequest(consumerNo, address, connType,requestNo);
//		this.eb.setNewConnRequests(request);
//		return request;
//	}
//	@Override
//	public ChangeOfConnectionRequest changeOfConnectionRequest(long consumerNo,long serviceNo, TypeOfConnection connType) {
//		if(this.eb.getConnections().get(serviceNo).getConnectionType() == connType) {
//			return null;
////					"Connection is already of type "+connType;
//		}
//		long requestNo = this.eb.getRequestNoSeries();
//		ChangeOfConnectionRequest request = new ChangeOfConnectionRequest(consumerNo,serviceNo, connType, requestNo);
//		this.eb.setConnChangeRequests(request);
//		return request;
////				"Change of connection for "+serviceNo+" has been requested successfully and request number is "+requestNo;
//	}
	@Override
	public List<String> getNotification(long  consumerNo) {
		List<String> notifications = this.eb.getConsumers().get(consumerNo).getNotifications();
		return notifications;
	}
	
	@Override
	public long createConsumer(String name, String email, long phoNo, String address) {
		long consumerNo = this.eb.getConsumerNoSeries();
		Consumer consumer = new Consumer(consumerNo, name, email, phoNo, address);
		this.eb.setConsumers(consumer);
		return consumer.getConsumerNO();
	}
	
	//new methods
	@Override
	public RequestObj newConnectionReq(long consumerNo, String address, TypeOfConnection connType) {
		Consumer consumer = this.eb.getConsumers().get(consumerNo);
		long requestNo = this.eb.getRequestNoSeries();
		RequestObj request = new RequestObj(consumerNo, address, connType, requestNo);
		System.out.println("New connection reqs "+request);
		consumer.setNotifis(request, null);
		this.eb.setRequests(request);
		return request;
	}
	@Override
	public RequestObj changeOfConnectionReq(long consumerNo, long serviceNo, TypeOfConnection connType) {
		Consumer consumer = this.eb.getConsumers().get(consumerNo);
		TypeOfConnection currType = this.eb.getConnections().get(serviceNo).getConnectionType();
		if(currType == connType) {
			return null;
		}
		long requestNo = this.eb.getRequestNoSeries();
//		long consumerNo,long serviceNo, TypeOfConnection connType,long requestNo
		RequestObj request = new RequestObj(consumerNo, serviceNo, connType, requestNo);
//		ChangeOfConnectionRequest request = new ChangeOfConnectionRequest(consumerNo,serviceNo, connType, requestNo);
		System.out.println(request);
		consumer.setNotifis(request, null);
		this.eb.setRequests(request);
		return request;
	}
	@Override
	public List<String> getNotification2(long consumerNo) {
		Consumer consumer = this.eb.getConsumers().get(consumerNo);
		//removes null
		List<String> notifs = consumer.getNotifis().values().stream().filter(x -> x!=null).collect(Collectors.toList());
		List<String> notifications = new ArrayList<>(notifs);
		return notifications;
	}
	
}
