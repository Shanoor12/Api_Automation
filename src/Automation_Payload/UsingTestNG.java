package Automation_Payload;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class UsingTestNG 
{
	
	@Test
	public void SumOfValidatons()
	
	{
		int sum =0;
		JsonPath cplxjson=new JsonPath(Payload_Class.CoursePrice());
		int coursescount = cplxjson.getInt("courses.size()");
		
		
		for(int i=0; i<coursescount; i++)
		{
			int prices=cplxjson.getInt("courses["+i+"].price");
			int copies=cplxjson.getInt("courses["+i+"].copies");
			int amount= prices*copies;
			System.out.println(amount);
			sum= sum+amount;			
		}
		
		int purchaseamount = cplxjson.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchaseamount);
		
		
	}

}
