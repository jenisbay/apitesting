package com.testing.tests;

import com.testing.configuration.ConfigProvider;
import com.testing.models.carts.AllCartsBody;
import com.testing.models.carts.SingleCartBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.lessThan;

public class CartsTests {

    private final String BASE_URL = ConfigProvider.getBaseUrl();

    @Test
    public void testGetAllCarts() {

        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .when()
                .get("/carts")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .extract().response();

        AllCartsBody body = response.body().as(AllCartsBody.class);
        Assert.assertEquals(body.getCarts().size(), body.getTotal());
    }

    @Test
    public void testGetSingleCart() {

        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .when()
                .get("/carts/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .extract().response();

        SingleCartBody body = response.body().as(SingleCartBody.class);
        Assert.assertEquals(body.getProducts().size(), body.getTotalProducts());
    }

    @Test
    public void testGetCartsOfUser() {

        RestAssured.given()
                .baseUri(BASE_URL)
                .pathParam("userId", 5)
                .when()
                .get("/carts/user/{userId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .header("vary", "Accept-Encoding")
                .time(lessThan(4000L));
    }

    @Test
    public void testAddNewCart() {
        HashMap<String, Object> product_1 = new HashMap<>();
        product_1.put("id", 1);
        product_1.put("quantity", 1);

        HashMap<String, Object> product_2 = new HashMap<>();
        product_2.put("id", 1);
        product_2.put("quantity", 1);

        HashMap<String, Object> body = new HashMap<>();
        body.put("userId", 1);
        body.put("products", List.of(product_1, product_2));

        RestAssured.given()
                .baseUri(BASE_URL)
                .body(body)
                .contentType(ContentType.JSON)
                .when()
                .post("/carts/add")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .time(lessThan(4000L));
    }

    @Test
    public void testUpdateCart() {
        HashMap<String, Object> product = new HashMap<>();
        product.put("id", 1);
        product.put("quantity", 1);

        HashMap<String, Object> body = new HashMap<>();
        body.put("merge", true);
        body.put("products", List.of(product));

        RestAssured.given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .pathParam("cartId", 1)
                .body(body)
                .when()
                .put("/carts/{cartId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .time(lessThan(4000L));
    }

    @Test
    public void testDeleteCart() {
        RestAssured.given()
                .baseUri(BASE_URL)
                .pathParam("cartId", 1)
                .when()
                .delete("/carts/{cartId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .time(lessThan(4000L));
    }

}
