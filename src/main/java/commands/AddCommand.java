package commands;

import byu.TaskList;
import byu.Ui;
import exceptions.DuplicateException;
import task.Task;

/**
 * A command to add a task to the list.
 */
public class AddCommand extends Command {

    private final Task task;

    /**
     * Creates a command to add a task.
     *
     * @param task the Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DuplicateException {
        tasks.addTask(task);
        String response = generateResponse(tasks);
        ui.setOutput(response);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String generateResponse(TaskList tasks) {
        String responseForOneTask = "Okie! I've added this task:\n%s\nNow you have %d upcoming task.\n";
        String responseForManyTasks = "Okie! I've added this task:\n%s\nNow you have %d upcoming tasks.\n";

        int numOfTasks = tasks.getNumOfTasks();
        if (numOfTasks == 1) {
            return String.format(responseForOneTask, this.task.toString(), numOfTasks);
        } else {
            return String.format(responseForManyTasks, this.task.toString(), numOfTasks);
        }
    }
}
