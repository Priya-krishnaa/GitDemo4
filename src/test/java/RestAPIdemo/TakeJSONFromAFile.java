package RestAPIdemo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import io.restassured.RestAssured;
import payload.jsons;

public class TakeJSONFromAFile {

		public static void main(String[] args) throws IOException {
			// TODO Auto-generated method stub
			RestAssured.baseURI="https://rahulshettyacademy.com";
			// Add a place
			String response=given().log().all().queryParam("key", "qaclick123").contentType("application/json").body(new String(Files.readAllBytes(Paths.get("C:\\Users\\pmkrishna\\OneDrive - Dotgo\\Documents\\addplace.json")))).when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
			System.out.println("-----------------------------------------------------------------------");
			System.out.println(response);
			
		
	}

}
