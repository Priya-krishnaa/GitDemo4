package RestAPIdemo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import PayloadPractice.JsonPayload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Basics1AddUpdateGetPlace {

	@Test
	public void AddPlace() {
	
	RestAssured.baseURI= "https://rahulshettyacademy.com";
	
	// Add Place
	
	
	String resp=given().log().all().header("Content-Type","application/json").body(JsonPayload.AddPlace()).when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Connection", "Keep-Alive").extract().response().asString();
	JsonPath js= new JsonPath(resp);
	String place_id=js.get("place_id");
	System.out.println("Add place place_id is : " + place_id);
	
	// Update Place 
	
	given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body(JsonPayload.updatePlace(place_id)).when().put("/maps/api/place/update/json").then().log().all().assertThat().statusCode(200);
	
	// Get Place
	
	String respget=given().log().all().queryParam("key", "qaclick123").queryParam("place_id", place_id).when().get("/maps/api/place/get/json").then().log().all().assertThat().statusCode(200).extract().response().asString();
	JsonPath js1= new JsonPath(respget);
	
	String place_id_final=js1.get("place_id");
	
	System.out.println("Final place id= " + place_id_final);
	
	
	
	
	
	
	
}
}