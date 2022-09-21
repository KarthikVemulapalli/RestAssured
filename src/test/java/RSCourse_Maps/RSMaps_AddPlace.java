package RSCourse_Maps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import APIDataStore.RSMaps_Payload;


public class RSMaps_AddPlace {

	/*
	 * RestAssured works on three keywords - Given, When, Then. All these three are static methods.
	 * Given - We provide all input details to an API
	 * When - Submit the API by providing http Method & Resource details
	 * Then - Validate the response of API
	 */
	
	public String APIBaseURI = "https://rahulshettyacademy.com";
	private String AddPlace_ResourceURI = "maps/api/place/add/json";
	
	public void apiAddNewPlace() {
		
		/* 
		 * We can also provide BaseURI in below format. Other than passing through given().
		 * RestAssured.baseURI = APIBaseURI;
		 */
		
		System.out.println("******************** Request Details ********************");
		//We can pass all the parameters/headers in Map Object using Key, Value pair.
		//Saving the response of AddPlace API
		Response AddPlace_Response = 
		given().baseUri(APIBaseURI).log().all().								//LogAll prints all details in Console
			queryParam("key", "qaclick123").
			header("Content-Type", "application/json").
			body(RSMaps_Payload.dataAddPlaceRequestPayload("(+91) 999 999 9999")).
		when().
			post(AddPlace_ResourceURI);
		System.out.println("******************** Request Details ********************");
		
		
		System.out.println("******************** Response Details ********************");
		//Using Response Reference variable
		
		//Validate Response Status Code
		AddPlace_Response.then().log().all().
			assertThat().statusCode(200);
		
		//Validate Value in the Response Body
		AddPlace_Response.then().
			assertThat().body("scope", equalTo("APP"));   //equalTo() is from Hamcrest Package
		
		//Validate the header in Response
		AddPlace_Response.then().
			assertThat().header("Server", "Apache/2.4.41 (Ubuntu)");
		
		System.out.println("******************** Response Details ********************");
		
	}
	
	public static void main(String[] args) {
		RSMaps_AddPlace RSMapsAddPlaceObj = new RSMaps_AddPlace();
		RSMapsAddPlaceObj.apiAddNewPlace();
	}

}
