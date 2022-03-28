package consumer_operations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import connection.Connection;
import connection.TypeOfConnection;
import consumer.Consumer;
import eb.ElectricityBoard;
import eb.RequestObj;
import eb.SerializedEbObjFromFile;
import validator_encrypter.Encryption;

public class ConsumerOperations implements IConsumerOperations{
	ElectricityBoard eb = null;
	Encryption encrypt = new Encryption();
	SerializedEbObjFromFile serialize = SerializedEbObjFromFile.getObj();
	public ConsumerOperations(ElectricityBoard eb) {
		this.eb = eb;
	}
	@Override
	public boolean registerUser(long consumerNo, String user_name, String password) {
		password = encrypt.encryptPassword(password);
		Consumer consumer = this.eb.getConsumers().get(consumerNo);
		consumer.setUser_name(user_name);
		consumer.setPassword(password);
		
		this.eb.setConsumerMapping(consumer);
		
		serialize.updateEbFile(this.eb);
		return true;
	}

	@Override
	public List<Connection> getConsumerConnection(long consumerNo) {
		Collection<Connection> connections = this.eb.getConsumers().get(consumerNo).getConnections().values(); 
		List<Connection> conns = new ArrayList<>(connections);	
		return conns;
	}
	
	
	@Override
	public long createConsumer(String name, String email, long phoNo, String address) {
		long consumerNo = this.eb.getConsumerNoSeries();
		Consumer consumer = new Consumer(consumerNo, name, email, phoNo, address);
		this.eb.setConsumers(consumer);
		serialize.updateEbFile(this.eb);
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
		serialize.updateEbFile(this.eb);
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
		RequestObj request = new RequestObj(consumerNo, serviceNo, connType, requestNo);
		consumer.setNotifis(request, null);
		this.eb.setRequests(request);
		serialize.updateEbFile(this.eb);
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
	@Override
	public  String getUserNameIfAlreadyRegiestered(Long consumerNo) {
		String user_name = this.eb.getConsumers().get(consumerNo).getUser_name();
		return user_name ;
	}
	
}
