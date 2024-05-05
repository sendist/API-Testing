package com.apitesting;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

public class DeleteUserTest {

    @Test
    public void tc01() {
        // DELETE operation without authorization header
        RestAssured.baseURI = "https://dummyapi.io/data/v1";

        given()
                .contentType(ContentType.JSON)
                .pathParam("id", "60d0fe4f5311236168a109ca")
                .when()
                .delete("/user/{id}")
                .then()
                .statusCode(403) // Expected status code is 403 Forbidden due to missing app-id
                .body("error", equalTo("APP_ID_MISSING"));
    }

    @Test
    public void tc02() {
        // DELETE operation with an invalid app-id header
        RestAssured.baseURI = "https://dummyapi.io/data/v1";

        given()
                .contentType(ContentType.JSON)
                .header("app-id", "appIdTidakValid")
                .pathParam("id", "60d0fe4f5311236168a109ca")
                .when()
                .delete("/user/{id}")
                .then()
                .statusCode(403) // Expected status code is 403 Forbidden due to invalid app-id
                .body("error", equalTo("APP_ID_NOT_EXIST"));
    }

    @Test
    public void tc03() {
        // DELETE operation with a valid app-id header and valid user ID
        RestAssured.baseURI = "https://dummyapi.io/data/v1";

        given()
                .contentType(ContentType.JSON)
                .header("app-id", "6627132f6cae03d7fddee77b")
                .pathParam("id", "60d0fe4f5311236168a109ca")
                .when()
                .delete("/user/{id}")
                .then()
                .statusCode(200) // Expected status code is 200 OK for successful deletion
                .body("id", equalTo("60d0fe4f5311236168a109ca"));
    }

    @Test
    public void tc04() {
        // DELETE operation with a valid app-id and a user ID that does not exist
        RestAssured.baseURI = "https://dummyapi.io/data/v1";

        given()
                .contentType(ContentType.JSON)
                .header("app-id", "6627132f6cae03d7fddee77b")
                .pathParam("id", "6627132f6cae03d7fddee77b")
                .when()
                .delete("/user/{id}")
                .then()
                .statusCode(404) // Expected status code is 404 Not Found when the resource does not exist
                .body("error", equalTo("RESOURCE_NOT_FOUND"));
    }
}
