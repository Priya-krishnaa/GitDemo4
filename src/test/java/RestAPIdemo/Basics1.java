package RestAPIdemo;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import PayloadPractice.JsonPayload;

import static org.hamcrest.Matchers.*;

public class Basics1 {
	@Test
	public void AddPlace() {
	
	RestAssured.baseURI= "https://rahulshettyacademy.com";
	
	given().log().all().header("Content-Type","application/json").body(JsonPayload.AddPlace()).when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Connection", "Keep-Alive");
	
	
	

}
}