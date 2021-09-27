package com.restAssuredTest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.files.Payload;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	@Test(dataProvider="BooksData")
	void addBook(String isbn, String aisle) {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response = given().header("Content-Type", "application/json").log().all()
		.body(Payload.addBook(isbn, aisle))
		.when().post("/Library/Addbook.php")
		.then().assertThat().statusCode(200).log().all().extract().response().asString();

		JsonPath jp = new JsonPath(response);
		String id = jp.get("ID");
		System.out.println(id);
		
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData() {
		return new Object[][] {{"alglx","6418"}, {"sldajg","9685"}, {"wpoeti","8170"}};
	}

}
