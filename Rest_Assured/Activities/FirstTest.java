package examples;


import static org.testng.Assert.assertEquals;
import javax.annotation.meta.When;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FirstTest {
	//Get 
	@Test
	public void getRequestWithQueryParam (){
	Response response =
			given().
			baseUri("https://petstore.swagger.io/v2/pet").
			header("Content-type", "application/jason").
			queryParam("status", "alive").
			
			when().
			get("/findByStatus");
	
	 System.out.println(response.getStatusCode());
	 System.out.println(response.getBody().asString());
	 System.out.println(response.getBody().asPrettyString()); 
	 System.out.println(response.getHeaders().asList()); 
	
	 
	 String petStatus = response.then().extract().path("[0].status");
	 //Assert with TestNg
	 assertEquals(petStatus,  "alive");
	 //RestAssured Assert
	 response.then().statusCode(200).body("[0].status", equalTo("alive"));
	 
	}
	
	@Test
	public void getRequestWithPathParam(){
		//Send Request, receive response and perfrom assertions
				given().
				baseUri("https://petstore.swagger.io/v2/pet").
				header("Content-type", "application/jason").
				pathParam("petId", 77232).
				log().all().
				
				when().get("/{petId}").
				
		        then().statusCode(200).
				body("name", equalTo("Hansel")).
				log().all();
				
		}
}