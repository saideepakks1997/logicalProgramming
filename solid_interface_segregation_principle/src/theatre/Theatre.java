package theatre;

public class Theatre {
	public String name;
	public int noOfSeats;
	public int snacksPrice;
	public int ticketPrice;
	public String movieName;
	public Theatre() {
		this.name = "Udayam";
		this.noOfSeats = 175;
		this.snacksPrice = 50;
		this.ticketPrice = 120;
		this.movieName = "Nanban";
	}
	public void watchMovie() {
		System.out.println(this.movieName+" has started keep your mobiles in silent mode");
	}
}
