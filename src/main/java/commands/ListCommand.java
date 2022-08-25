package commands;

import byu.ToDoList;
import byu.Ui;

import exceptions.InvalidIndex;

public class ListCommand extends Command {

    public void execute(ToDoList list, Ui ui) throws InvalidIndex {
        list.list();
    }

    public boolean isExit() {
        return false;
    }

}
