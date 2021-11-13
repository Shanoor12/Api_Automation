package HalloClub;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

public class JwtToken {
	
	@Test
	
	public void JwtTokenhandle()
	{
		RestAssured.baseURI="https://api.halloclub.com";
		
		String Registration=given().log().all().body(Payload.Registeration()).header("Content-Type","application/json")
		.when().post("https://api.halloclub.com/user/login")
		.then().log().all().assertThat().statusCode(200).body("message", equalTo("OTP Successfully sent"))
		.extract().response().asString();
		
		JsonPath js=new JsonPath(Registration);
		String UserId =js.getString("userId");
		
		System.out.println(UserId);
		
		
//		String Veryfyopt =given().log().all().body(Payload.Verify_Otp()).header("Content-Type","application/json")
//		.when().post("https://api.halloclub.com/user/verify-otp")
//		.then().log().all().assertThat().statusCode(200).body("message", equalTo("Login successful"))
//		.extract().response().asString();
//		
//		JsonPath js1=new JsonPath(Veryfyopt);
//		String Token =js1.getString("token");
//	
//		System.out.println(Token);
//		
//		
//		
//		given().log().all().header("Accept","*/*").header("Authorization","Bearer "+Token)
//		.when().get("https://api.halloclub.com/user/get")
//		.then().log().all().assertThat().statusCode(200).header("Content-Type","application/json").body("message", equalTo("User data fetched successfully"))
//		.extract().response().asString();
//		
//
//		JsonPath js2=new JsonPath(Veryfyopt);
//		String Id =js2.getString("id");
//		
//		System.out.println(Id);
//		
		
		
			
		
	}
	
}

	