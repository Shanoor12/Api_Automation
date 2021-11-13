package HalloClub;



public class Payload {
	
	public static String Registeration() 
	{
		
		return "{\r\n"
				+ "    \"mobile\": 7848998820,\r\n"
				+ "    \"countryCode\": 91\r\n"
				+ "}";
		
	}
	
	public static String Verify_Otp() 
	{
		return"{\r\n"
				+ "    \"countryCode\": 91,\r\n"
				+ "    \"mobile\": 7848998820,\r\n"
				+ "    \"otp\": 1234\r\n"
				+ "}";
		
	}
	
	

}
