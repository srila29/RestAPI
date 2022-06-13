package restAPI;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.path.json.mapping.JsonObjectDeserializer;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequestWithJsonObject {
	@Test
	public void test1()
	{
		RestAssured.baseURI="http://localhost:3000/employees";
		RequestSpecification request=RestAssured.given();
		JSONObject json=new JSONObject();
		json.put("name", "Greg");
		json.put("salary", "3000");
		
		
		Response reponse=request.contentType(ContentType.JSON).accept(ContentType.JSON).body(json.toString()).post("/create");
		String ResponseBody=reponse.getBody().asString();
		System.out.println(ResponseBody);		
		int responseCode=reponse.getStatusCode();
		System.out.println(responseCode);
		JsonPath jsonPath=reponse.jsonPath();
		System.out.println("ID "+jsonPath.getByte("id"));
		Assert.assertEquals(responseCode, 201);	
	}

}
