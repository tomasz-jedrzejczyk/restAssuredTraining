package org.example;

import net.thucydides.core.annotations.Steps;
import org.example.steps.ProjectSteps;
import org.junit.Test;


public class ProjectCreationTest extends Base{

    @Steps
    private ProjectSteps steps;

    @Test
    public void user_can_create_project() {

        steps.userCreateNewProject();
        steps.userChecksProjectDetails();
        steps.userChecksIfProjectIsListedWithAllProjects();
    }
}


