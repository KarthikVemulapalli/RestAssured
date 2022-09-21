package RSCourse_Library;

import static io.restassured.RestAssured.given;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RSLibrary_AddBookStaticPayload {
	
	private String LibraryBaseURI = "https://rahulshettyacademy.com";
	private String AddBook_ResourceAPI = "Library/Addbook.php";
	private String AddBook_ResponseBody_BookID;
	
	public void apiAddBookStaticBody(String JsonFileName) {
		
		try {
			
			String ProjectDirectory = System.getProperty("user.dir");
			String AddBook_JsonFilePath = ProjectDirectory+"\\src\\test\\resources\\JsonFiles\\"+JsonFileName;
			
			String AddBook_StringResponse = new String(Files.readAllBytes(Paths.get(AddBook_JsonFilePath)));
			
			Response AddBook_Response = given().
					baseUri(LibraryBaseURI).
					header("Content-Type", "application/json").
					body(AddBook_StringResponse).
				when().post(AddBook_ResourceAPI);
					
				JsonPath AddBook_JsonResponse = AddBook_Response.then().assertThat().statusCode(200).extract().response().jsonPath();
				AddBook_ResponseBody_BookID = AddBook_JsonResponse.getString("ID");
				
				System.out.println("ID - "+AddBook_ResponseBody_BookID);
			
		} catch (IOException e) { 
			System.out.println("JSON File Not Found Exception");
		}	
	}
	
	public static void main(String args[]) {
		RSLibrary_AddBookStaticPayload RSLABSPobj = new RSLibrary_AddBookStaticPayload();
		RSLABSPobj.apiAddBookStaticBody("RSLibrary_AddBook.json");
	}
	
}
