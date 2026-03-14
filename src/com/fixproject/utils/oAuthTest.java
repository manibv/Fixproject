package com.fixproject.utils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojo.Api;
import pojo.GetCourse;

import static io.restassured.RestAssured.*;

import java.util.List;

public class oAuthTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
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
//Get Courses names of webAutomation course list
 List<pojo.WebAutomation> w=gc.getCourses().getWebAutomation();
 for(int j=0;j<w.size();j++)
 {
	 System.out.println(w.get(j).getCourseTitle());
 }
 }
	}
