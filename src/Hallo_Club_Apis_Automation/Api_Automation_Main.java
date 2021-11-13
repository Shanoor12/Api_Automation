package Hallo_Club_Apis_Automation;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;


public class Api_Automation_Main extends RegisterApi{
	
	@AfterMethod
	
	@Test
	public void Main()
	{
		
		String token= Register();
		
		String Getuser=given().log().all().header("Accept","*/*").header("Authorization","Bearer "+token)
		.when().get("user/get")
		.then().log().all().assertThat().statusCode(200).header("Content-Type","application/json").body("message", equalTo("User data fetched successfully"))
		.extract().response().asString();
						
				
		JsonPath js2=new JsonPath(Getuser);
		String Id =js2.getString("data.id");
		System.out.println(Id);
		Assert.assertEquals(Getuser, Id);
		
		
		
		
	}

}
