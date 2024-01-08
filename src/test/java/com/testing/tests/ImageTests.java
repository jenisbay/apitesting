package com.testing.tests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

public class ImageTests {

    private final String BASE_URL = "https://image.dummyjson.com";

    @Test
    public void testGenerateSquareImage() {
        RestAssured.given()
                .baseUri(BASE_URL)
                .pathParam("side", 150)
                .when()
                .get("/{side}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType("image/png");
    }

    @Test
    public void testGenerateImageWithCustomWidthAndHeight() {
        RestAssured.given()
                .baseUri(BASE_URL)
                .pathParam("width", 200)
                .pathParam("height", 150)
                .when()
                .get("/{width}x{height}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType("image/png");
    }

    @Test
    public void testGenerateImageWithCustomText() {
        RestAssured.given()
                .baseUri(BASE_URL)
                .pathParam("width", 400)
                .pathParam("height", 200)
                .pathParam("text", "Hello World!")
                .pathParam("bgColor", "008080")
                .pathParam("textColor", "ffffff")
                .when()
                .get("/{width}x{height}/{bgColor}/{textColor}?text={text}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType("image/png");
    }

    @Test
    public void testGenerateImageWithCustomBackgroundColor() {
        RestAssured.given()
                .baseUri(BASE_URL)
                .pathParam("width", 400)
                .pathParam("height", 200)
                .pathParam("bgColor", "008080")
                .when()
                .get("/{width}x{height}/{bgColor}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType("image/png");
    }

    @Test
    public void testGenerateImageWithCustomBackgroundAndTextColor() {
        RestAssured.given()
                .baseUri(BASE_URL)
                .pathParam("width", 400)
                .pathParam("height", 200)
                .pathParam("bgColor", "008080")
                .pathParam("textColor", "ffffff")
                .when()
                .get("/{width}x{height}/{bgColor}/{textColor}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType("image/png");
    }

    @Test
    public void testGenerateImageWithDifferentImageFormats() {
        RestAssured.given()
                .baseUri(BASE_URL)
                .pathParam("size", 100)
                .pathParam("type", "webp")
                .when()
                .get("/{size}/?type={type}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType("image/webp");
    }

    @Test
    public void testGenerateImageWithCustomFontFamily() {
        RestAssured.given()
                .baseUri(BASE_URL)
                .pathParam("width", 400)
                .pathParam("height", 200)
                .pathParam("text", "Hello World!")
                .pathParam("bgColor", "008080")
                .pathParam("textColor", "ffffff")
                .pathParam("fontFamily", "cookie")
                .when()
                .get("/{width}x{height}/{bgColor}/{textColor}?text={text}!&fontFamily={fontFamily}}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType("image/png");
    }

    @Test
    public void testGenerateImageWithCustomFontSize() {
        RestAssured.given()
                .baseUri(BASE_URL)
                .pathParam("width", 400)
                .pathParam("height", 200)
                .pathParam("text", "Hello World!")
                .pathParam("bgColor", "008080")
                .pathParam("textColor", "ffffff")
                .pathParam("fontSize", "cookie")
                .when()
                .get("/{width}x{height}/{bgColor}/{textColor}?text={text}!&fontSize={fontSize}}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType("image/png");

    }

}
