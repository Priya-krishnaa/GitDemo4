package RestAPIdemo;

import java.util.List;

import io.restassured.path.json.JsonPath;
import payload.jsons;

public class ComplexJSON {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JsonPath js= new JsonPath(jsons.mockjson());
		// courses count
		int courses=js.getInt("courses.size()");
		System.out.println(courses);
		// total purchase amount
		int purchaseamt= js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseamt);
		// Get first course title
		String firstcoursetitle= js.getString("courses[0].title");
		System.out.println(firstcoursetitle);
		// Get all the titles and all the prices
		int sum=0;
		for(int i=0; i< courses;i++)
		{
			
			String coursetitles= js.getString( "courses["+i+"].title");
			//System.out.println("coursetitles are");
			System.out.println(coursetitles);
			int pricesofeach= js.getInt("courses["+i+"].price");
			System.out.println(pricesofeach);
		//	System.out.println(js.get("courses["+i+"].price").toString());
			// copies
			int copiesofeach= js.getInt("courses["+i+"].copies");
			System.out.println(copiesofeach);
			int amount = pricesofeach * copiesofeach;
			System.out.println(amount);
			sum = sum + amount;
		
		//	System.out.println(js.get("courses["+i+"].copies").toString());
			
			// Get number of copies sold by cypress
			if(coursetitles.equals("Cypress"))
			{
				System.out.println( js.get("courses["+i+"].copies").toString());
				
			}
		}
		
		System.out.println("Total sum :" + sum);
	}

}
