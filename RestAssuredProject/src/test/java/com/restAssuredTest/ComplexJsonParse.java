package com.restAssuredTest;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.files.Payload;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	@Test
	void testOne() {

		JsonPath js = new JsonPath(Payload.coursePrice());

		// print number of course
		int count = js.getInt("courses.size()");
		System.out.println(count);

		// print Purchase amount
		int totalAmaount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmaount);

		// print the first course title
		String courseTitle = js.get("courses[2].title");
		System.out.println(courseTitle + "\n");

		// print all courses title and course prices
		for (int i=0; i<count; i++) {
			System.out.println("\n"+js.get("courses["+i+"].title"));
			System.out.println(js.get("courses["+i+"].price"));
		}

		// print of copies sold by 'PRA' course
		for (int i=0; i<count; i++) {
			String allCourseTitle = js.get("courses["+i+"].title");
			if(allCourseTitle.equalsIgnoreCase("RPA")) {
				// copies sold
				int copies = js.get("courses["+i+"].copies");
				System.out.println("\n"+"RPA courses copies amount: "+copies+"\n");
				break;
			}
		}
		
		// Verify if Sum of all Course prices matches with Purchase Amount
		int sum = 0;
		for (int i=0; i<count; i++) {
			int prices = js.getInt("courses["+i+"].price");
			int copies = js.getInt("courses["+i+"].copies");
			int amount = prices * copies;
			System.out.println(amount);
			sum += amount;
		}
		System.out.println("\n"+sum);
		
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchaseAmount);
	}

}
