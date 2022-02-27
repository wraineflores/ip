import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * TaskList represents the class that has all the commands
 */
public class TaskList {
    static int workingIndex = 0;       // points to the index where we want o mark or unmark
    static String findKeyWord;
    static String TODO_EXCEPTION_STATEMENT = "OOPS!!! The description of a todo cannot be empty.";
    static String DEADLINE_EXCEPTION_STATEMENT = "OOPS!!! The description must have a task and date (YYYY-MM-DD).";
    static String EVENT_EXCEPTION_STATEMENT = "OOPS!!! The description must have a task and date (YYYY-MM-DD).";
    static String ELSE_EXCEPTION_STATEMENT = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    static String ELSE_EXCEPTION_STATEMENT_2 = "To see all features, enter: help";
    static String DELETED_TASK_STATEMENT = "Noted. I've removed this task:";
    static String DELETED_FAILED_TASK_STATEMENT = "OOPS!!! Task was not deleted.";
    static String MARK_UNMARK_FAILED_STATEMENT = "OOPS!!! Please key in a correct input.";
    static String START_FIND_STATEMENT = "Here are the matching tasks in your list:";
    static String FINISHED_FIND_STATEMENT = "System finished finding keyword.";
    static String FAILED_FIND_STATEMENT = "OOPS!!! Keyword is empty.";

    public TaskList() {
    }

    /**
     * Deletes the task from the list
     *
     * @param line     The next line that needs to be processed then added into the main list
     * @param addLists The main list of the program
     */
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

    /**
     * Adds a todo task in the main list
     *
     * @param line     The next line that needs to be processed then added into the main list
     * @param addLists The main list of the program
     * @throws DukeException Exception for this program
     */
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

    /**
     * Adds a deadline task in the main list
     *
     * @param line     The next line that needs to be processed then added into the main list
     * @param addLists The main list of the program
     * @throws DukeException Exception for this program
     */
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

    /**
     * Adds a event task in the main list
     *
     * @param line     The next line that needs to be processed then added into the main list
     * @param addLists The main list of the program
     * @throws DukeException Exception for this program
     */
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

    /**
     * Acknowledges that a task was added in the list
     *
     * @param task     The specific task in the list
     * @param addLists The main list of the program
     */
    private static void printAddedToList(Task task, ArrayList<Task> addLists) {
        task.printAdded();
        System.out.println(task.toString());
        System.out.println("Now you have " + addLists.size() + " tasks in the list.");
    }

    /**
     * Function that parses the line
     *
     * @param line The next line that needs to be processed then added into the main list
     * @return The parser instance from the line
     */
    private static Parser getParser(String line) {
        Parser parser;
        parser = new Parser(line);
        parser.splitLine();
        return parser;
    }

    /**
     * Marks the task as done in the list
     *
     * @param addLists The main list of the program
     * @param line     The next line that needs to be processed then added into the main list
     */
    private static void unmarkCommand(ArrayList<Task> addLists, String line) {
        try {
            workingIndex = Integer.parseInt(line.replace("unmark", "").trim()) - 1;
            addLists.get(workingIndex).markAsUndone();
            System.out.println(addLists.get(workingIndex).toString());
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(MARK_UNMARK_FAILED_STATEMENT);
        }

    }

    /**
     * Unmarks the task as undone in the list
     *
     * @param addLists The main list of the program
     * @param line     The next line that needs to be processed then added into the main list
     */
    private static void markCommand(ArrayList<Task> addLists, String line) {
        try {
            workingIndex = Integer.parseInt(line.replace("mark", "").trim()) - 1;
            addLists.get(workingIndex).markAsDone();
            System.out.println(addLists.get(workingIndex).toString());
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(MARK_UNMARK_FAILED_STATEMENT);
        }
    }

    /**
     * The try-catch block of the todoCommand function
     *
     * @param addLists The main list of the program
     * @param line     The next line that needs to be processed then added into the main list
     */
    private static void todoTryCatch(ArrayList<Task> addLists, String line) {
        try {
            todoCommand(line, addLists);
        } catch (DukeException e) {
            System.out.println(TODO_EXCEPTION_STATEMENT);
        }
    }

    /**
     * The try-catch block of the deadlineCommand function
     *
     * @param addLists The main list of the program
     * @param line     The next line that needs to be processed then added into the main list
     */
    private static void deadlineTryCatch(ArrayList<Task> addLists, String line) {
        try {
            deadlineCommand(line, addLists);
        } catch (DukeException e) {
            System.out.println(DEADLINE_EXCEPTION_STATEMENT);
        }
    }

    /**
     * The try-catch block of the eventCommand function
     *
     * @param addLists The main list of the program
     * @param line     The next line that needs to be processed then added into the main list
     */
    private static void eventTryCatch(ArrayList<Task> addLists, String line) {
        try {
            eventCommand(line, addLists);
        } catch (DukeException e) {
            System.out.println(EVENT_EXCEPTION_STATEMENT);
        }
    }

    /**
     * Function that finds task(s) based on the keyword
     *
     * @param addLists The main list of the program
     * @param line     The next line that needs to be processed then added into the main list
     */
    private static void findCommand(ArrayList<Task> addLists, String line) {
        findKeyWord = line.replace("find", "").trim();
        if (!findKeyWord.equalsIgnoreCase("")) {
            System.out.println(START_FIND_STATEMENT);
            for (Task addList : addLists) {
                if (addList.getDescription().contains(findKeyWord)) {
                    System.out.println(Integer.toString(addLists.indexOf(addList) + 1) + "." + addList.toString().trim());
                }
            }
            System.out.println(FINISHED_FIND_STATEMENT);
        } else {
            System.out.println(FAILED_FIND_STATEMENT);
        }

    }

    /**
     * Function that is the main loop sequence of the program
     *
     * @param addLists The main list of the program
     * @param line     The next line that needs to be processed then added into the main list
     */
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
        } else if (line.startsWith(String.valueOf(TaskListCommand.find))) {
            findCommand(addLists, line);
        } else if (line.startsWith(String.valueOf(TaskListCommand.help))) {
            Storage.helpCommand();
        } else {
            System.out.println(ELSE_EXCEPTION_STATEMENT);
            System.out.println(ELSE_EXCEPTION_STATEMENT_2);
        }
    }

    /**
     * The function that starts the Duke program
     *
     * @param addLists The main list of the program
     * @throws FileNotFoundException Exception if no file is found
     */
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
