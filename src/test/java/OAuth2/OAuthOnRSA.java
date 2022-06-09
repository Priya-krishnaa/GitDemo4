package OAuth2;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import JsonCodeDeserializationToJavaCode.ResponseGetCourse;
import JsonCodeDeserializationToJavaCode.api;
import JsonCodeDeserializationToJavaCode.webAutomation;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class OAuthOnRSA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//  https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php
		
		String[] a= {"Selenium Webdriver Java","Cypress","Protractor","fjfjk"}; 
		String url="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWi3UuDlJ8F7V8eLoPPEm8AD8tscygOAU3wC7GoL4Shi0k0aOH7TuQW_h0oiTXQYvA&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
		String partial_url= url.split("code=")[1];
		String code= partial_url.split("&scope")[0];
		System.out.println("Code is :-> " + code);
		System.out.println("***************************************************************************************************************************************************************************************");
		System.out.println("***************************************************************************************************************************************************************************************");
		
		String get_token_resp=given().urlEncodingEnabled(false).log().all().queryParam("code", code).queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php").queryParam("grant_type", "authorization_code").queryParam("state", "verifydss").when().post("https://www.googleapis.com/oauth2/v4/token").then().log().all().extract().response().asString();
		JsonPath js= new JsonPath(get_token_resp);
		System.out.println(js);
		System.out.println("***************************************************************************************************************************************************************************************");
		System.out.println("***************************************************************************************************************************************************************************************");

		String access_token=js.getString("access_token");
		System.out.println("Access token :->" + access_token);
		System.out.println("***************************************************************************************************************************************************************************************");
		System.out.println("***************************************************************************************************************************************************************************************");

		
	//	String actualresp=given().log().all().queryParam("access_token", access_token).when().get("https://rahulshettyacademy.com/getCourse.php").then().log().all().toString();
	//	System.out.println(actualresp);
	//	System.out.println("***************************************************************************************************************************************************************************************");

		
		ResponseGetCourse gc=given().queryParam("access_token", access_token).expect().defaultParser(Parser.JSON)
			.when()
				.get("https://rahulshettyacademy.com/getCourse.php").as(ResponseGetCourse.class);
				System.out.println(gc);
				System.out.println(gc.getLinkedIn());
				
			
				List<api> getApiCourses=gc.getCourses().getApi();
				
				for(int i=0; i< getApiCourses.size();i++)
				{
					if(getApiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing") )
						System.out.println(getApiCourses.get(i).getPrice());
				}
				
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				ArrayList<String> b= new ArrayList<String>();
				
				List<webAutomation> getWebAutomationCourses= gc.getCourses().getWebAutomation();
				
				for(int i=0; i< getWebAutomationCourses.size();i++)
				{
					System.out.println("******************************************************************************************************************");
					b.add(getWebAutomationCourses.get(i).getCourseTitle());
				}
				System.out.println(b);
				System.out.println("----------------------------------------");
			
				List<String> expectedArray= Arrays.asList(a);
				System.out.println(expectedArray);
				SoftAssert sa= new SoftAssert();
				Assert.assertEquals(b,expectedArray,"error");
				System.out.println("Success");
				
				
				
				
				
	}

}
