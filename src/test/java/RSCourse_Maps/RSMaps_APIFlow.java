package RSCourse_Maps;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.util.HashMap;
import APIDataStore.RSMaps_Payload;
import APIUtilityPack.API_ResuableMethods;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RSMaps_APIFlow {
	
	public String APIBaseURI = "https://rahulshettyacademy.com";
	private String AddPlace_ResourceURI = "maps/api/place/add/json";
	private String AddPlace_ResponseBody_PlaceID = "";
	
	private String AddPlace_RequestBody_PhoneNumber = "(+91) 999 999 9999";
	
	public void apiAddNewPlace() {
		
		String AddPlace_Response = 
				given().baseUri(APIBaseURI).queryParam("key", "qaclick123")
					.header("Content-Type", "application/json").body(RSMaps_Payload.dataAddPlaceRequestPayload(AddPlace_RequestBody_PhoneNumber)).
				when()
					.post(AddPlace_ResourceURI).
				then().extract().response().asString();  //Or you can directly get the response in Json using jsonPath() method
				//asString() gives response in Single line String. Whereas asPrettyString() also provides response in String but in decent looking format.
		
		//Parsing String Response into Json
		JsonPath AddPlace_JsonResponse = API_ResuableMethods.rawStringToJson(AddPlace_Response);
		AddPlace_ResponseBody_PlaceID = AddPlace_JsonResponse.getString("place_id");
		System.out.println(AddPlace_ResponseBody_PlaceID);
		
	}
	
	public void apiUpdatePlaceDetails() {  //Currently issue in this API
		
		Response AddPlace_ResponseObject = 
				given().baseUri(APIBaseURI).queryParam("key", "qaclick123")
					.header("Content-Type", "application/json").body(RSMaps_Payload.dataUpdatePlaceRequestPayload(AddPlace_ResponseBody_PlaceID)).
				when()
					.put(AddPlace_ResourceURI);
		
		AddPlace_ResponseObject.then().assertThat().statusCode(200);
		String AddPlace_Response = AddPlace_ResponseObject.then().extract().asPrettyString();
		
		if(AddPlace_Response.contains("Address successfully updated")) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}
	
	public void apiGetPlaceDetails() {
		
		HashMap<String, String> GetPlace_RequestHeaders = new HashMap<String, String>();
		GetPlace_RequestHeaders.put("key", "qaclick123");
		GetPlace_RequestHeaders.put("place_id", AddPlace_ResponseBody_PlaceID);
		
		JsonPath GetPlace_JsonResponse = 
				given().baseUri(APIBaseURI).
					queryParams(GetPlace_RequestHeaders).
				when().
					get(APIBaseURI).
				then().log().all().extract().response().jsonPath();
		
		String GetPlace_ResponseBody_PhoneNum = GetPlace_JsonResponse.getString("phone_number");  //API Issue in Parsing Json Format and extracting data
		
		assertEquals(AddPlace_RequestBody_PhoneNumber, GetPlace_ResponseBody_PhoneNum);
	}


	public static void main(String[] args) {
		
		RSMaps_APIFlow RSMapsAPIFlowObj = new RSMaps_APIFlow();
		RSMapsAPIFlowObj.apiAddNewPlace();
		//RSMapsAPIFlowObj.apiUpdatePlaceDetails(); Issue in API
		//RSMapsAPIFlowObj.apiGetPlaceDetails();  Issue in API to get Response
	}

}
