package user;

import java.util.ArrayList;
import java.util.List;

import theatre.Theatre;

public class Customer implements IUser{
	String name;
	List<String> history = new ArrayList();
	public Customer() {
		this.name = "Sai";
	}
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
		System.out.println("NO IMPLEMENTATION");
		
	}

	@Override
	public void updateTicketPrice(Theatre theatre) {
		System.out.println("NO IMPLEMENTATION");
		
	}

	@Override
	public void updateSnacksPrice(Theatre theatre) {
		System.out.println("NO IMPLEMENTATION");
		
	}

	@Override
	public void sanitizeSeatRequest() {
		System.out.println("Sanitization request has been accepted ");
		
	}

	@Override
	public void orderSnacksToYourSeat() {
		System.out.println("You will be delivring your snacks during interval time");
		
	}

}
