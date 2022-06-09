package OAuth2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class OAuth2Mechanism {
	@Test
	public void DuperOuth() {
		
		/*
		 * // Get code System.setProperty("webdriver.chrome.driver",
		 * "C:\\Users\\pmkrishna\\Downloads\\chromedriver_win32 (4)\\chromedriver.exe");
		 * WebDriver driver= new ChromeDriver(); driver.get(
		 * "https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php"
		 * ); driver.manage().window().maximize();
		 * driver.findElement(By.xpath("//input[@type='email']")).sendKeys(
		 * "exploringnewskills@gmail.com");
		 * driver.findElement(By.xpath("(//span[@jsname='V67aGc'])[2]")).click();
		 */
		
		
		String url="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWi_wySLvUZfw-AN4zjM2Zry5bjHbJq-b98cuO-klyka7-lkkFr1oY2WKLSltCu2VQ&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&hd=gupshup.io&prompt=none";
		String split1=url.split("code=")[1];
		String code= split1.split("&scope")[0];
		
		
		// Get access token
		String get_token_resp=given().urlEncodingEnabled(false).log().all().queryParam("code", code).queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php").queryParam("grant_type", "authorization_code").queryParam("state", "verifydss").when().post("https://www.googleapis.com/oauth2/v4/token").then().log().all().extract().response().asString();
		
	//	String accesstokenresp= given().urlEncodingEnabled(false).log().all().queryParam("code","4%2F0AX4XfWj4cLnuXjvHcz65dmxuGA0kAF6k8v3XjcRqJax_Mw4XNcCw6VKdk6btYExy3J439w").queryParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").queryParam("client_secret","erZOWM9g3UtwNRj340YYaK_W").queryParam("redirect_uri","https://rahulshettyacademy.com/getCourse.php").queryParam("grant_type","authorization_code").queryParam("state", "verifydss").when().post("https://www.googleapis.com/oauth2/v4/token").then().log().all().extract().response().asString();
			JsonPath js= new JsonPath(get_token_resp);
			String access_token=js.getString("access_token");
			
		//Actual req
		String resp=given().log().all().queryParam("access_token", access_token).when().get("https://rahulshettyacademy.com/getCourse.php").then().log().all().extract().response().asString();
		
		
		
		
	}

}
