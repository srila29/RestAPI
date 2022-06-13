package GitHub;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndToEndGitHubTest {
	
	@Test
	public void test1() throws Exception
	{
		RestAssured.baseURI="https://api.github.com/users/srila29/repos";
		RequestSpecification request=RestAssured.given();
		Response response=request.accept(ContentType.JSON).get();
		Thread.sleep(5000);
		String responseBody=response.getBody().asString();
		System.out.println(responseBody);
		int responseCode=response.getStatusCode();
	   Assert.assertEquals(responseCode, 200);	
	   
	   		
	}
	
	@Test
	public void test2() throws IOException
	{
		RestAssured.baseURI="https://api.github.com/user/repos";
		RequestSpecification request=RestAssured.given();		
		byte[] dataBytes=Files.readAllBytes(Paths.get("Data1.json"));
		Response reponse=request.contentType(ContentType.JSON).accept(ContentType.JSON).auth().oauth2("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").body(dataBytes).post();
		String ResponseBody=reponse.getBody().asString();
		System.out.println(ResponseBody);		
		int responseCode=reponse.getStatusCode();
		Assert.assertEquals(responseCode, 201);	
	}

}
