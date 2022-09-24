package RSCourse_ECommerce.API;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import APIUtilityPack.API_ResuableMethods;
import io.restassured.path.json.JsonPath;

public class RSECom_GetOrders {

	public void apiGetOrders(String BaseURI, String Auth, String UserID) {
		System.out.println("************************* GetOrders API Starts *************************");
		
		String GetOrders_Resource = "api/ecom/order/get-orders-for-customer/"+UserID;
		
		JsonPath GetOrder_Response = given().spec(API_ResuableMethods.buildRequest(BaseURI)).
			header("Authorization", Auth).
		when().get(GetOrders_Resource).
		then().spec(API_ResuableMethods.buildResponse()).extract().response().jsonPath();
		
		System.out.println(GetOrder_Response.getString("message"));
		assertEquals(GetOrder_Response.getString("message"), "No Orders");
		
		System.out.println("************************* GetOrders API Ends *************************");
	}
	
}
