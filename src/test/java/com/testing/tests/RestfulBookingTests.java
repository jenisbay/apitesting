package com.testing.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.testing.utils.FileUtils.loadSchema;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsd;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class RestfulBookingTests {

    @BeforeMethod
    public void setup() {
        RestAssured.baseURI = "http://localhost:3001";
    }

    @AfterMethod
    public void tearDown() {
        RestAssured.reset();
    }

    @Test
    public void testGetBookingIds() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/booking")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body(matchesJsonSchema(loadSchema("booker/booking_ids.json")));
    }

    @Test(dataProvider = "provideContentTypes")
    public void testGetBookingById(String contentType) {
        RestAssured.given()
                .pathParam("bookingId", 1)
                .accept(contentType)
                .when()
                .get("/booking/{bookingId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(contentType.equals(ContentType.JSON.toString()) ? contentType : ContentType.HTML.toString())
                .body(contentType.equals(ContentType.JSON.toString()) ?
                        matchesJsonSchema(loadSchema("booker/booking.json")) :
                        matchesXsd(loadSchema("booker/booking.xsd"))
                );
    }

    @Test(dataProvider = "provideBooking")
    public void testCreateBooking(HashMap<String, Object> payload) {

        RestAssured.given()
                .body(payload)
                .accept(ContentType.JSON.toString())
                .contentType(ContentType.JSON.toString())
                .when()
                .post("/booking")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON.toString())
                .body(matchesJsonSchema(loadSchema("booker/create_booking.json")));
    }

    @DataProvider
    public Object[][] provideContentTypes() {
        return new Object[][]{
                {ContentType.JSON.toString()},
                {ContentType.XML.toString()},
        };
    }

    @DataProvider
    public Object[] provideBooking() {

        HashMap<String, Object> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");

        HashMap<String, Object> payload = new HashMap<>();
        payload.put("firstname", "Jim");
        payload.put("lastname", "Brown");
        payload.put("totalprice", 111);
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "Breakfast");

        return new Object[]{payload};
    }

}
