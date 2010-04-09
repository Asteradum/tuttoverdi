package rehearsalServer;

/**
 * The aim of this class is to implement the DTO Pattern So, object from this
 * class will be transferred using RMI, from the server to the client
 * 
 * ATTENTION - ATTENTION - ATTENTION - ATTENTION
 * 
 * SOME DETAILS OF THE CLASS ARE PROVIDED BUT ONE ESSENTIAL DETAIL IS MISSING
 * TIP: PARAMETER PASSING IN RMI.
 */

public class RehearsalRMIDTO {

	private String operaHouse;
	private String operaName;
	private String date;
	private int availableSeats;

	public RehearsalRMIDTO(String h, String n, String d, int s) {
		operaHouse = h;
		operaName = n;
		date = d;
		availableSeats = s;
	}

	public RehearsalRMIDTO(RehearsalRMIDTO rmi) {
		operaHouse=rmi.getOperaHouse();
		operaName=rmi.getOperaName();
		date=rmi.getDate();
		availableSeats=rmi.getAvailableSeats();
		// TODO Auto-generated constructor stub
	}

	public String getOperaHouse() {
		return operaHouse;
	}

	public String getOperaName() {
		return operaName;
	}

	public String getDate() {
		return date;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int s) {
		availableSeats = s;
	}

	public void decAvailableSeats(int dec) {
		availableSeats -= dec;
	}
}