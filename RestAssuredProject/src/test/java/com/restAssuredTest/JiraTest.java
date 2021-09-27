package com.restAssuredTest;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class JiraTest {
	
	@Test
	public void testJira() {
		
		RestAssured.baseURI = "http://localhost:8080/";
		
		SessionFilter sf = new SessionFilter();
		
		// login to jira
		String response = given().header("Content-Type", "application/json").body("{\r\n" + 
				"    \"username\": \"aripbaizhomart\",\r\n" + 
				"    \"password\": \"q1w2e3\"\r\n" + 
				"}").log().all().filter(sf)
		.when().post("rest/auth/1/session").then().log().all().extract().response().asString();
		
		String expectedMessage = "Hi how are u";
		
		// add comment to issue
		String addComment = given().pathParam("id", "10004").header("Content-Type", "application/json").body("{\r\n" + 
				"    \"body\": \""+expectedMessage+"\",\r\n" + 
				"    \"visibility\": {\r\n" + 
				"        \"type\": \"role\",\r\n" + 
				"        \"value\": \"Administrators\"\r\n" + 
				"    }\r\n" + 
				"}").filter(sf)
		.when().post("/rest/api/2/issue/{id}/comment").then().log().all().assertThat().statusCode(201).extract().response().asString();
		JsonPath js = new JsonPath(addComment);
		String commnetId = js.getString("id");
		
		// add atachment
		given().header("X-Atlassian-Token", "no-check").header("Content-Type", "multipart/form-data")
		.pathParam("id", "10004").filter(sf).multiPart("file", new File("jira.txt"))
		.when().post("/rest/api/2/issue/{id}/attachments").then().log().all().assertThat().statusCode(200);
		
		// get issue
		String issueDetail = given().filter(sf).pathParam("key", "10004").queryParam("fields", "comment")
		.when().get("/rest/api/2/issue/{key}").then().log().all().extract().response().asString();
		
		JsonPath jp = new JsonPath(issueDetail);
		int commentCount = jp.getInt("fields.comment.comments.size()");
		for(int i=0; i<commentCount; i++) {
			String allCommentId = jp.get("fields.comment.comments["+i+"].id").toString();
			if (allCommentId.equalsIgnoreCase(commnetId)) {
				String issueBody = jp.get("fields.comment.comments["+i+"].body").toString();
				System.out.println(issueBody);
				Assert.assertEquals(issueBody, expectedMessage);
			}

		}
		
	}

} 
