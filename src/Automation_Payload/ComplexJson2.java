package Automation_Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJson2 

{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		JsonPath cplxjson=new JsonPath(Payload_Class.CoursePrice());

		int coursescount = cplxjson.getInt("courses.size()");
		System.out.println("the number of count is");
		System.out.println(coursescount);
		
		int purchaseamount = cplxjson.getInt("dashboard.purchaseAmount");
		System.out.println("the purchase amout is");
		System.out.println(purchaseamount);
		
		String Title = cplxjson.get("courses[0].title");
		System.out.println("Title is");
		System.out.println(Title);
		
		
		for(int i=0; i<coursescount; i++)
		{
			System.out.println(cplxjson.get("courses["+i+"].title").toString());
			
			System.out.println(cplxjson.getInt("courses["+i+"].price"));
			
		}	

	}

}
