package RSCourse_Maps;

import org.testng.Assert;
import APIDataStore.SampleJsonParse_Payload;
import APIUtilityPack.API_ResuableMethods;
import io.restassured.path.json.JsonPath;

public class SampleJasonParse_Validation {
	
	/*
	 * In this class we get values from Json payload by taking Json path.
	 * Refer SampleJsonParse_Payload.java from API DataStore
	 */
	
	/*
	  Solve the below Questions, using Json Payload.
	 	1.Print No of courses returned by API
		2.Print Purchase Amount
		3.Print Title of the first course
		4.Print All course titles and their respective Prices
		5.Print no of copies sold by RPA Course
		6.Verify if Sum of all Course prices matches with Purchase Amount
	 */
	
	JsonPath Payload_JsonResponse = API_ResuableMethods.rawStringToJson(SampleJsonParse_Payload.dataJsonPayload());
	int TotalCourses = Payload_JsonResponse.getInt("courses.size()");
	int TotalPurchaseAmount;

	public void apiValidatePrintCoursesCount () {
		System.out.println("Total Number of Courses - "+TotalCourses);
	}

	public void apiValidatePurchaseAmount () {
		TotalPurchaseAmount = Payload_JsonResponse.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase Amount - "+TotalPurchaseAmount);
	}

	public void apiValidateFirstCourseTitle () {
		String FirstCourseTitle = Payload_JsonResponse.getString("courses[0].title");
		System.out.println("FirstCourse Title - "+FirstCourseTitle);
	}

	public void apiValidateAllCoursesTitlePrice () {
		String CourseTitle = "";
		int CoursePrice;
		
		for (int i=0; i<TotalCourses; i++) {
			CourseTitle = Payload_JsonResponse.getString("courses["+i+"].title");
			CoursePrice = Payload_JsonResponse.getInt("courses["+i+"].price");
			
			System.out.println("Course Title - "+ CourseTitle +", Price - "+CoursePrice);
		}
	}

	public void apiValidateCourseSoldCopies(String CourseName) {
		String CourseTitle = "";
		int CourseCopies;
		
		for (int i=0; i<TotalCourses; i++) {
			CourseTitle = Payload_JsonResponse.getString("courses["+i+"].title").trim();
			CourseCopies = Payload_JsonResponse.getInt("courses["+i+"].copies");
			
			if(CourseName.trim().equalsIgnoreCase(CourseTitle)) {
				System.out.println("Course Title - "+ CourseTitle +", Copies Sold - "+CourseCopies);
				break;
			}
		}
	}
	
	public void apiValidateCoursesAmountMatch() {
		int CoursePrice;
		int CourseCopies = 1;
		int TotalCoursesPrice = 0;
		
		for (int i=0; i<TotalCourses; i++) {
			CoursePrice = Payload_JsonResponse.getInt("courses["+i+"].price");
			CourseCopies = Payload_JsonResponse.getInt("courses["+i+"].copies");
			
			TotalCoursesPrice = TotalCoursesPrice + (CoursePrice * CourseCopies);
		}
		
		Assert.assertEquals(TotalCoursesPrice, TotalPurchaseAmount);
		
		if (TotalCoursesPrice==TotalPurchaseAmount) {
			System.out.println("Purchase Amount and Courses Amount Validation: PASS");
		} else {
			System.out.println("Purchase Amount and Courses Amount Validation: FAIL");
		}
		
	}
	
	
	public static void main(String[] args) {
		SampleJasonParse_Validation JPVObj = new SampleJasonParse_Validation();
		JPVObj.apiValidatePrintCoursesCount();
		JPVObj.apiValidatePurchaseAmount();
		JPVObj.apiValidateFirstCourseTitle();
		JPVObj.apiValidateAllCoursesTitlePrice();
		JPVObj.apiValidateCourseSoldCopies("RPA");
		JPVObj.apiValidateCoursesAmountMatch();
	}

}
