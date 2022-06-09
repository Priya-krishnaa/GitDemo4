package RestAPIdemo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;
import io.restassured.path.json.JsonPath;
import payload.jsons;

import static io.restassured.RestAssured.*;

public class AddAndDeleteBooksUsingDataProvider {

@Test(dataProvider="data")
public void add(String isbn, String aisle) {
	RestAssured.baseURI="http://216.10.245.166";
	String resp=given().log().all().header("Content-Type","application/json").body(jsons.addbook(isbn, aisle)).when().post("/Library/Addbook.php").then().log().all().assertThat().statusCode(200).extract().response().asString();
	JsonPath js= new JsonPath(resp);
	String id=js.get("ID");
	System.out.println("ID is" + id);
	
	given().log().all().header("Content-Type","application/json").body("{\r\n"
			+ "\r\n"
			+ "\"ID\" : \""+id+"\"\r\n"
			+ "\r\n"
			+ "} ").when().post("/Library/DeleteBook.php").then().log().all().assertThat().statusCode(200).body("msg", equalTo("book is successfully deleted"));
}


@DataProvider
public Object[][] data() {
	Object[][] data= new Object[2][2];
	data[0][0]= "dnfd";
	data[0][1]= "2345";
	data[1][0]= "dmsj";
	data[1][1]= "7677";
	return data;
}
	@DataProvider
	public Object[][] getdata() {
		return new Object[][] {{"urug","3647"},{"smnfn","3747"},{"fndms","7484"}};
	}
	
}



