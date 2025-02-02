package byu.commands;

import byu.exceptions.InvalidIndexException;
import byu.task.Task;
import byu.util.Response;
import byu.util.TaskList;
import byu.util.Ui;

/**
 * A command to mark a task as done.
 */
public class MarkCommand extends Command {

    private final int index;
    private Task task;

    /**
     * Creates a MarkCommand with the index of the {@code Task} to be marked as done.
     *
     * @param index the index of the {@code Task} to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws InvalidIndexException {
        this.task = tasks.mark(this.index);
    }

    @Override
    public Response generateResponse(TaskList tasks) {
        String output = String.format(
            "Good job for completing the task!\nThis task is now marked as done:\n%s\n", this.task);
        return new Response(output, false, false);
    }
}
