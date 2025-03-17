package Project;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class  GitHub_RestAssured_Project{
	//SSH Key to test with
	String sshKey = "";
	int sshKeyId = 0;
	
		//Request Specification
	RequestSpecification requestSpec = new RequestSpecBuilder().
				setBaseUri("https://api.github.com/user/keys").
				addHeader("Authorization", "token").
				addHeader("Content-Type", "application/json").
				build();
		
		//Response Specification
	ResponseSpecification responseSpec = new ResponseSpecBuilder().
				expectResponseTime(lessThan(3000L)).
				expectBody("key", equalTo(sshKey)).
				expectBody("title", equalTo("TestAPIKey")).
				build();
		
		
	@Test(priority = 1)
	public void postRequest() {
		Map<String, String> reqBody = new HashMap<>();
		reqBody.put("title", "TestAPIKey");
		reqBody.put("key", sshKey);

	Response response = given()
		.spec(requestSpec)
		.body(reqBody)
		.log().all()// Add request body
		.when().post(); // Send POST request

	sshKeyId = response.then().extract().path("id");
	
	// Assertion
	response.then().log().all().statusCode(201).spec(responseSpec);
 }


	@Test(priority = 2)
	public void getRequest() {
		given().spec(requestSpec).pathParam("keyId", sshKeyId).
		when().get("/{keyId}").
		then().statusCode(200).spec(responseSpec);

		
	}

	@Test(priority = 3)
	public void deleteRequest() {
		given().spec(requestSpec).pathParam("keyId", sshKeyId).
		when().delete("/{keyId}").
		then().statusCode(204);
		
	}
}