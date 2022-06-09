package JiraAutomation;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;


public class DupeAddingAComment {
	
	@Test
	public void AddAComment() {
		
		RestAssured.baseURI="http://localhost:8080/";
		
		SessionFilter session= new SessionFilter();
		// Login
		
		given().log().all().header("Content-type","application/json").body("{ \"username\": \"priya.77498\", \"password\": \"99Priya@1\" }").filter(session).when().post("rest/auth/1/session").then().log().all().assertThat().statusCode(200).extract().response().asString();
				
		// Add a comment
		
		String resp=given().log().all().pathParam("issue","10103").header("Content-type","application/json").body("{\r\n"
				+ "    \"body\": \"This is a comment on 29th may that only i can see and its for 10103.\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session).when().post("/rest/api/2/issue/{issue}/comment").then().log().all().assertThat().statusCode(201).extract().response().asString();
		
		JsonPath js= new JsonPath(resp);
		String comment=js.getString("body");
		String commentid= js.get("id");
		System.out.println(comment);
		
		// Add attachment
		
		given().log().all().header("Content-Type","multipart/form-data").pathParam("issue", "10103").header("X-Atlassian-Token","no-check").filter(session).multiPart("file", new File("jira.txt")).when().post("/rest/api/2/issue/{issue}/attachments").then().log().all().assertThat().statusCode(200);
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
		
		// Get issue
		String response=given().log().all().filter(session).pathParam("issueid", "10103").queryParam("fields", "comment").when().get("rest/api/2/issue/{issueid}").then().assertThat().statusCode(200).extract().response().asString();
		System.out.println(response);
		
	}
}