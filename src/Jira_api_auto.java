import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

import static io.restassured.RestAssured.*;

import java.io.File;
public class Jira_api_auto {
	
	public static void main(String[] args) {
		
		
		RestAssured.baseURI="http://localhost:8080/";
		
		SessionFilter session=new SessionFilter();
		
		//Login
		
		String response=given().header("Content-Type", "application/json").body("{\r\n"
				+ "    \"username\": \"Shanoor\",\r\n"
				+ "    \"password\": \"Shanoor@1234\"\r\n"
				+ "}").log().all().filter(session).when().post("rest/auth/1/session").then().log().all().extract().response().asString();
		
		//Add comment
		given().pathParam("key", "10101").header("Content-Type", "application/json").body("{\r\n"
				+ "    \"body\": \"Critical bug12345.\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").log().all().filter(session).when().post("rest/api/2/issue/{key}/comment").then().log().all().assertThat().statusCode(201);
		
		
		//Add attachment
		
		given().header("X-Atlassian-Token", "no-check" ).filter(session).pathParam("key", "10101").
		header("Content-Type","multipart/form-data").multiPart("file",new File("C:\\Users\\pc\\eclipse-workspace\\Project_Api_Automation\\src\\jira.txt")).when().
		post("rest/api/2/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);
	}

}
