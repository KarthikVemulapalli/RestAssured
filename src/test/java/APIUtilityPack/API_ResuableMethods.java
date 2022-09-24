package APIUtilityPack;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class API_ResuableMethods {

	public static JsonPath rawStringToJson(String StringResponse) {
		JsonPath AddPlace_JsonResponse = new JsonPath(StringResponse);
		return AddPlace_JsonResponse;
	}
	
	public static RequestSpecification buildRequest(String BaseURI) {
		RequestSpecification RSobj =
				new RequestSpecBuilder().setBaseUri(BaseURI).setContentType(ContentType.JSON).log(LogDetail.ALL).build();
		return RSobj;
	}
	
	public static ResponseSpecification buildResponse() {
		ResponseSpecification RSobj = 
				new ResponseSpecBuilder().log(LogDetail.ALL).build();
		return RSobj;
	}
	
}
