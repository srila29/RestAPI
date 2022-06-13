package restAPI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequestWithDataFile {
	@Test
	public void test() throws IOException
	{
		RestAssured.baseURI="http://localhost:3000/employees";
		RequestSpecification request=RestAssured.given();
		
		byte[] dataBytes=Files.readAllBytes(Paths.get("Data.json"));
		Response reponse=request.contentType(ContentType.JSON).accept(ContentType.JSON).body(dataBytes).post("/create");
		String ResponseBody=reponse.getBody().asString();
		System.out.println(ResponseBody);		
		int responseCode=reponse.getStatusCode();
		System.out.println(responseCode);
		JsonPath jsonPath=reponse.jsonPath();
		System.out.println("ID "+jsonPath.getByte("id"));
		Assert.assertEquals(responseCode, 201);
	}

}
