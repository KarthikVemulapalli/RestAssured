package APIUtilityPack;

import io.restassured.path.json.JsonPath;

public class API_ResuableMethods {

	public static JsonPath rawStringToJson(String StringResponse) {
		JsonPath AddPlace_JsonResponse = new JsonPath(StringResponse);
		return AddPlace_JsonResponse;
	}
	
}
