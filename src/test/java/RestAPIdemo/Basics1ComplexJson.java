package RestAPIdemo;

import org.testng.annotations.Test;

import PayloadPractice.JsonPayload;
import io.restassured.path.json.JsonPath;

public class Basics1ComplexJson {
	
	@Test
	public void comp() {
	JsonPath js= new JsonPath(JsonPayload.mockJson());
	int count=js.get("courses.size()");
	System.out.println(count);
	
	int purchaseAmount= js.get("dashboard.purchaseAmount");
	
	System.out.println(purchaseAmount);
	
	String firsttitle= js.getString("courses[0].title");
	System.out.println(firsttitle);
	
	// Print All course titles and their respective Prices
	
	for(int i=0;i<count;i++)
	{
		
	
	String titles=js.getString("courses["+i+"].title");
	System.out.println(titles);
	System.out.println(js.get("courses["+i+"].price"));
	
	// Print no of copies sold by RPA Course
	
	if(titles.equalsIgnoreCase("Cypress"))
	{
	int copies=js.get("courses["+i+"].copies");
	System.out.println("Copies : " + copies);
	
	}
	}

	 // Verify if Sum of all Course prices matches with Purchase Amount
	int sum=0;
	for(int i=0;i<count;i++)
	{
		
	
	int price=js.get("courses["+i+"].price");
	int copies= js.get("courses["+i+"].copies");
	int payment= price*copies;
	
	 sum= sum+payment;
	}
	System.out.println(sum);
	
	if(purchaseAmount==sum)
	{
		System.out.println("Amount matches");
	}
	


}}
