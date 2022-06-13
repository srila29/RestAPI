package APIChaining;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndToEndTest {
	Response response;
	int responseCode;
	@Test
	public void test1()
	{
		//Get all the employees
		response=GetMethodAll();
		responseCode=response.getStatusCode();
		List<String> name;
		Assert.assertEquals(responseCode, 200);	
		
		
		//Create new Employee
		response=PostMethod("Anita","4000");
		responseCode=response.getStatusCode();
		Assert.assertEquals(responseCode, 201);	
		JsonPath jsonPath=response.jsonPath();
		int ID=jsonPath.getByte("id");
		System.out.println("ID "+ID+" Employee created successfully");
		
		
		
		//Get the new employee details using ID returned in POST method
		response=GetMethod(ID);
		jsonPath=response.jsonPath();
		name=jsonPath.get("name");
		String responseBody=response.getBody().asString();
	   	responseCode=response.getStatusCode();
		System.out.println(responseBody);
		for(int i=0;i<name.size();i++)
		{
			System.out.println("The Employee created is "+ name.get(i));
		}
		Assert.assertEquals(responseCode, 200);	
		Assert.assertTrue(name.get(0).contains("Anita"));
		System.out.println("Employee created and Validated");
		
		
		//Update the newly created employee name using PUT method and ID
		response=PutMethod(ID,"Jammy","5000");
		responseCode=response.getStatusCode();
		Assert.assertEquals(responseCode, 200);
		System.out.println("The employee updated");
		
		//Get the updated employee details to check if the name is updated
				response=GetMethod(ID);
				Assert.assertEquals(responseCode, 200);	
				System.out.println("Updation check - Get request");
				jsonPath=response.jsonPath();
				name=jsonPath.get("name");
				responseBody=response.getBody().asString();
			   	responseCode=response.getStatusCode();
				System.out.println(responseBody);
				Assert.assertEquals(responseCode, 200);	
				Assert.assertTrue(name.get(0).contains("Jammy"));
				System.out.println("The updated name checked - Jammy");				
				
				
		//Delete the newly created employee using Delete method
		response=DeleteMethod(ID);
		responseCode=response.getStatusCode();
		Assert.assertEquals(responseCode, 200);	
		System.out.println("The new employee deleted");
		
		//Get All employee details to validate if the newly created ID is deleted
		response=GetMethodAll();
		responseCode=response.getStatusCode();
		Assert.assertEquals(responseCode, 200);
		List<Integer> list_ID=response.jsonPath().get("id");
		Assert.assertFalse(list_ID.get(0).equals(ID));
		System.out.println("The new employee deletion is validated");
		
			}
	public Response GetMethodAll()
	{
		RestAssured.baseURI="http://localhost:3000/employees";
		RequestSpecification request=RestAssured.given();
		Response response=request.get();
		return response;
	}
	
	public Response PostMethod(String name,String salary)
	{
		RestAssured.baseURI="http://localhost:3000/employees";
		RequestSpecification request=RestAssured.given();
		Map<String,Object> mapObj=new HashMap<String,Object>();
		mapObj.put("name", name);
		mapObj.put("salary", salary);			
		Response response=request.contentType(ContentType.JSON).accept(ContentType.JSON).body(mapObj).post("/create");
		return response;
	}
	public Response GetMethod(int ID)
	{
		RestAssured.baseURI="http://localhost:3000/employees";
		RequestSpecification request=RestAssured.given();
		Response response=request.param("id", ID).get();
		return response;	
			}
	public Response PutMethod(int ID,String name,String salary)
	{
		RestAssured.baseURI="http://localhost:3000/employees";
		RequestSpecification request=RestAssured.given();
		Map<String,Object> mapObj=new HashMap<String,Object>();
		mapObj.put("name", name);
		mapObj.put("salary", salary);
		Response response=request.contentType(ContentType.JSON).accept(ContentType.JSON).body(mapObj).put("/"+ID);
		return response;
	}
	public Response DeleteMethod(int ID)
		{
		RestAssured.baseURI="http://localhost:3000/employees";
		RequestSpecification request=RestAssured.given();
		Response response=request.delete("/"+ID);
	return response;
	}

}
