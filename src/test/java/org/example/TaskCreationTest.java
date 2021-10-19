package org.example;

import org.example.steps.ProjectSteps;
import org.example.steps.TaskSteps;
import org.junit.Test;

public class TaskCreationTest extends Base{

    TaskSteps taskSteps = new TaskSteps();
    ProjectSteps preconditions = new ProjectSteps();

    @Test
    public void user_can_create_task_to_a_project(){

        String projectName = "New project for second test";
        String taskName = "New task";

        long projectId = preconditions.userCreateNewProject(projectName);

        long taskId = taskSteps.userCanAddNewTaskToProject(taskName, projectId);
        taskSteps.userCheckTaskDetails(taskName, projectId, taskId);
        taskSteps.userCheckIfProcjetHaveCorrectTask(taskId, taskName, projectId);
    }
}
