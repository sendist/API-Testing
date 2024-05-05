
package com.apitesting;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.json.simple.JSONObject; // Or your preferred JSON library

public class CreateUserTest {
    public JSONObject getPayload() {
        // Test Data
        String title = "ms";
        String firstName = "Daffa";
        String lastName = "Raihandika";
        String gender = "female";
        String email = "daffa@example.com";
        String dateOfBirth = "1990-01-01";
        String phone = "08127387812";
        String picture = "https://example.com/picture.jpg";

        // location object
        JSONObject location = new JSONObject();
        location.put("street", "123 Example St");
        location.put("city", "Exampleville");
        location.put("state", "Examplestate");
        location.put("country", "Exampleland");
        location.put("timezone", "+7:00");

        // Construct the main JSON payload
        JSONObject payload = new JSONObject();
        payload.put("title", title);
        payload.put("firstName", firstName);
        payload.put("lastName", lastName);
        payload.put("gender", gender);
        payload.put("email", email);
        payload.put("dateOfBirth", dateOfBirth);
        payload.put("phone", phone);
        payload.put("picture", picture);
        payload.put("location", location);

        return payload;
    }

    @BeforeEach
    public void before() {
        RestAssured.baseURI = "https://dummyapi.io/data/v1";

    }

    @Test
    public void TC01_Tanpa_appId() {
        // POST operation without authorization header
        JSONObject payload = getPayload();

        given()
                .contentType(ContentType.JSON)
                .body(payload.toJSONString())
                .when()
                .post("/user/create")
                .then()
                .assertThat()
                .statusCode(403)
                .body("error", equalTo("APP_ID_MISSING"));

    }

    @Test
    public void TC02_Dengan_appId_tidak_valid() {
        // POST operation with invalid authorization header
        JSONObject payload = getPayload();

        given()
                .contentType(ContentType.JSON)
                .header("app-id", "invalid-app-id")
                .body(payload.toJSONString())
                .when()
                .post("/user/create")
                .then()
                .assertThat()
                .statusCode(403)
                .body("error", equalTo("APP_ID_NOT_EXIST"));
    }

    @Test
    public void TC03_Dengan_appId_valid() {
        // POST operation with valid authorization header
        JSONObject payload = getPayload();
        String email = "emailtest@example.com";
        payload.put("email", email);

        given()
                .contentType(ContentType.JSON)
                .header("app-id", "6627132f6cae03d7fddee77b")
                .body(payload.toJSONString())
                .when()
                .post("/user/create")
                .then()
                .assertThat()
                .statusCode(200)
                .body("title", equalTo("ms"))
                .body("firstName", equalTo("Daffa"))
                .body("lastName", equalTo("Raihandika"))
                .body("gender", equalTo("female"))
                .body("email", equalTo(email))
                .body("dateOfBirth", equalTo("1990-01-01T00:00:00.000Z"))
                .body("phone", equalTo("08127387812"))
                .body("picture", equalTo("https://example.com/picture.jpg"))
                .body("location.street", equalTo("123 Example St"))
                .body("location.city", equalTo("Exampleville"))
                .body("location.state", equalTo("Examplestate"))
                .body("location.country", equalTo("Exampleland"))
                .body("location.timezone", equalTo("+7:00"));

    }

    @Test
    public void TC04_Dengan_email_yg_sudah_digunakan() {
        // POST operation with email that already used
        JSONObject payload = getPayload();
        payload.put("email", "daffa@example.com");

        given()
                .contentType(ContentType.JSON)
                .header("app-id", "6627132f6cae03d7fddee77b")
                .body(payload.toJSONString())
                .when()
                .post("/user/create")
                .then()
                .assertThat()
                .statusCode(400)
                .body("error", equalTo("BODY_NOT_VALID"));
    }

    @Test
    public void TC07_Field_firstName_kosong() {
        // POST operation with empty firstName field
        JSONObject payload = getPayload();
        payload.remove("firstName");

        given()
                .contentType(ContentType.JSON)
                .header("app-id", "6627132f6cae03d7fddee77b")
                .body(payload.toJSONString())
                .when()
                .post("/user/create")
                .then()
                .assertThat()
                .statusCode(400)
                .body("error", equalTo("BODY_NOT_VALID"));
    }

}
