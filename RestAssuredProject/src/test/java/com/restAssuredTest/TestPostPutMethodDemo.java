package com.restAssuredTest;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.util.JSONPObject;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class TestPostPutMethodDemo {

	@SuppressWarnings("unchecked")
	@Test
	void testOne() {

		/*
		 * Map<String, Object> map = new HashMap<String, Object>();
		 * 
		 * map.put("name", "Zhomart"); map.put("job", "tester");
		 */

		RequestSpecification requestSpec = new RequestSpecBuilder().setBaseUri("https://reqres.in/api").log(LogDetail.ALL).build();
		RestAssured.requestSpecification = requestSpec;

		ResponseSpecification responseSpec = new ResponseSpecBuilder().expectStatusCode(201).log(LogDetail.BODY).build();
		RestAssured.responseSpecification = responseSpec;

		JSONObject request = new JSONObject();
		request.put("name", "Zhomart");
		request.put("job", "tester");
		// System.out.println(request);

		given().spec(requestSpec).body(request.toJSONString()).when().post("/users").then().spec(responseSpec);
	}

	@SuppressWarnings("unchecked")
	@Test
	void testTwo() {

		JSONObject request = new JSONObject();
		request.put("name", "Zhomart");
		request.put("job", "tester");
		System.out.println(request);

		given().body(request.toJSONString()).contentType(ContentType.JSON).accept(ContentType.JSON).when()
				.put("https://reqres.in/api/users/2").then().statusCode(200).log().all();
	}

}
