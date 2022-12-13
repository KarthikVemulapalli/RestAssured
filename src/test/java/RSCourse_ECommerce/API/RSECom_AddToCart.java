package RSCourse_ECommerce.API;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import APIUtilityPack.API_ResuableMethods;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

public class RSECom_AddToCart {

	public void apiAddToCart(String BaseURI, String Auth, String UserID) {
		try {
			System.out.println("************************* AddToCart API Starts *************************");
			
			String ProjectDirectory = System.getProperty("user.dir");
			String AddBook_JsonFilePath = ProjectDirectory+"\\src\\test\\resources\\JsonFiles\\RSECom_AddToCart.json";
			String AddBook_StringResponse = new String(Files.readAllBytes(Paths.get(AddBook_JsonFilePath)));
			
			AddBook_StringResponse = AddBook_StringResponse.replaceAll("&AccountLogin_RsB_UserID&", UserID);
			AddBook_StringResponse = AddBook_StringResponse.replaceAll("&LoginCred_Username&", "vemulapalli.karthik@gmail.com");
			AddBook_StringResponse = AddBook_StringResponse.replaceAll("&AddProduct_RsB_ProductID&", RSECom_AddProduct.AddProduct_RsB_ProductID);
			
			JsonPath AddToCart_Response = given().spec(API_ResuableMethods.buildRequest(BaseURI)).
				header("Authorization", Auth).
				body(AddBook_StringResponse).
			when().post("api/ecom/user/add-to-cart").
			then().spec(API_ResuableMethods.buildResponse()).extract().response().jsonPath();
			
			System.out.println(AddToCart_Response.getString("message"));
			assertEquals(AddToCart_Response.getString("message"), "Product Added To Cart");
			
			System.out.println("************************* AddToCart API Ends *************************");
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
