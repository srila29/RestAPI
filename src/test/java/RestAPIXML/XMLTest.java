package RestAPIXML;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.internal.path.xml.NodeChildrenImpl;
import io.restassured.response.Response;

public class XMLTest {
	@Test
	public void test1()
	{
		RestAssured.given().baseUri("https://chercher.tech/sample/api/books.xml").when().get().then().log().all();
	}
	
	@Test
	public void test2()
	{
		Response response=RestAssured.given().baseUri("https://chercher.tech/sample/api/books.xml").when().get();
		NodeChildrenImpl books_title=response.then().extract().path("bookstore.book.title");
		
				System.out.println("All the books are : " +books_title.toString());
				System.out.println("First book is "+books_title.get(0).toString());
				System.out.println("Then language of first book is "+books_title.get(0).getAttribute("lang"));
				NodeChildrenImpl books_author=response.then().extract().path("bookstore.book.author");
				System.out.println("All the book authors are : " +books_author.toString());
				NodeChildrenImpl books_prices=response.then().extract().path("bookstore.book.price");
				System.out.println("All the prices are: "+books_prices.toString());
				System.out.println("First book price is : "+books_prices.get(0).children().get("paperback"));
				for(String b:books_title)
				{
					System.out.println(b);
				}
	}

}
