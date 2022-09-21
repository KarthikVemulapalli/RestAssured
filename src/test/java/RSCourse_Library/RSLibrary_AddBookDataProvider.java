package RSCourse_Library;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import APIDataStore.RSLibrary_Payload;
import APIUtilityPack.API_ResuableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import java.lang.reflect.Array;


public class RSLibrary_AddBookDataProvider {

	private String LibraryBaseURI = "https://rahulshettyacademy.com";
	private String AddBook_ResourceAPI = "Library/Addbook.php";
	private String AddBook_ResponseBody_BookID;
	private String DeleteBook_ResourceAPI = "Library/DeleteBook.php";
	
	//Passing Mulitple Data using @DataProvider TestNG annotation
	
	@Test(priority = 0, dataProvider = "MultipleBooksData")
	public void apiAddNewBook(String getISBN, String getAisle ) {
		
		Response AddBook_Response = given().
			baseUri(LibraryBaseURI).
			header("Content-Type", "application/json").
			body(RSLibrary_Payload.dataAddBookRequestPayload(getISBN, getAisle)).
		when().post(AddBook_ResourceAPI);
			
		JsonPath AddBook_JsonResponse = AddBook_Response.then().assertThat().statusCode(200).extract().response().jsonPath();
		AddBook_ResponseBody_BookID = AddBook_JsonResponse.getString("ID");
		
		
		Response DeleteBook_Response = given().
			baseUri(LibraryBaseURI).body(RSLibrary_Payload.dataDeleteBookRequestPayload(AddBook_ResponseBody_BookID)).
		when().post(DeleteBook_ResourceAPI);
		
		JsonPath DeleteBook_JsonResponse = DeleteBook_Response.then().extract().jsonPath();
		String DeleteBook_ResponseBody_Message = DeleteBook_JsonResponse.getString("msg");
		
		if(DeleteBook_ResponseBody_Message.contains("successfully deleted")) {
			System.out.println(AddBook_ResponseBody_BookID+" "+DeleteBook_ResponseBody_Message);
		}
	}
	
	@DataProvider(name="MultipleBooksData")
	public Object[][] getMultipleBooksData() {
		return new Object[][] {{"RSCourseLibrary123", "6543210"}, {"RSCourseLibrary123", "6543211"}, {"RSCourseLibrary123", "6543212"}};
	}
	
}
