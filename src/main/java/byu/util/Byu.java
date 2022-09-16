package byu.util;

import java.io.IOException;

import byu.commands.Command;
import byu.exceptions.ByuException;
import byu.exceptions.DuplicateException;
import byu.exceptions.IncorrectFileInputException;
import javafx.application.Platform;

/**
 * Byu is a chatbot that helps to organize tasks.
 */
public class Byu {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates an instance of Byu and loads data from the file.
     */
    public Byu() {
        try {
            ui = new Ui();
            storage = new Storage();
            tasks = storage.load();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IncorrectFileInputException e) {
            System.out.print(e.getMessage());
        } catch (DuplicateException e) {
            System.out.print(e.getMessage());
        }
    }

    /**
     * Generates a response to user input.
     *
     * @param input the user input Byu generates a response to.
     * @return the response to a user input.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            if (command.isExit()) {
                Platform.exit();
            }
            command.execute(this.tasks, this.ui);
            this.storage.save();
            return this.ui.getValidOutput();
        } catch (ByuException e) {
            return ui.getErrorOutput(e);
        }
    }
}
