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
	public List<Connection> getConsumerConnection(long consumerNo) {
		List<Connection> connections = this.eb.getConsumers().get(consumerNo).getConnections();
		return connections;
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
	public List<String> getNotification(long consumerNo) {
		Consumer consumer = this.eb.getConsumers().get(consumerNo);
		//removes null
		List<String> notifs = consumer.getNotifis().values().stream().filter(x -> x!=null).collect(Collectors.toList());
		List<String> notifications = new ArrayList<>(notifs);
		return notifications;
	}
	
}
