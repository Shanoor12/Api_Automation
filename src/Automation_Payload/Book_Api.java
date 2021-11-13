package Automation_Payload;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


public class Book_Api {
	
	@Test(dataProvider="Booksdata")
	public void Library_Api(String isbn, String aisle)
	{
		RestAssured.baseURI ="http://216.10.245.166/Library/Addbook.php";
		
		String Book=given().header("Content-Type","application/json").
				body(Payload_Class.Library_Payload(isbn,aisle)).
		when().post("Library/Addbook.php").
		then().assertThat().statusCode(200).body("Msg", equalTo( "successfully added")).extract().response().asString();
		System.out.println(Book);
		
		JsonPath js=new JsonPath(Book);
		String id= js.get("ID");
		
		System.out.println(id);
		
		
		
		
	}
	@DataProvider(name ="Booksdata")
	public Object[][] GetBook()
	{
		return new Object[][] {{"defg","888",},{"hhh","mmmm"}};
		
	}
}
		
		