package SpecBuilders;


	
	import org.testng.annotations.Test;

import Serialization.JsonVariables;
import Serialization.location;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

	import java.util.ArrayList;

	
	public class SpecBuilder {	
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
			
			RequestSpecification reqspec= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
			
			RequestSpecification req=given().spec(reqspec).body(j);
			
			ResponseSpecification respspec = new ResponseSpecBuilder().expectStatusCode(200).build();
			String response=  req.when().post("/maps/api/place/add/json").then().spec(respspec).extract().response().asString();
			System.out.println(response);
		
		
		
		}
	
}
