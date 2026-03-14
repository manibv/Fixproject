package com.fixproject.utils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojo.Api;
import pojo.GetCourse;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

public class oAuthTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//declare array of strings- Building expected titles so that we can compare later form json response with an assertion
		String[] courseTitles ={"Selenium Webdriver Java","Cypress","Protractot"};
		
  String response=   given()
.formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
.formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
.formParam("grant_type","client_credentials")
.formParam("scope","trust")
.when().log().all()
.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
  
  System.out.println(response);
  JsonPath js= new JsonPath(response);
 String accessToken= js.getString("access_token");
 
 //String response2=
 //Using Getcourse object gc
 GetCourse gc=
 given()
 .queryParam("access_token",accessToken)
 .when().log().all()
// .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();
 // As we are using POJO classes deserialization-> below
 .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);
 //System.out.println(response2);
 System.out.println(gc.getLinkedIn());
 System.out.println(gc.getInstructor());
 //getter method-getcourses then get api
 //There are list of APIs- getting first one
 System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
  //Scan the API and get soupUI web services testing course price -in the List of API courses
 List<Api> apiCourses=gc.getCourses().getApi();
 //iteration
 for(int i=0;i<apiCourses.size();i++)
 {
  if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
		  {
	  System.out.println(apiCourses.get(i).getPrice());
		  }
	}
//Get all Courses names of webAutomation course list
 //For a dynamic arrays(if not sure about the size) we use Arraylist and create object -as all are string adding the string
 ArrayList<String> a=new ArrayList<String>();
 List<pojo.WebAutomation> w=gc.getCourses().getWebAutomation();
 
 for(int j=0;j<w.size();j++)
 {
	// System.out.println(w.get(j).getCourseTitle());
	 
	a.add(w.get(j).getCourseTitle());
 }
 //For comparing Array and Array list- for that converting courseTitles[] Array to Arraylist
 List<String> expectedList= Arrays.asList(courseTitles);
 //TestNG assertion-to assert
 Assert.assertTrue(a.equals(expectedList));
 
 }
	}
//To check the course titles with expected course titles- for this we need to declare array of strings..above.
