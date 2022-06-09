package JiraAutomation;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class AddingAComment2 {

	@Test
	public void commenting() {
		
		RestAssured.baseURI= "http://localhost:8080";
		
		// Create a login session with jsession id
		
		// Handling Https certification
		SessionFilter session = new SessionFilter();
		given().log().all().relaxedHTTPSValidation().header("Content-Type","application/json").body("{ \"username\": \"priya.77498\", \"password\": \"99Priya@1\" }\r\n"
				+ "").filter(session).when().post("/rest/auth/1/session").then().assertThat().statusCode(200);
		
		// Adding a comment
		
		String respo=given().log().all().pathParam("key", "10104").header("Content-Type","application/json").body("{\r\n"
				+ "    \"body\": \"This is a comment that only i can see and its for 10104 in PTAC.\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session).when().post("/rest/api/2/issue/{key}/comment").then().log().all().assertThat().statusCode(201).extract().response().asString();
	
		JsonPath js1= new JsonPath(respo);
		int id=js1.getInt("id");
		
		
		// Add an attachment
	//	multipart
	//	content type
	//	header
		
		
		String resp=given().header("X-Atlassian-Token","no-check").pathParam("key", "10104").filter(session).header("Content-Type","multipart/form-data").multiPart("file",new File("C:\\Users\\pmkrishna\\eclipse-workspace\\APIDemo\\jira.txt")).when().post("/rest/api/2/issue/{key}/attachments").then().assertThat().statusCode(200).extract().response().asString();
		System.out.println(resp);
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
	
		
		// Get an issue and usage of both path parameters and query parameters
		
		String res=given().log().all().filter(session).pathParam("key", "10104").queryParam("fields","comment").when().get("/rest/api/2/issue/{key}").then().log().all().assertThat().statusCode(200).extract().response().asString();
		System.out.println(res);
		
		
		// Get the comment id and verify in the results
		
		JsonPath js= new JsonPath(res);
		int commentsize=js.get("fields.comment.comments.size()");
		for(int i=0;i< commentsize; i++)
		{
			int id1=js.getInt("fields.comment.comments["+i+"].id ");
			if(id==id1)
			{
				System.out.println("Newly added comment is present and the id is: " + id1);
				System.out.println(js.getString("fields.comment.comments["+i+"].body"));
			}
			
			
		}
		
	}

	

}
