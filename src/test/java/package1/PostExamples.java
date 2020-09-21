package package1;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class PostExamples {
	
	@Test
	public void createUser() {
		given().contentType(ContentType.JSON).body("{\r\n" + 
				"    \"name\": \"Brij\",\r\n" + 
				"    \"job\": \"leader1\"\r\n" + 
				"}").when().post("https://reqres.in/api/users").then().assertThat().statusCode(201);
	}

}
