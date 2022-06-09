package SerializatnPractice;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

public class AddPlace {
	@Test
	public void addplace() {
		
		PojoClass p= new PojoClass();
		p.setAccuracy(50);
		p.setName("Frontline house");
		p.setLanguage("French-IN");
		p.setAddress("29, side layout, cohen 09");
		p.setPhone_number("\"(+91) 983 893 3937");
		List<String> mylist= new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		p.setTypes(mylist);
		location l= new location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String resp=given().queryParam("key", "qaclick123").body(p).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(resp);
	}

}
