package RestAPIdemo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import payload.jsons;

import static org.hamcrest.Matchers.*;

import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		
		// Add a place
		
		
		String response=given().log().all().queryParam("key", "qaclick123").contentType("application/json").body(jsons.AddPlace()).when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		System.out.println("-----------------------------------------------------------------------");
		System.out.println(response);
		
		JsonPath js= new JsonPath(response);
		String place_id=js.getString("place_id");
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println(place_id);
		System.out.println("-----------------------------------------------------------------------------------");
		
		
		// Update a place with new address
		
		
		String newaddress= "70 Winterr walk, USA";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body("{\r\n"
				+ "\"place_id\":\""+place_id+"\",\r\n"
				+ "\"address\":\""+newaddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}").when().put("/maps/api/place/update/json").then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
System.out.println("---------------------------------------------------------------------------------------------------------------");
		

// Get the place with new address


		String resp=given().log().all().queryParam("key", "qaclick123").queryParam("place_id",place_id).when().get("/maps/api/place/get/json").then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js1= new JsonPath(resp);
		String newadd=js1.getString("address");
		SoftAssert sa= new SoftAssert();
		sa.assertEquals(newadd, newaddress,"Incorrect address");
		sa.assertAll();
		
		
	}

}
