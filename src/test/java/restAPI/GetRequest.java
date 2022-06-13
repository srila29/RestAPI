package restAPI;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequest {
	@Test
	public void test1()
	{
		RestAssured.baseURI="http://localhost:3000/employees";
		RequestSpecification request=RestAssured.given();
		Response response=request.get();
		String responseBody=response.getBody().asString();
		int responseCode=response.getStatusCode();
		System.out.println(responseBody);
		Assert.assertEquals(responseCode, 200);	
	}

}
