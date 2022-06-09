package LibraryAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import payload.jsons;

import static io.restassured.RestAssured.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PayloadPractice.JsonPayload;

public class LibAPIAddBook {
	@Test(dataProvider="getData")
	public void addbook(String isbn, String aisle) {
		
		RestAssured.baseURI="http://216.10.245.166/";
		String resp=given().header("Content-Type","application/json").body(JsonPayload.addbook(isbn, aisle)).when().post("Library/Addbook.php").then().log().all().assertThat().statusCode(200).extract().response().asString();
	
		//String resp=given().log().all().header("Content-Type","application/json").body(JsonPayload.addbook(isbn, aisle)).when().post("Library/Addbook.php").then().log().all().assertThat().statusCode(200).extract().response().asString();
	JsonPath js= new JsonPath(resp);
	
		String id= js.get("ID");
		System.out.println(id);
	
	
	// Delete
	
	given().header("Content-Type","application/json").body("{\r\n"
			+ "\r\n"
			+ "\"ID\" : \""+id+"\"\r\n"
			+ "\r\n"
			+ "} \r\n"
			+ "").when().post("Library/DeleteBook.php").then().log().all().assertThat().statusCode(200);
	}
	@DataProvider
	public Object[][] getData() {
		return new Object[][] {{"fff","8989"},{"were","8989"},{"dsdd","4849"}};
	
		
	}
	

}
