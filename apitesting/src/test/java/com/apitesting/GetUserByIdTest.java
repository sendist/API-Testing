package com.apitesting;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

public class GetUserByIdTest {

    @Test
    public void tc01() {
        // GET operation without authorization header
        RestAssured.baseURI = "https://dummyapi.io/data/v1";

        given()
                .contentType(ContentType.JSON)
                .pathParam("id", "60d0fe4f5311236168a109ca")
                .when()
                .put("/user/{id}")
                .then()
                .statusCode(403) // Periksa bahwa status code adalah 403 Forbidden
                .body("error", equalTo("APP_ID_MISSING"));
    }

    @Test
    public void tc02() {
        // GET operation with an invalid app-id header
        RestAssured.baseURI = "https://dummyapi.io/data/v1";

        given()
                .contentType(ContentType.JSON)
                .header("app-id", "60d0fe4f5311236168a109ca")
                .pathParam("id", "60d0fe4f5311236168a109ca")
                .when()
                .put("/user/{id}")
                .then()
                .statusCode(403) // Periksa bahwa status code adalah 403 Forbidden
                .body("error", equalTo("APP_ID_NOT_EXIST"));
    }

    @Test
    public void tc03() {
        // GET operation with a valid app-id header and valid user ID
        RestAssured.baseURI = "https://dummyapi.io/data/v1";

        given()
                .contentType(ContentType.JSON)
                .header("app-id", "6627132f6cae03d7fddee77b")
                .pathParam("id", "60d0fe4f5311236168a109ca")
                .when()
                .put("/user/{id}")
                .then()
                .statusCode(200) // Periksa bahwa status code adalah 403 Forbidden
                .body("id", equalTo("60d0fe4f5311236168a109ca"));
    }

    @Test
    public void tc04() {
        // GET operation with a valid app-id and a user ID that does not exist
        RestAssured.baseURI = "https://dummyapi.io/data/v1";

        given()
                .contentType(ContentType.JSON)
                .header("app-id", "6627132f6cae03d7fddee77b")
                .pathParam("id", "6627132f6cae03d7fddee77b")
                .when()
                .put("/user/{id}")
                .then()
                .statusCode(404) // Periksa bahwa status code adalah 403 Forbidden
                .body("error", equalTo("RESOURCE_NOT_FOUND"));
    }
}
