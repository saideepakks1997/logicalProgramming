package user;

import theatre.Theatre;

public class Admin implements IUser{
	String adminName;

	@Override
	public void bookTicket(Theatre theatre) {
		System.out.println("Ticket has been booked in theatre "
				+ ""+theatre.name+" for movie "+theatre.movieName+""
				+ "Cost of ticket is "+theatre.ticketPrice);
		theatre.noOfSeats -= 1;
		
	}

	@Override
	public void checkSeatAvailability(Theatre theatre) {
		System.out.println("No of seats availabe now is "+theatre.noOfSeats);		
	}

	@Override
	public void bulkBooking(Theatre theatre) {
		System.out.println("Bulk booking has been done");
		
	}

	@Override
	public void updateTicketPrice(Theatre theatre) {
		System.out.println("Ticket price has been updated");
		theatre.ticketPrice += 50;		
	}

	@Override
	public void updateSnacksPrice(Theatre theatre) {
		System.out.println("snacks  price has been updated");
		theatre.snacksPrice += 20;
		
	}

	@Override
	public void sanitizeSeatRequest() {
		System.out.println("NO IMPLEMENTATION");
		
	}

	@Override
	public void orderSnacksToYourSeat() {
		System.out.println("NO IMPLEMENTATION");
		
	}

}
