package com.restAssuredTest;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class TestDeleteMethodDemo {
	
	@Test
	void testOne() {
		
			when().delete("https://reqres.in/api/users/2").then().statusCode(204).log().all();
		}
		
}
