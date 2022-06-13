package restAPI;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteRequest {

	@Test
	public void test1()
	{
		RestAssured.baseURI="http://localhost:3000/employees";
		RequestSpecification request=RestAssured.given();
		
		Response reponse=request.delete("/3");
		String ResponseBody=reponse.getBody().asString();
		System.out.println(ResponseBody);		
		int responseCode=reponse.getStatusCode();
		System.out.println(responseCode);
		Assert.assertEquals(responseCode, 200);	
		
}
}
