package user;

import theatre.Theatre;

public interface IUser {
	public void bookTicket(Theatre theatre);
	public void checkSeatAvailability(Theatre theatre);
}
