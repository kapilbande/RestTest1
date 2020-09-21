package package2;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetTestsNG {

// TestNG assert
@Test
public void getListOfUsersAssertBody() {
	RestAssured.baseURI="https://reqres.in";
	RestAssured.basePath="/api/users";
	String response = given().log().all()
			.queryParam("page", 2)
			.when().get()
			.then().assertThat().statusCode(200).extract().response().asString();
	JsonPath js=new JsonPath(response); //for parsing Json
	int total=js.get("total");
	Assert.assertEquals(total, 12);
}

// TestNG assert
@Test
public void getListOfUsersAssertBody1() {
	RestAssured.baseURI="https://reqres.in";
	RestAssured.basePath="/api/users";
	String response = given().log().all()
			.queryParam("page", 2)
			.when().get()
			.then().assertThat().statusCode(200).extract().response().asString();
	System.out.println(response);
	JsonPath js=new JsonPath(response); //for parsing Json
	String id=js.getString("data.id");
	String expectedId = "7, 8, 9, 10, 11, 12";
	Assert.assertTrue(id.contains(expectedId));
}

//TestNG assert
@Test
public void getListOfUsersFirstIdValidation() {
	RestAssured.baseURI="https://reqres.in";
	RestAssured.basePath="/api/users";
	String response = given().log().all()
			.queryParam("page", 2)
			.when().get()
			.then().assertThat().statusCode(200).extract().response().asString();
	System.out.println(response);
	JsonPath js=new JsonPath(response); //for parsing Json
	String id=js.getString("data[0].id");
	String expectedId = "7";
	Assert.assertTrue(id.contains(expectedId));
}

@Test
public void getListOfUsersJsonPath() {
	RestAssured.baseURI="https://reqres.in";
	RestAssured.basePath="/api/users";
	RequestSpecification reqSpec=RestAssured.given();
	//reqSpec.contentType(ContentType.JSON);
	reqSpec.headers("content-type","application/json");
	reqSpec.queryParam("page", 1);
	//RestAssured.get().then().body("data[0].id", equalTo(1));
	reqSpec.get().then().body("data.id", hasItems(1,2,3,4,5,6));
		/*
		 * JsonPath jp=new JsonPath(respInStr); System.out.println(jp.get("data.id"));
		 */
}

@Test
public void getListOfUsersRootPath() {
	RestAssured.baseURI="https://reqres.in";
	RestAssured.basePath="/api/users";
	RestAssured.rootPath="data";
	RequestSpecification reqSpec=RestAssured.given();
	//reqSpec.contentType(ContentType.JSON);
	reqSpec.headers("content-type","application/json");
	reqSpec.queryParam("page", 2);
	//RestAssured.get().then().body("data[0].id", equalTo(1));
	reqSpec.get().then().body("id", hasItems(7)); //equal to body("data.id", hasItems(7))
}


}
