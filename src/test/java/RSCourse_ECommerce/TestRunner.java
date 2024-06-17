package RSCourse_ECommerce;

import org.testng.annotations.*;
import RSCourse_ECommerce.API.RSECom_AddProduct;
import RSCourse_ECommerce.API.RSECom_AddToCart;
import RSCourse_ECommerce.API.RSECom_CreateOrder;
import RSCourse_ECommerce.API.RSECom_DeleteOrder;
import RSCourse_ECommerce.API.RSECom_DeleteProduct;
import RSCourse_ECommerce.API.RSECom_GetOrders;
import RSCourse_ECommerce.API.RSECom_Login;
import RSCourse_ECommerce.POJO.RSECom_LoginResPojo;

public class TestRunner {
	
	RSECom_Login loginObj = new RSECom_Login();
	RSECom_LoginResPojo loginResPObj = new RSECom_LoginResPojo();
	
	RSECom_AddProduct AddProductObj = new RSECom_AddProduct();
	RSECom_DeleteProduct DeleteProductOjb = new RSECom_DeleteProduct();
	RSECom_AddToCart AddToCartObj = new RSECom_AddToCart();
	
	RSECom_CreateOrder CreateOrderobj = new RSECom_CreateOrder();
	RSECom_GetOrders GetOrdersobj = new RSECom_GetOrders();
	RSECom_DeleteOrder DeleteOrderobj = new RSECom_DeleteOrder();
	
	@BeforeSuite
	public void BeforeSuite() {
		System.out.println("RS ECommerce - Welcomes You");
		System.out.println();
	}
	
	@AfterSuite
	public void AfterSuite() {
		System.out.println();
		System.out.println("RS ECommerce - Thank You For Shopping");
	}
	
	@BeforeTest
	public void BeforeTest() {
		System.out.println("RS ECommerce - Great Deals On Shopping");
	}
	
	@AfterTest
	public void AfterTest() {
		System.out.println("RS ECommerce - You Are A Valuable Customer");
	}	
	
	@Test
	@Parameters({"ECommerceBaseURL"})
	public void ExecutionFlow(@Optional("https://rahulshettyacademy.com/") String BaseURI) {	
		//Application Login
		loginResPObj = loginObj.apiLogin(BaseURI);
		//AddNewProduct in Customer Account
		AddProductObj.apiAddProduct(BaseURI, loginResPObj);
		//AddToCart
		AddToCartObj.apiAddToCart(BaseURI, loginResPObj.getToken(), loginResPObj.getUserId());
		//Create order
		CreateOrderobj.apiCreateOrder(BaseURI, loginResPObj.getToken());
		//Delete Order
		DeleteOrderobj.apiDeleteOrder(BaseURI, loginResPObj.getToken());
		//Validate GetOrders
		GetOrdersobj.apiGetOrders(BaseURI, loginResPObj.getToken(), loginResPObj.getUserId());
		//DeleteProduct from Customer Account
		DeleteProductOjb.apiDeleteProduct(BaseURI, loginResPObj.getToken());
	}
	
}