package test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {

    //
	//@Test
	@Test(dataProvider="BooksData")
    //public void addBook() {
	//To use the data provider we need to declare 
		public void addBook(String isbn,String aisle) {
        RestAssured.baseURI = "http://216.10.245.166";

     String response=   given()
                .header("Content-Type", "application/json")
               // .body("{ \"name\":\"Learn API\" }") - as we added payload static
                //body(payload.Addbook()) - to control data from the test we doing the below changes adding string arguments
               // .body(payload.Addbook("adsfs","6464"))
                .body(payload.Addbook(isbn,aisle))
        .when()
                .post("/Library/Addbook.php")
        .then()
        //logging all
        .log().all().
                assertThat().statusCode(200)
                .extract().response().asString();
     //passing the string response
    JsonPath js= ReUsableMethods.rawToJson(response);
    //Storing in variable id
  String id=  js.get("ID");
System.out.println(id);

    }
    
    
	@DataProvider(name="BooksData")

	public Object[][]  getData()

	{

	//array=collection of elements

	//multidimensional array= collection of arrays

		return new Object[][] {
		    {"ojfwty", "9363"},
		    {"cwetee", "4253"},
		    {"ckmfet", "533"}
		};

	}
    
}
