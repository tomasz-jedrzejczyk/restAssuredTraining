package org.example;

import org.example.steps.ProjectSteps;
import org.junit.Test;


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


