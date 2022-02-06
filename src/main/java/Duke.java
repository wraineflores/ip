import java.util.Scanner;

public class Duke {
    //@edu.umd.cs.findbugs.annotations.SuppressFBWarnings("DM_DEFAULT_ENCODING")
    public static void main(String[] args) throws DukeException {
        System.out.println("Hello! I'm Earl Grey\nWhat can I do for you?");
        String TODO_EXCEPTION_STATEMENT = "OOPS!!! The description of a todo cannot be empty.";
        String DEADLINE_EXCEPTION_STATEMENT = "OOPS!!! The description of a deadline must have a task and date.";
        String EVENT_EXCEPTION_STATEMENT = "OOPS!!! The description of an event must have a task and date.";
        String ELSE_EXCEPTION_STATEMENT = "OOPS!!! I'm sorry, but I don't know what that means :-(";

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine().trim();            // reads input
        LineSplitter lineSplitter;              // class that splits input

        Task[] addLists = new Task[100];        // array of all tasks
        int addListsCounter = 0;                // counts the number of non-null elements in the array
        int workingIndex = 0;                   // points to the index where we want o mark or unmark

        while (!String.valueOf(CommandTypes.bye).equals(line)) {
            if (line.startsWith(String.valueOf(CommandTypes.list))) {
                List updatedList = new List(addLists);
                updatedList.printList();
            } else if (line.startsWith(String.valueOf(CommandTypes.mark))) {
                workingIndex = Integer.parseInt(line.replace("mark", "").trim()) - 1;
                addLists[workingIndex].markAsDone();
                System.out.println(addLists[workingIndex].toString());
            } else if (line.startsWith(String.valueOf(CommandTypes.unmark))) {
                workingIndex = Integer.parseInt(line.replace("unmark", "").trim()) - 1;
                addLists[workingIndex].markAsUndone();
                System.out.println(addLists[workingIndex].toString());
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

    private static int todoFunction(String line, Task[] addLists, int addListsCounter) throws DukeException {
        LineSplitter lineSplitter;
        lineSplitter = getLineSplitter(line);
        if (lineSplitter.getNewDescription().isEmpty()) {
            throw new DukeException();
        }
        addLists[addListsCounter] = new Todo(lineSplitter.getNewDescription());
        addListsCounter = printAdded(addListsCounter, addLists[addListsCounter]);
        return addListsCounter;
    }

    private static int eventFunction(String line, Task[] addLists, int addListsCounter) throws DukeException {
        LineSplitter lineSplitter;
        lineSplitter = getLineSplitter(line);
        try {
            if (lineSplitter.getNewDescription().isEmpty() || lineSplitter.getByOrAt().isEmpty()) {
                throw new DukeException();
            }
        } catch (NullPointerException e) {
            throw new DukeException();
        }
        addLists[addListsCounter] = new Event(lineSplitter.getNewDescription(), lineSplitter.getByOrAt());
        addListsCounter = printAdded(addListsCounter, addLists[addListsCounter]);
        return addListsCounter;
    }

    private static int deadlineFunction(String line, Task[] addLists, int addListsCounter) throws DukeException {
        LineSplitter lineSplitter;
        lineSplitter = getLineSplitter(line);
        try {
            if (lineSplitter.getNewDescription().isEmpty() || lineSplitter.getByOrAt().isEmpty()) {
                throw new DukeException();
            }
        } catch (NullPointerException e) {
            throw new DukeException();
        }
        addLists[addListsCounter] = new Deadline(lineSplitter.getNewDescription(), lineSplitter.getByOrAt());
        addListsCounter = printAdded(addListsCounter, addLists[addListsCounter]);
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
}
