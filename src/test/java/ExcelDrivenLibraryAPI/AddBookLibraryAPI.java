package ExcelDrivenLibraryAPI;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AddBookLibraryAPI {

@Test
public void add() throws IOException {
	RestAssured.baseURI="http://216.10.245.166";
	
	DataDrive data= new DataDrive();
	ArrayList a= data.getData("RestAssuredInfo","Sheet2");
	HashMap <String,Object> map= new HashMap();
	map.put("name", a.get(1));
	map.put("isbn", a.get(2));
	map.put("aisle", a.get(3));
	map.put("author", a.get(4));
	
	String resp=given().log().all().header("Content-Type","application/json").body(map).when().post("/Library/Addbook.php").then().log().all().assertThat().statusCode(200).extract().response().asString();
	JsonPath js= new JsonPath(resp);
	String id=js.get("ID");
	System.out.println("ID is" + id);
	
	}



}



