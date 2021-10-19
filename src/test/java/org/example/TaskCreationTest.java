package org.example;

import net.thucydides.core.annotations.Steps;
import org.example.steps.ProjectSteps;
import org.example.steps.TaskSteps;
import org.junit.Test;

public class TaskCreationTest extends Base{

    @Steps
    private TaskSteps taskSteps;

    @Test
    public void user_can_create_task_to_a_project(){

        taskSteps.userCanAddNewTaskToProject();
        taskSteps.userCheckTaskDetails();
        taskSteps.userCheckIfProjectHaveCorrectTask();
    }
}
