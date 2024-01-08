package com.testing.tests;

import com.testing.utils.FileUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.hamcrest.Matchers.*;

public class RestApiTests {

    @BeforeMethod
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @AfterMethod
    public void tearDown() {
        RestAssured.reset();
    }

    @Test
    public void testGetPosts() {
        RestAssured.given()
                .when()
                .get("/posts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body(JsonSchemaValidator.matchesJsonSchema(FileUtils.readFileAsStream("schemas/post/posts_schema.json")));
    }

    @Test
    public void testGetPostById() {
        RestAssured.given()
                .pathParam("postId", 99)
                .when()
                .get("/posts/{postId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body(JsonSchemaValidator.matchesJsonSchema(FileUtils.readFileAsStream("schemas/post/post_schema.json")))
                .body("userId", equalTo(10))
                .body("id", equalTo(99))
                .body("body", notNullValue());
    }

    @Test
    public void testGetPostByIdThatDoesNotExist() {
        RestAssured.given()
                .pathParam("postId", 150)
                .when()
                .get("/posts/{postId}")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .contentType(ContentType.JSON)
                .body(JsonSchemaValidator.matchesJsonSchema(FileUtils.readFileAsStream("schemas/empty_schema.json")));
    }

    @Test
    public void testAddNewPost() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("title", "New Title");
        body.put("body", "New Body");
        body.put("userId", 1);
        body.put("id", 101);

        RestAssured.given()
                .body(body)
                .contentType(ContentType.JSON)
                .when()
                .post("/posts")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("title", equalTo("New Title"))
                .body("body", equalTo("New Body"))
                .body("userId", equalTo(1))
                .body("id", equalTo(101));
    }

    @Test
    public void testGetUsers() {
        RestAssured.given()
                .when()
                .get("/users")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body(JsonSchemaValidator.matchesJsonSchema(FileUtils.readFileAsStream("schemas/user/users.json")));
    }

    @Test
    public void testGetUserById() {
        RestAssured.given()
                .pathParam("id", 5)
                .when()
                .get("/users/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body(JsonSchemaValidator.matchesJsonSchema(FileUtils.readFileAsStream("schemas/user/user.json")))
                .body("id", equalTo(5))
                .body("address.street", equalTo("Skiles Walks"));
    }

}
