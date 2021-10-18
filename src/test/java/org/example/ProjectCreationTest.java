package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

public class ProjectCreationTest {

    @Before
    public void setup(){
        RestAssured.baseURI = "https://api.todoist.com";
        RestAssured.basePath = "/rest/v1/";

        RestAssured.requestSpecification =
                RestAssured
                        .given()
                            .header("Authorization", "Bearer 6e0af658835382fa334b51863752c64b07dcc204")
                            .contentType(ContentType.JSON);

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
    @Test
    public void userCanCreateProject(){

        long projectId = RestAssured
                .given()
                    .body("{\"name\": \"Moj nowy projekt\"}")
                .when()
                    .post("/projects")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body("name", Matchers.equalTo("Moj nowy projekt"))
                        .header("Content-Type", Matchers.equalTo("application/json"))
                        .and()
                        .extract().path("id");

        RestAssured
                .given()
                    .pathParam("id", projectId)
                .when()
                    .get("/projects/{id}")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body("name", Matchers.equalTo("Moj nowy projekt"));

        RestAssured
                .when()
                    .get("/projects")
                .then()
                    .assertThat()
                        .body(String.format("find{ it.id == %d }.name", projectId), Matchers.equalTo("Moj nowy projekt"));

    }
}

