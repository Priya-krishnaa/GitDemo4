package Serialization;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class AddAPlace {
	
	@Test
	public void test() {
		
		JsonVariables j= new JsonVariables();
		j.setAccuracy(50);
		j.setAddress("29, side layout, cohen 09");
		j.setLanguage("French-IN");
		location l= new location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		j.setLocation(l);
		j.setName("My house");
		j.setPhone_number("(+91) 983 893 3937");
		ArrayList<String> types= new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");
		j.setTypes(types);
		j.setWebsite("http://google.com");
		
		RestAssured.baseURI= "https://rahulshettyacademy.com/";
		
		String response=given().queryParam("key","qaclick123").body(j).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).extract().response().asString();
		System.out.println(response);
	}
	
	

}
