package files;

import consumer.Consumer;
import eb.ElectricityBoard;
import eb.RequestObj;

public interface IRequestObjFile {
	public void createRequest(RequestObj request);
	public void updateRequest(RequestObj request);
	public void loadRequests(ElectricityBoard eb, String[] requests, Consumer consumer);
}
