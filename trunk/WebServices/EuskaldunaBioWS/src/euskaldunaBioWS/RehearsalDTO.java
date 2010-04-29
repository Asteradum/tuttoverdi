package euskaldunaBioWS;

public class RehearsalDTO {
	
	  private String operaName = null;
	  private String date = null;
	  private int seats = (int)0;

	  public RehearsalDTO ()
	  {
	  }

	  public RehearsalDTO (String _operaName, String _date, int _seats)
	  {
	    setOperaName(_operaName);
	    setDate(_date);
	    setSeats(_seats);
	  }

	public void setOperaName(String operaName) {
		this.operaName = operaName;
	}

	public String getOperaName() {
		return operaName;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public int getSeats() {
		return seats;
	}
}
