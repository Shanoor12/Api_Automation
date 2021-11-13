import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import Automation_Payload.Payload_Class;

public class Api_Automation {

	public static void main(String[] args) throws IOException {
		
		// TODO Auto-generated method stub

		RestAssured.baseURI= "https://rahulshettyacademy.com";
		
		String Response=given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").
		body(new String( Files.readAllBytes(Paths.get("E:\\Automation Rahul\\Api.json")))).
		when().post("/maps/api/place/add/json").
		then().log().all().assertThat().statusCode(200)
		.header("Server", "Apache/2.4.18 (Ubuntu)").body("scope", equalTo("APP")).extract().response().asString();
		
		System.out.println(Response);
		
		
		JsonPath js = new JsonPath(Response);
		String PlaceId=js.getString("place_id");
		
		System.out.println(PlaceId);
		
		String expectedAdddress="Shanoor";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").
		body("{\r\n"
				+ "\"place_id\":\""+PlaceId+"\",\r\n"
				+ "\"address\":\""+expectedAdddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ ""
		).
		//when().put("com/maps/api/place/update/json").
		//then().assertThat().statusCode(200).body("msg", equalTo( "Address successfully updated"));
		
		when().put("/maps/api/place/update/json").
		then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		
		String response1=given().log().all().queryParam("key", "qaclick123").queryParam("place_id", PlaceId).
		when().get("/maps/api/place/get/json").
		then().log().all().assertThat().extract().response().asString();
		
		
		System.out.println(response1);
		
		JsonPath js1= new JsonPath(response1);
		String ActualAddress=js1.getString("address");		
		
		System.out.println(ActualAddress);
		
		Assert.assertEquals(ActualAddress, expectedAdddress);
		
				
		
	}

}
