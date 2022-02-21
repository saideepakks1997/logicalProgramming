package main;

import theatre.*;
import user.Admin;
import user.Customer;

public class Main {
	public static void main(String args[]) {
		Theatre theatre = new Theatre();
		Customer sai = new Customer();
		Admin admin = new Admin();
		
		System.out.println("Functionalities of Admin");
		System.out.println("------------------------------");
		admin.checkSeatAvailability(theatre);
		admin.bookTicket(theatre);
		admin.bulkBooking(theatre);
		admin.updateTicketPrice(theatre);
		admin.updateSnacksPrice(theatre);
		System.out.println("------------------------------");
		
		
		System.out.println("Functionalities of Customer");
		System.out.println("------------------------------");
		sai.checkSeatAvailability(theatre);
		sai.bookTicket(theatre);
		sai.sanitizeSeatRequest();
		sai.orderSnacksToYourSeat();
		System.out.println("------------------------------");
	}
}
