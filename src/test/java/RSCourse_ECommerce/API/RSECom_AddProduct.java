package RSCourse_ECommerce.API;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.HashMap;

import APIUtilityPack.API_ResuableMethods;
import RSCourse_ECommerce.POJO.RSECom_LoginResPojo;
import io.restassured.path.json.JsonPath;

public class RSECom_AddProduct {
	
	RSECom_LoginResPojo RSELRsPobj;
	public static String AddProduct_RsB_ProductID; 
	
	public void apiAddProduct(String BaseURI, RSECom_LoginResPojo classObj) {
		System.out.println("************************* AddProduct API Starts *************************");
		
		RSELRsPobj = classObj;
		JsonPath AddProduct_Response = given().spec(API_ResuableMethods.buildRequest(BaseURI)).
			params(createRequestPayloadFormParam()).
			multiPart("productImage", new File("C:\\Users\\vkarthik1\\Desktop\\Automation\\SDET_Automation\\APITesting\\image.jpg")).
			header("Authorization", RSELRsPobj.getToken()).
			header("Content-Type", "multipart/form-data; boundary=<calculated when request is sent>").
		when().post("api/ecom/product/add-product").
		then().spec(API_ResuableMethods.buildResponse()).extract().response().jsonPath();
			
		AddProduct_RsB_ProductID = AddProduct_Response.getString("productId");
		System.out.println(AddProduct_Response.getString("message"));
		assertEquals(AddProduct_Response.getString("message"), "Product Added Successfully");
		
		System.out.println("************************* AddProduct API Ends *************************");
	}
	
	private HashMap<String, String> createRequestPayloadFormParam(){
		
		HashMap<String, String> FormParamMap = new HashMap<String, String>();
		FormParamMap.put("productName", "StromBreaker");
		FormParamMap.put("productAddedBy", RSELRsPobj.getUserId());
		FormParamMap.put("productCategory", "fashion");
		FormParamMap.put("productSubCategory", "shirts");
		FormParamMap.put("productPrice", "8666");
		FormParamMap.put("productDescription", "StromBreaker");
		FormParamMap.put("productFor", "men");
		
		return FormParamMap;
	}
	
}
