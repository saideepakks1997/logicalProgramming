package comparator;
import java.time.LocalDateTime;
import java.util.Comparator;

import eb.RequestObj;

//this show the consumer the last updated notifications (i.e) from request object 
public class RequestObjectDescendingComp implements Comparator<RequestObj>{

	
	@Override
	public int compare(RequestObj obj1, RequestObj obj2) {
		LocalDateTime updatedTime1 = obj1.getLastUpdatedTime();
		LocalDateTime updatedTime2 = obj2.getLastUpdatedTime();
		return updatedTime2.compareTo(updatedTime1);
	}
}

