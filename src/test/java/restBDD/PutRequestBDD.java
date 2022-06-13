package restBDD;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PutRequestBDD {
	
	@Test
	public void test1()
	{
		Map<String,Object> mapObj=new HashMap<String,Object>();
		mapObj.put("name", "Jammy");
		mapObj.put("salary", "5500");
		RestAssured.given()
		.baseUri("http://localhost:3000/employees")
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(mapObj)
		.when()
		.put("15")
		.then()
		.log()
		.body()
		.statusCode(200)
		.body("name",Matchers.equalTo("Jammy"))
		.body("salary",Matchers.equalTo("5500"));
		
			}
	

}
