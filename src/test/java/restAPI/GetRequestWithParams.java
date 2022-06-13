package restAPI;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequestWithParams {
	@Test
	public void test1()
	{
		RestAssured.baseURI="http://localhost:3000/employees";
		RequestSpecification request=RestAssured.given();
		Response response=request.param("id", "1").get();
		JsonPath jsonPath=response.jsonPath();
		List<String> name=jsonPath.get("name");
		String responseBody=response.getBody().asString();
	   String responseHeader=response.getHeader("Content-Type");
		int responseCode=response.getStatusCode();
		System.out.println(responseBody);
	//	System.out.println(name.get(0));
		for(int i=0;i<name.size();i++)
		{
			System.out.println(name.get(i));
		}
		Assert.assertEquals(responseCode, 200);	
		Assert.assertTrue(name.get(0).contains("Pankaj"));
		Assert.assertTrue(responseHeader.contains("application/json; charset=utf-8"));
	}
}
