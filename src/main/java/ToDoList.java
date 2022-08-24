import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;

public class ToDoList {
    private final ArrayList<Task> list;
    private int numOfTasks = 0;

    public ToDoList() {
        this.list = new ArrayList<>();
    }

    public void execute(Instruction instruct, String response) throws InvalidIndex, EmptyDescription {
        if (instruct == Instruction.LIST) {
            list();
        } else {
            String[] str = response.split(" ");
            if (str.length == 1) {
                throw new EmptyDescription(instruct.name());
            }
            String description = response.substring(str[0].length() + 1);
            switch (instruct) {
                case MARK: {
                    Integer index1 = Integer.valueOf(description);
                    //should handle non-int
                    mark(index1);
                    break;
                }
                case UNMARK: {
                    Integer index = Integer.valueOf(description);
                    unMark(index);
                    break;
                }
                case DEADLINE: {
                    String[] substrings = description.split(" /by ");
                    Deadlines d = new Deadlines(substrings[0], substrings[1]);
                    addTask(d);
                    break;
                }
                case EVENT: {
                    String[] substrings = description.split(" /at ");
                    Events e = new Events(substrings[0], substrings[1]);
                    addTask(e);
                    break;
                }
                case TODO: {
                    ToDos t = new ToDos(description);
                    addTask(t);
                    break;
                }
                case DELETE: {
                    Integer index = Integer.valueOf(description);
                    delete(index);
                    break;
                }
            }
        }
    }

    public void addTask(Task task) {
        this.list.add(task);
        this.numOfTasks += 1;
        System.out.format("added: %s\n", task.toString());
        if (this.numOfTasks == 1) {
            System.out.format("Now you have %d task in the list.\n", this.numOfTasks);
        } else {
            System.out.format("Now you have %d tasks in the list.\n", this.numOfTasks);
        }
    }

    public void list() {
        System.out.println("These are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.format("%d. %s\n", i + 1, list.get(i).toString());
        }
    }

    public void mark(int i) throws InvalidIndex  {
        if (i > numOfTasks) {
            throw new InvalidIndex();
        }
        Task t = list.get(i - 1);
        t.markDone();
        System.out.println("Nice! I've marked this task as done:\n" + t);
    }

    public void unMark(int i) throws InvalidIndex {
        if (i > numOfTasks) {
            throw new InvalidIndex();
        }
        Task t = list.get(i - 1);
        t.markUndone();
        System.out.println("OK, I've marked this task as not done yet:\n" + t);
    }

    public void delete(int i) throws InvalidIndex {
        if (i > numOfTasks) {
            throw new InvalidIndex();
        }
        Task t = list.get(i - 1);
        this.list.remove(i - 1);
        this.numOfTasks -= 1;
        System.out.println("Alright! I've removed this task:\n" + t);
        System.out.format("Now you have %d tasks in the list.\n", this.numOfTasks);
    }

    public void save() {
        try {
            FileWriter fw = new FileWriter("./data/Duke.txt");
            for (int i = 0; i < numOfTasks; i++) {
                Task t = list.get(i);
                t.write(fw);
            }
            fw.close();
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }
}
