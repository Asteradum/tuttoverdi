package euskaldunaBioWS;

public class prueba {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EuskaldunaBioWS e = new EuskaldunaBioWS();
		
		RehearsalDTO[] array = e.getRehearsals();
		
		for (int i = 0; array.length<i; i++){
			System.out.println(array[i].getOperaName() + "    " + array[i].getDate()+ "    " + array[i].getSeats());
		}

	}

}
