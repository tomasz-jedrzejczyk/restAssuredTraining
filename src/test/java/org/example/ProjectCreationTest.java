package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.example.steps.ProjectSteps;
import org.junit.Before;
import org.junit.Test;

import static java.lang.String.format;
import static org.hamcrest.Matchers.equalTo;

public class ProjectCreationTest extends Base{

    ProjectSteps steps = new ProjectSteps();

    @Test
    public void user_can_create_project() {

        String projectName = "New project for first test";

        long projectId = steps.userCreateNewProject(projectName);
        steps.userChecksProjectDetails(projectName, projectId);
        steps.userChecksIfProjectIsListedWithAllProjects(projectName, projectId);
    }
}


