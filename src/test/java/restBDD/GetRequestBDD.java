package restBDD;

import java.util.List;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetRequestBDD {
	//Get Request
	@Test
	public void test1()
	{
		RestAssured.given().baseUri("http://localhost:3000/employees").when().get().then().log().all();
	}
	
	//Get Request with Parameters
	@Test
	public void test2()
	{
		//RestAssured.given().baseUri("http://localhost:3000/employees").when().get("/12").then().log().status().statusCode(200);
		//RestAssured.given().baseUri("http://localhost:3000/employees").when().get("/12").then().log().all();
		RestAssured.given().baseUri("http://localhost:3000/employees").queryParam("id", "15").get().then().log().body().statusCode(200).body("[0].name", Matchers.equalTo("Tom"));
	}
	@Test
	public void test3()
	{
		Response response=RestAssured.given().baseUri("http://localhost:3000/employees").when().get();
		JsonPath jPath=response.jsonPath();
		List<String> names=jPath.get("name");
		
		System.out.println(names.get(0));
		Assert.assertEquals(names.get(0), "Pankaj");
	//	Assert.assertEquals(response.getStatusCode(), 200);
		}
	
	
	
	

}
