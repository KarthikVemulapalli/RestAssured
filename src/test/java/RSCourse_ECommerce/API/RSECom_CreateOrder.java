package RSCourse_ECommerce.API;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import APIUtilityPack.API_ResuableMethods;
import RSCourse_ECommerce.POJO.RSECom_CreateOrderReqPojo;
import RSCourse_ECommerce.POJO.RSECom_CreateOrderReq_Orders;
import io.restassured.path.json.JsonPath;

public class RSECom_CreateOrder {
	
	public static String CreateOrder_RsB_OrderID;

	public void apiCreateOrder(String BaseURI, String Auth) {
		System.out.println("************************* CreateOrder API Starts *************************"); 
				
		JsonPath CreateOrder_Response = 
				given().spec(API_ResuableMethods.buildRequest(BaseURI)).
					header("Authorization", Auth).body(createCreateOrderReqPayload()).
				when().post("api/ecom/order/create-order").
				then().extract().response().jsonPath();
		
		System.out.println(CreateOrder_Response.getString("message"));
		assertEquals(CreateOrder_Response.getString("message"), "Order Placed Successfully");
		
		CreateOrder_RsB_OrderID = CreateOrder_Response.getString("orders[0]");
		
		System.out.println("************************* CreateOrder API Ends *************************");
	}
	
	private RSECom_CreateOrderReqPojo createCreateOrderReqPayload() {
		
		RSECom_CreateOrderReq_Orders Ordersobj = new RSECom_CreateOrderReq_Orders();
		Ordersobj.setCountry("India");
		Ordersobj.setProductOrderedId(RSECom_AddProduct.AddProduct_RsB_ProductID);
		
		RSECom_CreateOrderReqPojo CreateOrderobj = new RSECom_CreateOrderReqPojo();
		List<RSECom_CreateOrderReq_Orders> CreateOrderlist = new ArrayList<RSECom_CreateOrderReq_Orders>();
		CreateOrderlist.add(Ordersobj);
		
		CreateOrderobj.setOrders(CreateOrderlist);
		
		return CreateOrderobj;
	}
	
}
