package com.fixproject.utils;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;


import org.testng.Assert;

import files.ReUsableMethods;
import files.payload;

public class Basics {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
// validate if Add Place API is workimg as expected 
		//Add place-> Update Place with New Address -> Get Place to validate if New address is present in response
		
		//given - all input details 
		//when - Submit the API -resource,http method
		//Then - validate the response
		//Content of the file to string->Contents of file can convert into Byte--> Byte to string
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payload.AddPlace())
				//For using files
		//.body(Files.readAllBytes(Paths.get("C:\\Program Files\\Notepad++")))
				//.body(Files.readAllBytes(Paths.get("")))
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		//The Server header:
		.header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
//Is automatically sent by the web server

//Is part of Response Headers

//Cannot be set from client side

//Used sometimes for debugging

//Often hidden in production for security reasons
		
		System.out.println(response);
		JsonPath js=new JsonPath(response); //for parsing Json
		String placeId=js.getString("place_id");
		
		System.out.println(placeId);
		
		//Update Place
		String newAddress = "Summer Walk, Africa";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}").
		when().put("maps/api/place/update/json")
		//equalTO Hamcrest-matchers
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//Get Place
		
		String getPlaceResponse=	given().log().all().queryParam("key", "qaclick123")
			.queryParam("place_id",placeId)
			.when().get("maps/api/place/get/json")
			.then().assertThat().log().all().statusCode(200).extract().response().asString();
		JsonPath js1=ReUsableMethods.rawToJson(getPlaceResponse);
		String actualAddress =js1.getString("address");
		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress, "Summer Walk, Africa"); //this address is in correct should be "Summer Walk, Africa".
		
	
	
	
	
	
	
	
	

		
		
		
		
		
		
		
		
		
		
		
	}



	}


