package RestAPIdemo;

import org.testng.Assert;
import org.testng.annotations.Test;

import PayloadPractice.JsonPayload;
import io.restassured.path.json.JsonPath;

@Test
public class Basics11ComplexJson {
	
	public void complex() {
	JsonPath js1= new JsonPath(JsonPayload.mockJson());
	// Print no of coursess
	

	int  size=js1.get("courses.size");
	System.out.println("Size of courses is :" + size);

	
	// Print purchase amt
	
	int amt=js1.get("dashboard.purchaseAmount");
	System.out.println("Purchase amt is: " + amt);
	
	
	// Print title of first course
	
	String firsttitle=js1.getString("courses[0].title");
	System.out.println("First title:" + firsttitle);
	System.out.println("===================================================================================");
	
	// all course titlse and thir resp prices
	int sum=0;
	for(int i=0;i<size;i++)
		{
			String titles=js1.getString("courses["+i+"].title");
			System.out.println(" title:" + titles);
			int prices= js1.getInt("courses["+i+"].price");
			System.out.println("Prices :"+ prices);
			System.out.println("===================================================================================");
			// print no of courses sold by rest
			if(titles.equalsIgnoreCase("RPA"))
			{
				int rpacourses= js1.getInt("courses["+i+"].copies");
				System.out.println("RPA Copies:" + rpacourses);
				
			}
			System.out.println("===================================================================================");
			// print if sum= purchaseamt
			
		int copies= js1.getInt("courses["+i+"].copies");
	int	sum1= prices*copies;
		sum= sum+sum1;
		
		
		
		}
	
	System.out.println("Total sum :" +sum);
	Assert.assertEquals(sum, amt);
	
	
	}
}
