package com.apitesting;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

public class UpdateTest {
    
    @Test
    public void tc01() {
        // Operasi tidak punya authorization

        RestAssured.baseURI = "https://dummyapi.io/data/v1";
        String requestBody = "{\n" +
                             "  \"title\": \"mr\",\n" +
                             "  \"firstName\": \"Eza\"\n" +
                             "}";

        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .pathParam("id", "662e8f911846fb0eced57ceb")
        .when()
            .put("/user/{id}")
        .then()
            .statusCode(403) // Periksa bahwa status code adalah 403 Forbidden
            .body("error", equalTo("APP_ID_MISSING")); // Periksa bahwa response body mengandung error "APP_ID_MISSING"
    }

    @Test
    public void tc06() {
        // Operasi memiliki valid authorization dan menggunakan user id yang sudah ada di sistem
        RestAssured.baseURI = "https://dummyapi.io/data/v1";
        String requestBody = "{\n" +
                             "  \"lastName\": \"Sananta\"\n" +
                             "}";

        given()
            .contentType(ContentType.JSON)
            .header("app-id", "662e7da1bb70a751fa259763")
            .body(requestBody)
            .pathParam("id", "662e8f911846fb0eced57ceb")
        .when()
            .put("/user/{id}")
        .then()
            .statusCode(200) // Periksa bahwa status code adalah 403 Forbidden
            .body("lastName", equalTo("Sananta"))
            .body("id", equalTo("662e8f911846fb0eced57ceb"));
    }

    @Test
    public void tc07() {
        // Operasi memiliki valid authorization dan menggunakan user id yang sudah ada di sistem (update gender)
        RestAssured.baseURI = "https://dummyapi.io/data/v1";
        String requestBody = "{\n" +
                             "  \"gender\": \"female\"\n" +
                             "}";

        given()
            .contentType(ContentType.JSON)
            .header("app-id", "662e7da1bb70a751fa259763")
            .body(requestBody)
            .pathParam("id", "662e8f911846fb0eced57ceb")
        .when()
            .put("/user/{id}")
        .then()
            .statusCode(200) // Periksa bahwa status code adalah 403 Forbidden
            .body("gender", equalTo("female"))
            .body("id", equalTo("662e8f911846fb0eced57ceb"));
    }

    @Test
    public void tc09() {
        // Operasi memiliki valid authorization dan menggunakan user id yang sudah ada di sistem (update dateOfBirth)
        RestAssured.baseURI = "https://dummyapi.io/data/v1";
        String requestBody = "{\n" +
                             "  \"dateOfBirth\": \"2002-04-2\"\n" +
                             "}";

        given()
            .contentType(ContentType.JSON)
            .header("app-id", "662e7da1bb70a751fa259763")
            .body(requestBody)
            .pathParam("id", "662e8f911846fb0eced57ceb")
        .when()
            .put("/user/{id}")
        .then()
            .statusCode(200) // Periksa bahwa status code adalah 403 Forbidden
            .body("dateOfBirth", equalTo("2002-04-02T00:00:00.000Z"))
            .body("id", equalTo("662e8f911846fb0eced57ceb"));
    }

    @Test
    public void tc19() {
        // Operasi memiliki valid authorization dan format invalid (update firstName < 2)
        RestAssured.baseURI = "https://dummyapi.io/data/v1";
        String requestBody = "{\n" +
                             "  \"firstName\": \"E\"\n" +
                             "}";

        given()
            .contentType(ContentType.JSON)
            .header("app-id", "662e7da1bb70a751fa259763")
            .body(requestBody)
            .pathParam("id", "662e8f911846fb0eced57ceb")
        .when()
            .put("/user/{id}")
        .then()
            .statusCode(400)
            .body("error", equalTo("BODY_NOT_VALID"));
    }
}
