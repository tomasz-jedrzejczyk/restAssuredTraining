package org.example.steps;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static java.lang.String.format;
import static org.hamcrest.Matchers.equalTo;

public class ProjectSteps {

    private String projectName;
    private long projectId;

    @Step
    public long userCreateNewProject(){

        projectName = "New project";
        projectId = SerenityRest
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
        return projectId;
    }

    @Step
    public void userChecksProjectDetails(){

        SerenityRest
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

    @Step
    public void userChecksIfProjectIsListedWithAllProjects(){

        SerenityRest
                .given()
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
