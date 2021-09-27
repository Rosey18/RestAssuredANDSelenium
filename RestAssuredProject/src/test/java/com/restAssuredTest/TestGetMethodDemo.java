package com.restAssuredTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class TestGetMethodDemo {

	@Test
	void testOne() {

		Response response = get("https://reqres.in/api/users?page=2");

		System.out.println(response.getBody());
		System.out.println(response.getStatusCode());
		System.out.println(response.asString());
		System.out.println(response.getHeader("content-type"));

		int statusCode = response.getStatusCode();

		Assert.assertEquals(statusCode, 201);
	}

	@Test
	void testTwo() {

		RequestSpecification requestSpec =  new RequestSpecBuilder()
					.setBaseUri("https://reqres.in/api")
					.setAccept(ContentType.JSON)
					.setContentType(ContentType.ANY).log(LogDetail.ALL).build();
		
		RestAssured.requestSpecification = requestSpec;

		given().spec(requestSpec).get("/users?page=2").then().statusCode(200).body("data.first_name",  hasItems("Michael", "Lindsay"));
	}

}
