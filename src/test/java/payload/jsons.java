package payload;

public class jsons {

	
		// TODO Auto-generated method stub
		
		
	

	public static String AddPlace() {
		// TODO Auto-generated method stub
		return "{\r\n"
		+ "  \"location\": {\r\n"
		+ "    \"lat\": -38.383494,\r\n"
		+ "    \"lng\": 33.427362\r\n"
		+ "  },\r\n"
		+ "  \"accuracy\": 50,\r\n"
		+ "  \"name\": \"Priyaa house\",\r\n"
		+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
		+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
		+ "  \"types\": [\r\n"
		+ "    \"shoe park\",\r\n"
		+ "    \"shop\"\r\n"
		+ "  ],\r\n"
		+ "  \"website\": \"http://google.commm\",\r\n"
		+ "  \"language\": \"French-IN\"\r\n"
		+ "}\r\n"
		+ "";

	}


public static String mockjson() {
	// TODO Auto-generated method stub

	return "{\r\n"
			+ "\r\n"
			+ "\"dashboard\": {\r\n"
			+ "\r\n"
			+ "\"purchaseAmount\": 910,\r\n"
			+ "\r\n"
			+ "\"website\": \"rahulshettyacademy.com\"\r\n"
			+ "\r\n"
			+ "},\r\n"
			+ "\r\n"
			+ "\"courses\": [\r\n"
			+ "\r\n"
			+ "{\r\n"
			+ "\r\n"
			+ "\"title\": \"Selenium Python\",\r\n"
			+ "\r\n"
			+ "\"price\": 50,\r\n"
			+ "\r\n"
			+ "\"copies\": 6\r\n"
			+ "\r\n"
			+ "},\r\n"
			+ "\r\n"
			+ "{\r\n"
			+ "\r\n"
			+ "\"title\": \"Cypress\",\r\n"
			+ "\r\n"
			+ "\"price\": 40,\r\n"
			+ "\r\n"
			+ "\"copies\": 4\r\n"
			+ "\r\n"
			+ "},\r\n"
			+ "\r\n"
			+ "{\r\n"
			+ "\r\n"
			+ "\"title\": \"RPA\",\r\n"
			+ "\r\n"
			+ "\"price\": 45,\r\n"
			+ "\r\n"
			+ "\"copies\": 10\r\n"
			+ "\r\n"
			+ "}\r\n"
			+ "\r\n"
			+ "]\r\n"
			+ "\r\n"
			+ "}";
}





public static String addbook(String isbn, String aisle) {
	String addbook="{\r\n"
			+ "\r\n"
			+ "\"name\":\"Learn Apppium Automation with Java\",\r\n"
			+ "\"isbn\":\""+isbn+"\",\r\n"
			+ "\"aisle\":\""+aisle+"\",\r\n"
			+ "\"author\":\"Me foe\"\r\n"
			+ "}";
	return addbook;

}

public static String delbook(String isbn, String aisle)
{
	return aisle;
}
}
