package POJO_DeSerialization;

import static io.restassured.RestAssured.*;
import java.util.HashMap;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RSGoogleOAuth2_Test {	
	
	public String GoogleAPIBaseURI = "https://www.googleapis.com/oauth2/v4/token";
	public String GetCourseDetailsBaseURI = "https://rahulshettyacademy.com/getCourse.php";
	private String AccessCode_ResponseBody_AccessToken;
	
	public void apiAccessCode (String CodeAuthentication) {
		
		HashMap<String, String> QueryParametersMap = new HashMap<String, String>();
		QueryParametersMap.put("code", CodeAuthentication);
		QueryParametersMap.put("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com");
		QueryParametersMap.put("client_secret", "erZOWM9g3UtwNRj340YYaK_W");
		QueryParametersMap.put("redirect_uri", "https://rahulshettyacademy.com/getCourse.php");
		QueryParametersMap.put("grant_type", "authorization_code");
		
		Response AccessCode_Response = given().urlEncodingEnabled(false).baseUri(GoogleAPIBaseURI).queryParams(QueryParametersMap).
		when().post();
		
		String AccessCode_StringResponse = AccessCode_Response.asString();
		JsonPath AccessCode_JsonResponse = new JsonPath(AccessCode_StringResponse);
		
		AccessCode_ResponseBody_AccessToken = AccessCode_JsonResponse.getString("access_token");
	}
	
	public void apiGetCourseDetails() {
		
		//The response body is not printed in console, refer 'ComplexJson_PojoSample.json' file in 'src/test/resources/JsonFiles' path.
		RSGoogleOAuth2_Pojo RSGOA2Pobj = 
				given().baseUri(GetCourseDetailsBaseURI).
					urlEncodingEnabled(false).
					queryParam("access_token", AccessCode_ResponseBody_AccessToken).
					expect().defaultParser(Parser.JSON).
				when().get().as(RSGoogleOAuth2_Pojo.class);    //Pass the main parent class in as() function
		/* 
		 * Above via POJO classes we are trying to get response body, we need to explicitly mention, expect().defaultParser(Parser.JSON) in given().
		 * As we have created response in according to Json response body. So we mention explicitly to send response in Json format. 
		 */
		
		//Using POJO Parent Class object, we get all values in the JSON
		//The below code retrieves the FirstCourse title inside WebAutomation
		System.out.println("WebAutomation First Course Title - "+RSGOA2Pobj.getCourses().getWebAutomation().get(0).getCourseTitle());
	}

	
	public static void main(String Args[]) {
		
		/*
		 * PreRequisites Steps should be done Manually:
		 * 1. Launch the URL 'https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php'
		 * 2. Enter Email and Password
		 * 3. after successful authentication, Get the code present in the URL
		 * 4. Pass the code below in apiAccessCode() function.
		 */
		
		RSGoogleOAuth2_Test RSGO2obj = new RSGoogleOAuth2_Test();
		RSGO2obj.apiAccessCode("4%2F0ARtbsJoZva0cQFAalC2p9sIEs4414emepoEjPvAOhnx-Qb9NWk-JLn6cuuI-nENkvaFqtA");
		RSGO2obj.apiGetCourseDetails();
	}
	
}
