package Hallo_Club_Apis_Automation;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import HalloClub.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class RegisterApi {
	
	
	@BeforeMethod

	public String Register()
	{
		RestAssured.baseURI="https://api.halloclub.com/dev/";
		
		String Reg=given().log().all().body(Payload.Registeration()).header("Content-Type","application/json")
		.when().post("user/login")
		.then().log().all().assertThat().statusCode(200).body("message", equalTo("OTP Successfully sent"))
		.extract().response().asString();
		
		JsonPath js= new JsonPath(Reg);
		String UserId=js.getString("data.userId");
		System.out.println(UserId);
		Assert.assertEquals(Reg, UserId);
		
		
		String Veryfyopt =given().log().all().body(Payload.Verify_Otp()).header("Content-Type","application/json")
		.when().post("user/verify-otp")
		.then().log().all().assertThat().statusCode(200).body("message", equalTo("Login successful"))
		.extract().response().asString();
				
		JsonPath js1=new JsonPath(Veryfyopt);
		String Token =js1.getString("data.userdata.token");
		System.out.println(Token);
		Assert.assertEquals(Veryfyopt, Token);
		return Token;
				
		
	}
	

}
