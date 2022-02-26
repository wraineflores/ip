import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    static int workingIndex = 0;       // points to the index where we want o mark or unmark
    static String TODO_EXCEPTION_STATEMENT = "OOPS!!! The description of a todo cannot be empty.";
    static String DEADLINE_EXCEPTION_STATEMENT = "OOPS!!! The description must have a task and date (YYYY-MM-DD).";
    static String EVENT_EXCEPTION_STATEMENT = "OOPS!!! The description must have a task and date (YYYY-MM-DD).";
    static String ELSE_EXCEPTION_STATEMENT = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    static String DELETED_TASK_STATEMENT = "Noted. I've removed this task:";
    static String DELETED_FAILED_TASK_STATEMENT = "OOPS!!! Task was not deleted.";
    static String MARK_UNMARK_FAILED_STATEMENT = "OOPS!!! Please key in a correct input.";

    public TaskList() {
    }

    private static void deletedCommand(String line, ArrayList<Task> addLists) {
        try {
            workingIndex = Integer.parseInt(line.replace("delete", "").trim()) - 1;
            System.out.println(DELETED_TASK_STATEMENT);
            System.out.println(addLists.get(workingIndex).toString());
            addLists.remove(workingIndex);
            System.out.println("Now you have " + addLists.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(DELETED_FAILED_TASK_STATEMENT);
        }
    }

    private static void todoCommand(String line, ArrayList<Task> addLists) throws DukeException {
        Parser parser;
        parser = getParser(line);
        if (parser.getNewDescription().isEmpty()) {
            throw new DukeException();
        }
        Todo newTodo = new Todo(parser.getNewDescription());
        addLists.add(newTodo);
        printAddedToList(newTodo, addLists);
    }

    private static void deadlineCommand(String line, ArrayList<Task> addLists) throws DukeException {
        Parser parser;
        parser = getParser(line);
        try {
            if (parser.getNewDescription().isEmpty()) {
                throw new DukeException();
            } else if (parser.getByOrAt().isEmpty()) {
                throw new DukeException();
            } else if (parser.getDate() == null) {
                throw new DukeException();
            } else {
                Deadline newDeadline = new Deadline(parser.getNewDescription(), parser.getByOrAt());
                addLists.add(newDeadline);
                printAddedToList(newDeadline, addLists);
            }
        } catch (NullPointerException e) {
            throw new DukeException();
        }
    }

    private static void eventCommand(String line, ArrayList<Task> addLists) throws DukeException {
        Parser parser;
        parser = getParser(line);
        try {
            if (parser.getNewDescription().isEmpty()) {
                throw new DukeException();
            } else if (parser.getByOrAt().isEmpty()) {
                throw new DukeException();
            } else if (parser.getDate() == null) {
                throw new DukeException();
            } else {
                Event newEvent = new Event(parser.getNewDescription(), parser.getByOrAt());
                addLists.add(newEvent);
                printAddedToList(newEvent, addLists);
            }
        } catch (NullPointerException e) {
            throw new DukeException();
        }
    }

    private static void printAddedToList(Task task, ArrayList<Task> addLists) {
        task.printAdded();
        System.out.println(task.toString());
        System.out.println("Now you have " + addLists.size() + " tasks in the list.");
    }

    private static Parser getParser(String line) {
        Parser parser;
        parser = new Parser(line);
        parser.splitLine();
        return parser;
    }

    private static void unmarkCommand(ArrayList<Task> addLists, String line) {
        try {
            workingIndex = Integer.parseInt(line.replace("unmark", "").trim()) - 1;
            addLists.get(workingIndex).markAsUndone();
            System.out.println(addLists.get(workingIndex).toString());
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(MARK_UNMARK_FAILED_STATEMENT);
        }

    }

    private static void markCommand(ArrayList<Task> addLists, String line) {
        try {
            workingIndex = Integer.parseInt(line.replace("mark", "").trim()) - 1;
            addLists.get(workingIndex).markAsDone();
            System.out.println(addLists.get(workingIndex).toString());
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(MARK_UNMARK_FAILED_STATEMENT);
        }
    }

    private static void todoTryCatch(ArrayList<Task> addLists, String line) {
        try {
            todoCommand(line, addLists);
        } catch (DukeException e) {
            System.out.println(TODO_EXCEPTION_STATEMENT);
        }
    }

    private static void deadlineTryCatch(ArrayList<Task> addLists, String line) {
        try {
            deadlineCommand(line, addLists);
        } catch (DukeException e) {
            System.out.println(DEADLINE_EXCEPTION_STATEMENT);
        }
    }

    private static void eventTryCatch(ArrayList<Task> addLists, String line) {
        try {
            eventCommand(line, addLists);
        } catch (DukeException e) {
            System.out.println(EVENT_EXCEPTION_STATEMENT);
        }
    }

    static void mainLoopCommand(ArrayList<Task> addLists, String line) {
        if (line.startsWith(String.valueOf(TaskListCommand.list))) {
            List updatedList = new List(addLists);
            updatedList.printList();
        } else if (line.startsWith(String.valueOf(TaskListCommand.mark))) {
            markCommand(addLists, line);
        } else if (line.startsWith(String.valueOf(TaskListCommand.unmark))) {
            unmarkCommand(addLists, line);
        } else if (line.startsWith(String.valueOf(TaskListCommand.delete))) {
            deletedCommand(line, addLists);
        } else if (line.startsWith(String.valueOf(TaskListCommand.todo))) {
            todoTryCatch(addLists, line);
        } else if (line.startsWith(String.valueOf(TaskListCommand.deadline))) {
            deadlineTryCatch(addLists, line);
        } else if (line.startsWith(String.valueOf(TaskListCommand.event))) {
            eventTryCatch(addLists, line);
        } else {
            System.out.println(ELSE_EXCEPTION_STATEMENT);
        }
    }

    static void run(ArrayList<Task> addLists) throws FileNotFoundException {
        String line;
        addLists = Storage.tryCatchReadFile(addLists);
        System.out.println("Hello! I'm Earl Grey\nWhat can I do for you?");
        Scanner in = new Scanner(System.in);
        line = in.nextLine().trim();            // reads input

        while (!String.valueOf(TaskListCommand.bye).equals(line)) {
            TaskList.mainLoopCommand(addLists, line);
            line = in.nextLine().trim();
        }
        Storage.programmeExits(addLists);
    }
}
