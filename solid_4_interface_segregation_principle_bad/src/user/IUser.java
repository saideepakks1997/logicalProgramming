package user;

import theatre.Theatre;

public interface IUser {
	public void bookTicket(Theatre theatre);
	public void checkSeatAvailability(Theatre theatre);
	public void bulkBooking(Theatre theatre);
	public void updateTicketPrice(Theatre theatre);
	public void updateSnacksPrice(Theatre theatre);
	public void sanitizeSeatRequest();
	public void orderSnacksToYourSeat();
}
