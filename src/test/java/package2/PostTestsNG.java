package package2;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertNotNull;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utils.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class PostTestsNG {

@Test
public void loginSuccessTest() {
	RestAssured.baseURI="https://reqres.in";
	String response = given().log().all().header("Content-Type","application/json")
			.body(Payload.loginSuccess())
			.when().post("/api/login")
			.then().assertThat().statusCode(200).extract().response().asString();
	JsonPath js=new JsonPath(response); //for parsing Json
	String token=js.getString("token");
	Assert.assertNotNull(token);
}

@Test
public void createUser() {
	RestAssured.baseURI="https://reqres.in";
	String response = given().log().all().header("Content-Type","application/json")
			.body(Payload.createUser())
			.when().post("/api/users")
			.then().assertThat().statusCode(201).extract().response().asString();
	JsonPath js=new JsonPath(response); //for parsing Json
	String name=js.getString("name");
	Assert.assertEquals(name, "Brij");
}

}

