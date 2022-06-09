package RestAPIdemo;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PayloadPractice.JsonPayload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static org.hamcrest.Matchers.*;

public class Basics2ParameterizatnUsingDataProvider {
	
	@Test(dataProvider="books")
	public void Addbook(String isbn, String aisle)
	{
	RestAssured.baseURI="http://216.10.245.166/";
	
	String resp=given().header("Content-Type","application/json")
			.body(JsonPayload.addbook(isbn, aisle))
			.when().post("Library/Addbook.php")
			.then().assertThat().statusCode(200).extract().response().asString();
	JsonPath js= new JsonPath(resp);
	String id=js.get("ID");
	System.out.println(id);
	
	
	given().header("Content-Type","application/json").body("{\r\n"
			+ "\r\n"
			+ "\"ID\" : \""+id+"\"\r\n"
			+ "\r\n"
			+ "} \r\n"
			+ "").when().delete("Library/DeleteBook.php").then().log().all().assertThat().statusCode(200).body("msg",equalTo( "book is successfully deleted"));
	}
	@DataProvider(name="books")
	public Object[][] getData() {
		
		return new Object[][] {{"ffbfn","43758"},{"fhgjgk","8859"},{"jbng","85285"}};
		
	
	
	

}
}
