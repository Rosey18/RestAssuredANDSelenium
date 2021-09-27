package com.restAssuredTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GoogleMapPostMethodDemo {

	@Test
	void testOne() throws IOException {
		
		// given -- all input details
		// when -- submit he API, - resource, http method
		// then -- validate the response

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		// POST
		String response = given().queryParam("key", "qaclick123").header("Conten-Type","application/json").log().all()
		.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\Zhomart\\Desktop\\addPlace.json"))))
		.when().post("/maps/api/place/add/json")
		.then().statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);
		String placeId = js.getString("place_id");
		
		String newAddress = "USA, NY Halo Avenue 105";
		
		// PUT
		given().queryParam("key", "qaclick123").header("Conten-Type", "application/json").log().all()
		.body("{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}\r\n" + 
				"")
		.when().put("/maps/api/place/update/json")
		.then().assertThat().statusCode(200).log().all().body("msg", equalTo("Address successfully updated"));
		
		// GET
		String address = given().queryParam("key", "qaclick123").queryParam("place_id", placeId).header("Conten-Type", "application/json").log().all()
			.when().get("/maps/api/place/get/json")
			.then().statusCode(200).body("address",  equalTo(newAddress)).extract().asString();
		
		JsonPath jsPath = new JsonPath(address);
		String actualAddress = jsPath.get("address");
		Assert.assertEquals(actualAddress, newAddress);
		
	}

}
