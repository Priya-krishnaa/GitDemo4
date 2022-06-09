package RestAPIdemo;

import org.testng.annotations.Test;

import PayloadPractice.JsonPayload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class Basics2AddBook {
	
	@Test
	public void addbook()
	{
	RestAssured.baseURI="http://216.10.245.166/";
	
	String resp=given().header("Content-Type","application/json").body(JsonPayload.addbook("7485", "dhfhf")).when().post("Library/Addbook.php").then().assertThat().statusCode(200).extract().response().asString();
	JsonPath js= new JsonPath(resp);
	String id=js.get("ID");
	System.out.println(id);
	
	}

}
