
package restAPI;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequest {
	@Test
	public void test1()
	{
		RestAssured.baseURI="http://localhost:3000/employees";
		RequestSpecification request=RestAssured.given();
		Response reponse=request.contentType(ContentType.JSON).accept(ContentType.JSON).body("{\r\n"
				+ "            \"name\": \"Rob\",\r\n"
				+ "            \"salary\": \"5000\"\r\n"
				+ "    }").post("/create");
		String ResponseBody=reponse.getBody().asString();
		System.out.println(ResponseBody);		
		int responseCode=reponse.getStatusCode();
		System.out.println(responseCode);
		JsonPath jsonPath=reponse.jsonPath();
		System.out.println("ID "+jsonPath.getByte("id"));
		Assert.assertEquals(responseCode, 201);
	  
	}

}