package testApi;

import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class TestApi {

	@BeforeClass
	public static void setUp() {
		RestAssured.baseURI = "http://localhost:3001";
	}

	@Test
	public void deverRetornarProjects() {
		RestAssured.given().when().get("/projects").then().statusCode(200);

	}

	@Test
	public void naoDeverRetornarProjects() {
		RestAssured.given().when().get("/").then().statusCode(404);

	}

	@Test
	public void deveAdicionarProjeto() {
		RestAssured.given().body("{ \"id\":\"1\", \"title\":\"titulo 1\",\"task\":[\"testes\", \"testes\"] }")
				.contentType(ContentType.JSON).when().post("/projects").then().statusCode(200);

	}

	@Test
	public void deveAlterarProjeto() {
		RestAssured.given()
				.body("{ \"id\":\"1\", \"title\":\"testes de alteração\",\"task\":[\"testes\", \"testes\"] }")
				.contentType(ContentType.JSON).when().put("/projects/1").then().statusCode(200);

	}

	@Test
	public void naoDeveAlterarProjeto() {
		RestAssured.given()
				.body("{ \"id\":\"1\", \"title\":\"testes de alteração\",\"task\":[\"testes\", \"testes\"] }")
				.contentType(ContentType.JSON).when().put("/projects/800").then().statusCode(400)
				.body("message", CoreMatchers.is("O projeto não existe"));

	}

	@Test
	public void deveDeletarProjeto() {
		RestAssured.given().when().delete("/projects/1").then().statusCode(200);

	}

}
