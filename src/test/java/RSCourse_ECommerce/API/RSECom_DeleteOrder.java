package RSCourse_ECommerce.API;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import APIUtilityPack.API_ResuableMethods;
import io.restassured.path.json.JsonPath;

public class RSECom_DeleteOrder {

	public void apiDeleteOrder(String BaseURI, String Auth) {
		System.out.println("************************* DeleteOrder API Starts *************************");
		
		String DeleteOrder_Resource = "api/ecom/order/delete-order/"+RSECom_CreateOrder.CreateOrder_RsB_OrderID;
		
		JsonPath DeleteOrder_Response = given().spec(API_ResuableMethods.buildRequest(BaseURI)).
			header("Authorization", Auth).
		when().delete(DeleteOrder_Resource).
		then().spec(API_ResuableMethods.buildResponse()).extract().response().jsonPath();
		
		assertEquals(DeleteOrder_Response.getString("message"), "Orders Deleted Successfully");
		System.out.println(DeleteOrder_Response.getString("message"));
		
		System.out.println("************************* DeleteOrder API Ends *************************");
	}
		
}
