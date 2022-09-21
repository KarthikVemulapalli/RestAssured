package POJO_Serialization;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;
import APIUtilityPack.API_ResuableMethods;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RSMaps_AddPlace_Test {
	
	public String APIBaseURI = "https://rahulshettyacademy.com";
	private String AddPlace_ResourceURI = "maps/api/place/add/json";
	
	private AddPlace_Pojo createAddPlacePayload() {
		
		AddPlace_Pojo APPobj = new AddPlace_Pojo();
		//Passing values
		APPobj.setAccuracy(50);
		APPobj.setAddress("");
		APPobj.setLanguage("English");
		APPobj.setName("High Street Road");
		APPobj.setPhone_number("(+91) 111 111 1111");
		APPobj.setWebsite("https://www.google.com/");
		
		//to assign values for types, we need to pass list
		List<String> typesList = new ArrayList<String>();
		typesList.add("Side Street");
		typesList.add("Lane 23");
		APPobj.setTypes(typesList);
		
		//to assign values for lat & lng use Location class object
		Location Lobj = new Location();
		Lobj.setLat(-38.383494);
		Lobj.setLng(29.929211);
		APPobj.setLocation(Lobj);
		
		return APPobj;
	}
	
	public void apiAddNewPlace() {
		Response AddPlace_Response = 
				given().baseUri(APIBaseURI).								//LogAll prints all details in Console
					queryParam("key", "qaclick123").
					header("Content-Type", "application/json").
					body(createAddPlacePayload()).
				when().
					post(AddPlace_ResourceURI);
		
		//Validating Response
		String AddPlace_StringResponse = AddPlace_Response.then().
		assertThat().statusCode(200).extract().response().asString();
		
		JsonPath AddPlace_JsonResponse = API_ResuableMethods.rawStringToJson(AddPlace_StringResponse);
		String AddPlace_ResponseBody_PlaceID = AddPlace_JsonResponse.getString("place_id");
		
		System.out.println("PlaceID - "+AddPlace_ResponseBody_PlaceID);
	}
	
	public static void main(String[] Args) {
		RSMaps_AddPlace_Test RSMATobj = new RSMaps_AddPlace_Test();
		RSMATobj.apiAddNewPlace();
	}

}
