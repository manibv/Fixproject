package files;
import io.restassured.RestAssured;

import org.testng.AssertJUnit;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
public class GraphQLScript {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Query 
		//int characterId = 14
		
		String response = given().log().all().header("content-type","application/json")
				
				// body("{\"query\":\"mutation($locationName:String!,$CharacterName: String!,$episodeName: String!)\\n{\\n  createLocation(location :{name:$locationName,type:\\\"Nzone\\\",dimension:\\\"999\\\"})\\n  {\\n    id\\n  }\\n  createCharacter(character:{name:$CharacterName,type:\\\"Macho\\\",status:\\\"alive\\\",species:\\\"fantasy\\\",gender:\\\"male\\\",image:\\\"png\\\",originId: 29847,locationId:29847})\\n{\\n  id\\n}\\n  createEpisode(episode:{name:$episodeName,air_date:\\\"1990 March\\\",episode:\\\"Disney\\\"})\\n  {\\n    id\\n  }\\n  \\n  deleteLocations(locationIds:[29849,11])\\n  {\\n    locationsDeleted\\n  }\\n}\\n\\n\",\"variables\":{\"locationName\":\"SouthAfrica\",\"CharacterName\":\"Robin Hood \",\"episodeName\":\"Manifest\"}}")
		.body("{\"query\":\"mutation($locationName:String!,$CharacterName: String!,$episodeName: String!)\\n{\\n  createLocation(location :{name:$locationName,type:\\\"Nzone\\\",dimension:\\\"999\\\"})\\n  {\\n    id\\n  }\\n  createCharacter(character:{name:$CharacterName,type:\\\"Macho\\\",status:\\\"alive\\\",species:\\\"fantasy\\\",gender:\\\"male\\\",image:\\\"png\\\",originId: 29847,locationId:29847})\\n{\\n  id\\n}\\n  createEpisode(episode:{name:$episodeName,air_date:\\\"1990 March\\\",episode:\\\"Disney\\\"})\\n  {\\n    id\\n  }\\n  \\n  deleteLocations(locationIds:[29849,11])\\n  {\\n    locationsDeleted\\n  }\\n}\\n\\n\",\"variables\":{\"locationName\":\"SouthAfrica\",\"CharacterName\":\"Robin Hood \",\"episodeName\":\"Manifest\"}}")
.when().post("https://www.rahulshettyacademy.com")
.then().extract().response().asString();
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String characterName =js.getString("data.character.name");
		AssertJUnit.assertEquals(characterName, "Mani");
		
		//Mutations
		//String newCharacter =""
String mutationResponse = given().log().all().header("content-type","application/json")
				
				
		.body("{\"query\":\"mutation($locationName:String!,$CharacterName: String!,$episodeName: String!)\\n{\\n  createLocation(location :{name:$locationName,type:\\\"Nzone\\\",dimension:\\\"999\\\"})\\n  {\\n    id\\n  }\\n  createCharacter(character:{name:$CharacterName,type:\\\"Macho\\\",status:\\\"alive\\\",species:\\\"fantasy\\\",gender:\\\"male\\\",image:\\\"png\\\",originId: 29847,locationId:29847})\\n{\\n  id\\n}\\n  createEpisode(episode:{name:$episodeName,air_date:\\\"1990 March\\\",episode:\\\"Disney\\\"})\\n  {\\n    id\\n  }\\n  \\n  deleteLocations(locationIds:[29849,11])\\n  {\\n    locationsDeleted\\n  }\\n}\\n\\n\",\"variables\":{\"locationName\":\"SouthAfrica\",\"CharacterName\":\"Robin Hood \",\"episodeName\":\"Manifest\"}}")
.when().post("https://www.rahulshettyacademy.com")
.then().extract().response().asString();
 System.out.println(mutationResponse);
		
		
		
		
		
	}

}
