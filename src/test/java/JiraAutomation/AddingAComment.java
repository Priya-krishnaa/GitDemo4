package JiraAutomation;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class AddingAComment {
	
	@Test
	public void comment() {
		RestAssured.baseURI="http://localhost:8080/";
		
		// login first to jira
		SessionFilter session= new SessionFilter();
		given().log().all().header("Content-type","application/json").body("{ \"username\": \"priya.77498\", \"password\": \"99Priya@1\" }").filter(session).when().post("rest/auth/1/session").then().assertThat().statusCode(200);
			
		
		// Add the comment 
		String commentresp=given().log().all().pathParam("Key", "10001").header("Content-type","application/json").body("{\r\n"
				+ "    \"body\": \"This is a whole new  comment that only administrators can see.\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session).when().post("rest/api/2/issue/{Key}/comment").then().log().all().assertThat().statusCode(201).extract().response().asString();
		JsonPath js= new JsonPath(commentresp);
		int commentid=js.getInt("id");
		
	
	
	// Add an attachment
		
		given().log().all().header("X-Atlassian-Token","no-check").filter(session).pathParam("Key", "10001").header("Content-type","multipart/form-data").multiPart("file", new File("jira.txt")).when().post("rest/api/2/issue/{Key}/attachments").then().log().all().assertThat().statusCode(200);
		
	System.out.println("----------------------------------------------------------------------------------------------------------------------------------");

	// Using get method to retreive the above issue and get particular selected fields in the json
		String getresp=given().log().all().filter(session).pathParam("key", "10001").queryParam("fields", "comment").when().get("/rest/api/2/issue/{key}").then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		// Check if new comment added is visible or not
		
		
		JsonPath js1= new JsonPath(getresp);
		int SizeofComments=js1.getInt("fields.comment.comments.size()");
		
		for(int i=0; i< SizeofComments;i++)
		{
			int getCommentid= js1.getInt("fields.comment.comments["+i+"].id");
		System.out.println(getCommentid);
			if(getCommentid==commentid)
			{
				System.out.println("New comment id is " + getCommentid);
				System.out.println("Comment is :" +  js1.getString("fields.comment.comments["+i+"].body"));
			}
		}
		
		
		
		
	}	

}
