package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;

public class ProjectCreationTest {

    @Test
    public void userCanCreateAProject(){

        long projectId = RestAssured
                .given()
                    .baseUri("https://api.todoist.com")
                    .basePath("/rest/v1/")
                    .header("Authorization", "Bearer 6e0af658835382fa334b51863752c64b07dcc204")
                    .contentType(ContentType.JSON)
                    .body("{\"name\": \"Moj nowy projekt\"}")
                .when()
                    .post("/projects")
                .then()
                    .log().all()
                    .assertThat()
                        .statusCode(200)
                        .body("name", Matchers.equalTo("Moj nowy projekt"))
                        .header("Content-Type", Matchers.equalTo("application/json"))
                        .and()
                        .extract().path("id");

        RestAssured
                .given()
                    .baseUri("https://api.todoist.com")
                    .basePath("/rest/v1/")
                    .header("Authorization", "Bearer 6e0af658835382fa334b51863752c64b07dcc204")
                    .contentType(ContentType.JSON)
                    .pathParam("id", projectId)
                .when()
                    .get("/projects/{id}")
                .then()
                    .log().all()
                    .assertThat()
                        .statusCode(200)
                        .body("name", Matchers.equalTo("Moj nowy projekt"));

        RestAssured
                .given()
                    .baseUri("https://api.todoist.com")
                    .basePath("/rest/v1/")
                    .header("Authorization", "Bearer 6e0af658835382fa334b51863752c64b07dcc204")
                    .contentType(ContentType.JSON)
                .when()
                    .get("/projects")
                .then()
                    .log().all();
        
    }
}

