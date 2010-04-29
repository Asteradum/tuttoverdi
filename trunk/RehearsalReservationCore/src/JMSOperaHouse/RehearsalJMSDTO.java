package JMSOperaHouse;

import java.io.Serializable;


	public final class RehearsalJMSDTO implements Serializable
	{
	  public String getOperaName() {
			return operaName;
		}

		public RehearsalJMSDTO(String operaName, String date, int seats) {
		super();
		this.operaName = operaName;
		this.date = date;
		this.seats = seats;
	}

		public void setOperaName(String operaName) {
			this.operaName = operaName;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public int getSeats() {
			return seats;
		}

		public void setSeats(int seats) {
			this.seats = seats;
		}

	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	public String operaName = null;
	  public String date = null;
	  public int seats = (int)0;

	  public RehearsalJMSDTO ()
	  {
	  } // ctor

	  

	} 

