package byu.task;

import java.io.FileWriter;
import java.io.IOException;

/**
 * A task, with a given name and status (completed or not completed).
 */
public abstract class Task {

    public static final String DONE_INT = "1";
    public static final String NOT_DONE_INT = "0";
    private final String name;
    private boolean isDone;

    /**
     * Creates a task, with its status initialized as incomplete.
     * @param name the name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public void setDone(boolean b) {
        this.isDone = b;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns "1" if the task is marked as done,
     * else returns "0".
     *
     * @return "1" if the task is marked as done, else return "0".
     */
    public String getDoneValue() {
        if (this.isDone) {
            return DONE_INT;
        } else {
            return NOT_DONE_INT;
        }
    }

    /**
     * Returns true if the name of the {@code Task} contains the string,
     * else returns false.
     *
     * @return true if the name of the {@code Task} contains the string.
     */
    public boolean matches(String string) {
        return name.contains(string);
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }

    /**
     * Saves the {@code Task} by writing it to a file using a {@code FileWriter}.
     * @param fileWriter the {@code FileWriter} to save the {@code Task} to a file.
     * @throws IOException if an I/O error occurs.
     */
    public abstract void write(FileWriter fileWriter) throws IOException;

    /**
     * Compares the {@code Task} with another {@code Task}.
     * Returns true if they have the same name and description, else returns false.
     *
     * @param task the {@code Task} to compare with.
     * @return true if the two tasks have the same name and description, else returns false.
     */
    public abstract boolean equals(Task task);
}
