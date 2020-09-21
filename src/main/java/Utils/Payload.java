package Utils;


public class Payload {

	public static String createUser() {
		
		return "{\r\n" + 
				"    \"name\": \"Brij\",\r\n" + 
				"    \"job\": \"leader1\"\r\n" + 
				"}";
	}
	
	public static String loginSuccess() {
		
		return "{\r\n" + 
				"    \"email\": \"eve.holt@reqres.in\",\r\n" + 
				"    \"password\": \"cityslicka\"\r\n" + 
				"}";
	}
	
}
