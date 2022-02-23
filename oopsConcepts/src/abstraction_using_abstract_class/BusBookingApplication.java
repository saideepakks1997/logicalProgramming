package abstraction_using_abstract_class;

import java.util.Scanner;

public abstract class BusBookingApplication {
	public int totalSeats;
	public double fare ;
	public int availableSeats;
	static Scanner sc =new Scanner(System.in);
	public abstract void displaySeatArrangment();
	
	public void bookTicket(BusBookingApplication bus) {
		System.out.println("Enter total number of seats");
		int noOfSeats = sc.nextInt();
		if(noOfSeats > bus.availableSeats) {
			System.out.println("Enter correct option");
			return;
		}
		else {
			bus.availableSeats -= noOfSeats;
		}
			
		System.out.println("Ticket has been booked successfully");
		System.out.println("Total payable amount is "+bus.fare*noOfSeats);
	}
	public  void displayFareAndSeatAvailability(BusBookingApplication bus) {
		System.out.println("Total number of seats available "+bus.availableSeats);
		System.out.println("Fare for this bus is "+bus.fare);
	}
}
