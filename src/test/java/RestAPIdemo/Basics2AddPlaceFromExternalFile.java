package RestAPIdemo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import PayloadPractice.JsonPayload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Basics2AddPlaceFromExternalFile {

	@Test
	public void addplace() throws IOException {
RestAssured.baseURI= "https://rahulshettyacademy.com";
	
	// Add Place
	
	
	String resp=given().log().all().header("Content-Type","application/json").body(new String(Files.readAllBytes(Paths.get("C:\\Users\\pmkrishna\\OneDrive - Dotgo\\Documents\\addplace.json")))).when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Connection", "Keep-Alive").extract().response().asString();
	JsonPath js= new JsonPath(resp);
	String place_id=js.get("place_id");
	System.out.println("Add place place_id is : " + place_id);
	
}
}
