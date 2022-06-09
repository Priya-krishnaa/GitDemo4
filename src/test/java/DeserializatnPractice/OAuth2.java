package DeserializatnPractice;


	

	import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

	import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
	public class OAuth2 {
	public class OAuth2Mechanism {
		@Test
		public void DuperOuth() {
			
			String[] actual = {"Selenium Webdriver Java","Cypress","Protractor"};
			String url="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWji_kYccCHdXX-3QwIqotaZ-kW0mGJOckeaXeanIEVI7qWYa-wZ6PvVPgVraVnG3Q&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&hd=gupshup.io&prompt=none";
			String split1=url.split("code=")[1];
			String code= split1.split("&scope")[0];
			
			
			// Get access token
			String get_token_resp=given().urlEncodingEnabled(false).log().all().queryParam("code", code).queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php").queryParam("grant_type", "authorization_code").queryParam("state", "verifydss").when().post("https://www.googleapis.com/oauth2/v4/token").then().log().all().extract().response().asString();
			
		//	String accesstokenresp= given().urlEncodingEnabled(false).log().all().queryParam("code","4%2F0AX4XfWj4cLnuXjvHcz65dmxuGA0kAF6k8v3XjcRqJax_Mw4XNcCw6VKdk6btYExy3J439w").queryParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").queryParam("client_secret","erZOWM9g3UtwNRj340YYaK_W").queryParam("redirect_uri","https://rahulshettyacademy.com/getCourse.php").queryParam("grant_type","authorization_code").queryParam("state", "verifydss").when().post("https://www.googleapis.com/oauth2/v4/token").then().log().all().extract().response().asString();
				JsonPath js= new JsonPath(get_token_resp);
				String access_token=js.getString("access_token");
				
			//Actual req
			 getcourse resp=given().queryParam("access_token", access_token).expect().defaultParser(Parser.JSON).when().get("https://rahulshettyacademy.com/getCourse.php").as(getcourse.class);
			 
			System.out.println(resp.getLinkedIn());
			System.out.println(resp.getInstructor());
			System.out.println( resp.getCourses().getApi().get(1).getCourseTitle());
			List<api> apicourses= resp.getCourses().getApi();
			
			for(int i=0;i< apicourses.size();i++)
			{
				if(apicourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
					System.out.println(apicourses.get(i).getPrice());
			}
			ArrayList<String> expectedarray= new ArrayList<String>();
			List<webAutomation> webautomationcourses= resp.getCourses().getWebAutomation();
			for(int i=0;i<webautomationcourses.size();i++)
			{
				System.out.println(expectedarray.add(webautomationcourses.get(i).getCourseTitle()));
			}
			
List<String> actualarray=Arrays.asList(actual);
			
			Assert.assertEquals(actualarray,expectedarray);
				
			
		}

	}


}
