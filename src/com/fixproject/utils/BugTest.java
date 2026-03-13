package com.fixproject.utils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;


public class BugTest {

	private static final String CreateIssueResponse = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://bethina.atlassian.net/";
		String createIssueResponse =given().header("Content-Type","application/json")
.header("Authorization","Basic YmV0aGluYS52bUBnbWFpbC5jb206QVRBVFQzeEZmR0Ywd0xGX1VSTEpmeV9WZ1ZjeHJzMXhYUkRMTnlIWjlzZkFmczBUR2FyOENiVnZHc3FPY2tEVXA4RU5FNFdjOTlBSHc4MGlTWlk3Ym5KdmI3ZkFsdVZ3c3hKamJDM3NzOU5UcS1JYjd4a3JyQjlCc1pWa2pBejd4SEFKWFFMdWd6b1k1dnVKSUdPRTM0cFIzTjBOUl82QlBrOUY2YTRqanBkN09ZRThWX01MUUVRPUEwRDEwMUYy")
.body("{\r\n"
		+ "  \"fields\": {\r\n"
		+ "    \"project\": {\r\n"
		+ "      \"key\": \"QAA\"\r\n"
		+ "    },\r\n"
		+ "    \"summary\": \"Testing eclipse Jira issue creation\",\r\n"
		+ "    \"description\": {\r\n"
		+ "      \"type\": \"doc\",\r\n"
		+ "      \"version\": 1,\r\n"
		+ "      \"content\": [\r\n"
		+ "        {\r\n"
		+ "          \"type\": \"paragraph\",\r\n"
		+ "          \"content\": [\r\n"
		+ "            {\r\n"
		+ "              \"type\": \"text\",\r\n"
		+ "              \"text\": \"just check if Eclipse is coming through\"\r\n"
		+ "            }\r\n"
		+ "          ]\r\n"
		+ "        }\r\n"
		+ "      ]\r\n"
		+ "    },\r\n"
		+ "    \"issuetype\": {\r\n"
		+ "      \"name\": \"Task\"\r\n"
		+ "    }\r\n"
		+ "  }\r\n"
		+ "}")
.log().all()
.post("rest/api/3/issue").then().log().all().statusCode(201)
.extract().response().asString();
JsonPath js = new JsonPath(createIssueResponse);
String issueID = js.getString("id");
System.out.println(issueID);
given()			.pathParam("key", issueID)			
.header("X-Atlassian-Token","no-check").
header("Authorization","Basic YmV0aGluYS52bUBnbWFpbC5jb206QVRBVFQzeEZmR0Ywd0xGX1VSTEpmeV9WZ1ZjeHJzMXhYUkRMTnlIWjlzZkFmczBUR2FyOENiVnZHc3FPY2tEVXA4RU5FNFdjOTlBSHc4MGlTWlk3Ym5KdmI3ZkFsdVZ3c3hKamJDM3NzOU5UcS1JYjd4a3JyQjlCc1pWa2pBejd4SEFKWFFMdWd6b1k1dnVKSUdPRTM0cFIzTjBOUl82QlBrOUY2YTRqanBkN09ZRThWX01MUUVRPUEwRDEwMUYy\"")			
.multiPart("file",new File("C:\\Users\\mani\\OneDrive\\Pictures\\Screenshots\\Screenshot 2025-10-27 115632.png"))
.log().all()			
.post("rest/api/3/issue/{key}/attachments")
.then().log().all().assertThat().statusCode(200);
	}

}
