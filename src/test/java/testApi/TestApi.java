package testApi;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;

public class TestApi {
	
	@BeforeClass
	public static void setUp() {
		RestAssured.baseURI = "http://localhost:3001";
	}
	
	@Test
	public void deverRetornarProjects() {
		RestAssured.given()
		.when()
		     .get("/projects")
		.then()
		     .statusCode(200)
		     .log().body();
		
	}
	
}
