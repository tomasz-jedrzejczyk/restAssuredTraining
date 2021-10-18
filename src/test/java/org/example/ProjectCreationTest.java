package org.example;

import io.restassured.RestAssured;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

public class ProjectCreationTest {

    @Test
    public void userCanCreateAProject(){

        RestAssured
                .given()
                    .baseUri("https://api.todoist.com")
                    .header("Authorization", "Bearer 6e0af658835382fa334b51863752c64b07dcc204")
                    .header("Content-Type", "application/json")
                    .body("{\"name\":\"Moj nowy projekt\"}")
                .when()
                    .post("/rest/v1/projects")
                .then()
                    .log().all()
                    .assertThat()
                        .statusCode(200)
                        .body("name", Matchers.equalTo("Moj nowy projekt"))
                        .header("Content-Type", Matchers.equalTo("application/json"));

    }
}
