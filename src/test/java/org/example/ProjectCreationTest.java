/**
 * Created by Tomasz Jedrzejczyk
 * Date 18.10.2021
 */

package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static java.lang.String.format;
import static org.hamcrest.Matchers.equalTo;

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
    public void userCanCreateProject() {

        String projectName = "Totalnie nowy projekt";
        long projectId = userCreateNewProject(projectName);
        userChecksProjectDetails(projectName, projectId);
        userChecksIfProjectIsListedWithAllProjects(projectName, projectId);

    }

    public long userCreateNewProject(String projectName){

        return RestAssured
                    .given()
                        .body(format("{\"name\": \"%s\"}", projectName))
                    .when()
                        .post("/projects")
                    .then()
                        .assertThat()
                            .statusCode(200)
                            .body(
                                    "name", equalTo(projectName)
                            )
                            .header("Content-Type", equalTo("application/json"))
                            .and()
                            .extract().path("id");
    }

    public void userChecksProjectDetails(String projectName, long projectId){

        RestAssured
                .given()
                    .pathParam("id", projectId)
                .when()
                    .get("/projects/{id}")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body(
                                "name", equalTo(projectName)
                        );
    }

    public void userChecksIfProjectIsListedWithAllProjects(String projectName, long projectId){

        RestAssured
                .when()
                    .get("/projects")
                .then()
                    .assertThat()
                        .body(
                                format("find{ it.id == %d }.name", projectId),
                                equalTo(projectName)
                        );
    }
}

