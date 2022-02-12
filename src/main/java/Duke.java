import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static String fileContent = "";
    static String TODO_EXCEPTION_STATEMENT = "OOPS!!! The description of a todo cannot be empty.";
    static String DEADLINE_EXCEPTION_STATEMENT = "OOPS!!! The description of a deadline must have a task and date.";
    static String EVENT_EXCEPTION_STATEMENT = "OOPS!!! The description of an event must have a task and date.";
    static String ELSE_EXCEPTION_STATEMENT = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    static String DELETED_TASK_STATEMENT = "Noted. I've removed this task:";

    private static void readFromFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            fileContent = fileContent.concat(scan.nextLine() + "\n");
        }
    }

    private static void writeToFile(String filePath, String fileContent) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(fileContent);
        fileWriter.close();
    }

    private static int deletedFunction(String DELETED_TASK_STATEMENT, ArrayList<Task> addLists, int addListsCounter, int workingIndex) {
        System.out.println(DELETED_TASK_STATEMENT);
        System.out.println(addLists.get(workingIndex).toString());
        addLists.remove(workingIndex);
        addListsCounter--;
        System.out.println("Now you have " + addListsCounter + " tasks in the list.");
        return addListsCounter;
    }

    private static int todoFunction(String line, ArrayList<Task> addLists, int addListsCounter) throws DukeException {
        LineSplitter lineSplitter;
        lineSplitter = getLineSplitter(line);
        if (lineSplitter.getNewDescription().isEmpty()) {
            throw new DukeException();
        }
        Todo newTodo = new Todo(lineSplitter.getNewDescription());
        addLists.add(newTodo);
        addListsCounter = printAdded(addListsCounter, addLists.get(addListsCounter));
        return addListsCounter;
    }

    private static int eventFunction(String line, ArrayList<Task> addLists, int addListsCounter) throws
            DukeException {
        LineSplitter lineSplitter;
        lineSplitter = getLineSplitter(line);
        try {
            if (lineSplitter.getNewDescription().isEmpty() || lineSplitter.getByOrAt().isEmpty()) {
                throw new DukeException();
            }
        } catch (NullPointerException e) {
            throw new DukeException();
        }
        Event newEvent = new Event(lineSplitter.getNewDescription(), lineSplitter.getByOrAt());
        addLists.add(newEvent);
        addListsCounter = printAdded(addListsCounter, addLists.get(addListsCounter));
        return addListsCounter;
    }

    private static int deadlineFunction(String line, ArrayList<Task> addLists, int addListsCounter) throws
            DukeException {
        LineSplitter lineSplitter;
        lineSplitter = getLineSplitter(line);
        try {
            if (lineSplitter.getNewDescription().isEmpty() || lineSplitter.getByOrAt().isEmpty()) {
                throw new DukeException();
            }
        } catch (NullPointerException e) {
            throw new DukeException();
        }
        Deadline newDeadline = new Deadline(lineSplitter.getNewDescription(), lineSplitter.getByOrAt());
        addLists.add(newDeadline);
        addListsCounter = printAdded(addListsCounter, addLists.get(addListsCounter));
        return addListsCounter;
    }

    private static int printAdded(int addListsCounter, Task addList) {
        addList.printAdded();
        System.out.println(addList.toString());
        addListsCounter++;
        System.out.println("Now you have " + addListsCounter + " tasks in the list.");
        return addListsCounter;
    }

    private static LineSplitter getLineSplitter(String line) {
        LineSplitter lineSplitter;
        lineSplitter = new LineSplitter(line);
        lineSplitter.splitLine();
        return lineSplitter;
    }

    public static void main(String[] args) throws DukeException {
        System.out.println("Hello! I'm Earl Grey\nWhat can I do for you?");

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine().trim();            // reads input
        LineSplitter lineSplitter;              // class that splits input

        ArrayList<Task> addLists = new ArrayList<>();
        int addListsCounter = 0;                // counts the number of non-null elements in the array
        int workingIndex = 0;                   // points to the index where we want o mark or unmark

        while (!String.valueOf(CommandTypes.bye).equals(line)) {
            if (line.startsWith(String.valueOf(CommandTypes.list))) {
                List updatedList = new List(addLists);
                updatedList.printList();
            } else if (line.startsWith(String.valueOf(CommandTypes.mark))) {
                workingIndex = Integer.parseInt(line.replace("mark", "").trim()) - 1;
                addLists.get(workingIndex).markAsDone();
                System.out.println(addLists.get(workingIndex).toString());
            } else if (line.startsWith(String.valueOf(CommandTypes.unmark))) {
                workingIndex = Integer.parseInt(line.replace("unmark", "").trim()) - 1;
                addLists.get(workingIndex).markAsUndone();
                System.out.println(addLists.get(workingIndex).toString());
            } else if (line.startsWith(String.valueOf(CommandTypes.delete))) {
                workingIndex = Integer.parseInt(line.replace("delete", "").trim()) - 1;
                addListsCounter = deletedFunction(DELETED_TASK_STATEMENT, addLists, addListsCounter, workingIndex);
            } else if (line.startsWith(String.valueOf(CommandTypes.todo))) {
                try {
                    addListsCounter = todoFunction(line, addLists, addListsCounter);
                } catch (DukeException e) {
                    System.out.println(TODO_EXCEPTION_STATEMENT);
                }
            } else if (line.startsWith(String.valueOf(CommandTypes.deadline))) {
                try {
                    addListsCounter = deadlineFunction(line, addLists, addListsCounter);
                } catch (DukeException e) {
                    System.out.println(DEADLINE_EXCEPTION_STATEMENT);
                }
            } else if (line.startsWith(String.valueOf(CommandTypes.event))) {
                try {
                    addListsCounter = eventFunction(line, addLists, addListsCounter);
                } catch (DukeException e) {
                    System.out.println(EVENT_EXCEPTION_STATEMENT);
                }
            } else {
                System.out.println(ELSE_EXCEPTION_STATEMENT);
            }
            line = in.nextLine().trim();
        }
        System.out.println("Bye. Until we meet again!");
    }
}
