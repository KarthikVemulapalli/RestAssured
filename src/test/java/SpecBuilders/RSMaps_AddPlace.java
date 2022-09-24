package SpecBuilders;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import APIDataStore.RSMaps_Payload;


public class RSMaps_AddPlace {

	public String APIBaseURI = "https://rahulshettyacademy.com";
	private String AddPlace_ResourceURI = "maps/api/place/add/json";
	
	public void apiAddNewPlace() {
		
		/*SpecBuilders are nothing but common datainput/validation code where we can use in multiple api. This will be useful for code reusability.
		 * For Request we use 'RequestSpecification
		 * For Response we use 'ResponseSpecification
		 * We can create methods of these with common datainput/validation ad place it in some utility class. These can be used during all API's testing.
		 */
		RequestSpecification RequestSpec = 
				new RequestSpecBuilder().setBaseUri(APIBaseURI).addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).log(LogDetail.ALL).build();		//After providing common inputs we need to build() the request Specbuilder
		
		ResponseSpecification ResponseSpec = 
				new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).log(LogDetail.ALL).build();		//After providing common validation we need to build the Response Specbuilder
		
		
		//Breaking down into parts
		RequestSpecification GivenReqSpec = given().spec(RequestSpec).
			body(RSMaps_Payload.dataAddPlaceRequestPayload("(+91) 999 999 9999"));
		
		//Using the below response we can perform more validations
		Response AddPlace_Response = 
				GivenReqSpec.when().post(AddPlace_ResourceURI).then().spec(ResponseSpec).extract().response();
		

		//We can use this response object for further validations.
		System.out.println("ResponseHeader Server - "+AddPlace_Response.getHeader("Server"));
		System.out.println("ResponseBody PlaceID - "+AddPlace_Response.jsonPath().getString("place_id"));
				
	}
	
	public static void main(String[] args) {
		RSMaps_AddPlace RSMapsAddPlaceObj = new RSMaps_AddPlace();
		RSMapsAddPlaceObj.apiAddNewPlace();
	}

}