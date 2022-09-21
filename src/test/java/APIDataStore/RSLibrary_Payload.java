package APIDataStore;

public class RSLibrary_Payload {

	public static String dataAddBookRequestPayload(String ISBN, String Aisle) {
		String AddBook_RequestBody = "{\r\n"
				+ "    \"name\": \"Learn Appium Automation with Java\",\r\n"
				+ "    \"isbn\": \""+ISBN+"\",\r\n"
				+ "    \"aisle\": \""+Aisle+"\",\r\n"
				+ "    \"author\": \"Karthik TestBook Z\"\r\n"
				+ "}";
		
		return AddBook_RequestBody;
	}
	
	public static String dataDeleteBookRequestPayload(String BookID) {
		String DeleteBook_RequestBody = "{\r\n"
				+ "    \"ID\": \""+BookID+"\"\r\n"
				+ "}";
		
		return DeleteBook_RequestBody;
	}
	
	
}
