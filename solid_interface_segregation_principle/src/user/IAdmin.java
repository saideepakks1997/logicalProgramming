package user;

import theatre.Theatre;

public interface IAdmin extends IUser{
	public void bulkBooking(Theatre theatre);
	public void updateTicketPrice(Theatre theatre);
	public void updateSnacksPrice(Theatre theatre);
}
