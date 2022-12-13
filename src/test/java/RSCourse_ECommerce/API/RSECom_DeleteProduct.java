package RSCourse_ECommerce.API;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import APIUtilityPack.API_ResuableMethods;
import io.restassured.path.json.JsonPath;

public class RSECom_DeleteProduct {

	public void apiDeleteProduct(String BaseURI, String Auth) {
		System.out.println("************************* DeleteProduct API Starts *************************");
		
		String Resource = "api/ecom/product/delete-product/"+ RSECom_AddProduct.AddProduct_RsB_ProductID;
		
		JsonPath DeleteProduct_Response = given().spec(API_ResuableMethods.buildRequest(BaseURI)).
				header("Authorization", Auth).
		when().delete(Resource).
		then().spec(API_ResuableMethods.buildResponse()).extract().response().jsonPath();
		
		System.out.println(DeleteProduct_Response.get("message"));
		assertEquals(DeleteProduct_Response.get("message"), "Product Deleted Successfully");
		
		System.out.println("************************* DeleteProduct API Ends *************************");
	}
	
}
