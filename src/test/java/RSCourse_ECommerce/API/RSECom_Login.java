package RSCourse_ECommerce.API;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import APIUtilityPack.API_ResuableMethods;
import RSCourse_ECommerce.POJO.RSECom_LoginReqPojo;
import RSCourse_ECommerce.POJO.RSECom_LoginResPojo;
import io.restassured.response.Response;

public class RSECom_Login {
		
	public RSECom_LoginResPojo apiLogin(String BaseURI) {
		
		System.out.println("************************* Login API Starts *************************");
		
		Response Login_Response = given().header("Content-Type", "application/json").
			spec(API_ResuableMethods.buildRequest(BaseURI)).
			body(createLoginReqPayload()).
		when().post("api/ecom/auth/login");
		
		Login_Response.then().assertThat().statusCode(200);
		
		RSECom_LoginResPojo RSECOMLRsPobj = 
				Login_Response.then().spec(API_ResuableMethods.buildResponse()).extract().as(RSECom_LoginResPojo.class);
		
		System.out.println(RSECOMLRsPobj.getMessage());
		assertEquals(RSECOMLRsPobj.getMessage(), "Login Successfully");
		
		System.out.println("************************* Login API Ends *************************");
		return RSECOMLRsPobj;
	}
		
	
	private RSECom_LoginReqPojo createLoginReqPayload() {
		
		RSECom_LoginReqPojo RSEComLobj = new RSECom_LoginReqPojo();
		RSEComLobj.setUserEmail("vemulapalli.karthik@gmail.com");
		RSEComLobj.setUserPassword("Karthik@1234");
		
		return RSEComLobj;
	}
	
}
