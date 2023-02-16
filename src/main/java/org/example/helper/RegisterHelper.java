package org.example.helper;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.user.UserCredentials;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class RegisterHelper {

    private static final String BASE_URI = "https://stellarburgers.nomoreparties.site/";
    private static final String USER_REGISTER_PATH = "/api/auth/register";
    private static final String USER_DELETE_PATH = "/api/auth/user";
    private static final String USER_LOGIN_PATH = "/api/auth/login";

    private String userAccessToken;

    @Step("Зарегистрировать пользователя через API")
    public void registerUser(UserCredentials credentials) {
        userAccessToken = given()
                .spec(getSpec())
                .body(credentials)
                .when()
                .post(USER_REGISTER_PATH)
                .then()
                .assertThat().statusCode(SC_OK)
                .and()
                .extract()
                .path("accessToken").toString().replace("Bearer ", "");
    }

    @Step("Удалить созданного ранее пользователя через API")
    public ValidatableResponse deleteUser() {
        return given()
                .spec(getSpec()
                        .auth()
                        .oauth2(userAccessToken)
                )
                .when()
                .delete(USER_DELETE_PATH)
                .then();
    }

    public boolean loginUser(UserCredentials credentials) {
        ValidatableResponse response = given()
                .spec(getSpec())
                .body(credentials)
                .when()
                .post(USER_LOGIN_PATH)
                .then();

        boolean isUserLoggedIn = response.extract().statusCode() == SC_OK;
        if (isUserLoggedIn) {
            userAccessToken = response
                    .extract()
                    .path("accessToken")
                    .toString()
                    .replace("Bearer ", "");
        }
        return isUserLoggedIn;
    }

    private RequestSpecification getSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URI)
                .build();
    }
}
