
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.files.AddressJSON;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.List;

public class OAuthTest {

	@Test
	public void test() {

		String body = String.format("username=gaukhar@cm&password=1&client_id=clever-crm-vue&grant_type=password");

		String accessTokenResponse = given().urlEncodingEnabled(false).headers("Content-Type", "application/x-www-form-urlencoded")
				.headers("Cookie", "KEYCLOAK_LOCALE=ru").body(body).when().log().all()
				.post("https://testsso.clevermart.kz/auth/realms/clever-market/protocol/openid-connect/token").asString();

		JsonPath js = new JsonPath(accessTokenResponse);
		String accessToken = js.getString("access_token");

		//String token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJIOVEzTWRvQ3NHaEE1NjZRdmxEWlRUTFMzNzJ6a2dLeUF0ampqOTNFcW1nIn0.eyJleHAiOjE2MzI0MTU3MTksImlhdCI6MTYzMjQxMzkxOSwianRpIjoiZWYwZjMwMDMtYmMwZi00M2Q4LWE1NzUtMTk2Y2Y2NWZjNDc3IiwiaXNzIjoiaHR0cHM6Ly90ZXN0c3NvLmNsZXZlcm1hcnQua3ovYXV0aC9yZWFsbXMvY2xldmVyLW1hcmtldCIsInN1YiI6ImNkOWRhMDdiLTU1MTgtNDk0MS1iNDRjLTI1Y2E2ZDE2MWVhMyIsInR5cCI6IkJlYXJlciIsImF6cCI6ImNsZXZlci1jcm0tdnVlIiwic2Vzc2lvbl9zdGF0ZSI6IjY4MmM2ZGMzLWNkMzMtNDU4Ny1iZWYyLTRjNWNmMjE3YjA1NiIsImFjciI6IjEiLCJhbGxvd2VkLW9yaWdpbnMiOlsiaHR0cDovL2xvY2FsaG9zdDo4MDgwIiwiaHR0cHM6Ly90ZXN0Y3JtLmNsZXZlcm1hcnQua3oiLCJodHRwOi8vbG9jYWxob3N0OjgwODEiLCJodHRwOi8vMTAuMC4xOTYuNzozMDU1MCJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsiUk9MRV9DUk1fVVNFUiIsIlJPTEVfQURNSU4iLCJST0xFX0NBTExfQ0VOVEVSX01BTkFHRVIiLCJST0xFX0NSTV9NQU5BR0VSIl19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJiaXJ0aGRhdGUiOiIyMDAwLTAzLTA5IiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImdlbmRlciI6ImZlbWFsZSIsIm5hbWUiOiLQk9Cw0YPRhdCw0YAg0JEiLCJncm91cHMiOlsiR1JPVVBfU1VQUExJRVJfMTIiLCJHUk9VUF9XQVJFSE9VU0VfV0FSRUhPVVNFXzEiXSwicHJlZmVycmVkX3VzZXJuYW1lIjoiZ2F1a2hhckBjbSIsImdpdmVuX25hbWUiOiLQk9Cw0YPRhdCw0YAiLCJmYW1pbHlfbmFtZSI6ItCRIiwiZW1haWwiOiJnYXVraGFyQGNtIn0.KGnygY1G-3ledTpXwk4k_BYceU1n28yIbCrIN1U_wncWSTI8BcKDw4mPP4qzHnZkwDAZENopkTWX8eRNR_LXWfUEQ5iONn4b-Qqvx9rEpqTOwuopAv_MrXfrbxg02jvdY65p35cn47h0D1_kt_fjOQ9X4sMk7sAK9f0IsbDUFD3itr9YwFdrCSWrbPE01cTCb33QMkUZq_cYvBP5gCQVP2NPoAzMpwm5KDIWmxYjWeQ7on5724C1vI44vZKhKo7OnDiGdGhLNJhdZ2NVV6pSSjV2j7DrIj3OqcCHNX1-uvxvvWKYpHJyXZBOa8TDMbVnoIHXD2JWLqB1Cwr0am5BnA";

		/*
		 * RequestSpecification requestSpec = new
		 * RequestSpecBuilder().setBaseUri("https://testapi.clevermart.kz")
		 * .setContentType(ContentType.JSON).log(LogDetail.ALL).build();
		 * RestAssured.requestSpecification = requestSpec;
		 * 
		 * ResponseSpecification responseSpec = new
		 * ResponseSpecBuilder().expectStatusCode(201).log(LogDetail.BODY).build();
		 * RestAssured.responseSpecification = responseSpec;
		 */

		RestAssured.baseURI = "https://testapi.clevermart.kz";

		// POST Address
		String postResponse = given().header("Content-Type", "application/json").header("Authorization", "Bearer " + accessToken + "").log().all()
				.body(AddressJSON.postAddress()).when().post("/api/v1/public/addresses").then().log().all().assertThat().statusCode(200)
				.extract().response().asString();

		JsonPath jp = new JsonPath(postResponse);
		String addressId = jp.getString("id");
		System.out.println("\n" + addressId + "\n");

		// GET Address
		String getResponse = given().header("Content-Type", "application/json").header("Authorization", "Bearer " + accessToken + "").log().all()
				.when().get("/api/v1/public/addresses").then().log().all().assertThat().statusCode(200)
				.extract().response().asString();
		
		JsonPath getResponseBody = new JsonPath(getResponse);
		
		List<Integer> jResponse = getResponseBody.getList("id");
		for (Integer id : jResponse) {
            if (id.toString().equals(addressId)) {
                System.out.println("\n\n\n"+"POSTted response GETTED");
            } else {
            	System.out.println("\n\n\n"+"POSTted response !NOT! GETTED");
            }
        }

	}

}
