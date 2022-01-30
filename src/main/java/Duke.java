import java.util.Scanner;

public class Duke {
    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings("DM_DEFAULT_ENCODING")
    public static void main(String[] args) {
        System.out.println("Hello! I'm Earl Grey\nWhat can I do for you?");

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine().trim();            // reads input
        LineSplitter lineSplitter;              // class that splits input

        Task[] addLists = new Task[100];        // array of all tasks
        int addListsCounter = 0;                // counts the number of non-null elements in the array
        int workingIndex = 0;                   // points to the index where we want o mark or unmark

        while (!"bye".equals(line)) {
            if (line.startsWith("list")) {
                List updatedList = new List(addLists);
                updatedList.printList();
            } else if (line.startsWith("mark")) {
                workingIndex = Integer.parseInt(line.replace("mark", "").trim()) - 1;
                addLists[workingIndex].markAsDone();
                System.out.println(addLists[workingIndex].toString());
            } else if (line.startsWith("unmark")) {
                workingIndex = Integer.parseInt(line.replace("unmark", "").trim()) - 1;
                addLists[workingIndex].markAsUndone();
                System.out.println(addLists[workingIndex].toString());
            } else if (line.startsWith("todo")) {
                lineSplitter = new LineSplitter(line);
                lineSplitter.splitLine();
                addLists[addListsCounter] = new Todo(lineSplitter.getNewDescription());
                addLists[addListsCounter].printAdded();
                System.out.println(addLists[addListsCounter].toString());
                addListsCounter++;
                System.out.println("Now you have " + addListsCounter + " tasks in the list.");
            } else if (line.startsWith("deadline")) {
                lineSplitter = new LineSplitter(line);
                lineSplitter.splitLine();
                addLists[addListsCounter] = new Deadline(lineSplitter.getNewDescription(), lineSplitter.getByOrAt());
                addLists[addListsCounter].printAdded();
                System.out.println(addLists[addListsCounter].toString());
                addListsCounter++;
                System.out.println("Now you have " + addListsCounter + " tasks in the list.");
            } else if (line.startsWith("event")) {
                lineSplitter = new LineSplitter(line);
                lineSplitter.splitLine();
                addLists[addListsCounter] = new Event(lineSplitter.getNewDescription(), lineSplitter.getByOrAt());
                addLists[addListsCounter].printAdded();
                System.out.println(addLists[addListsCounter].toString());
                addListsCounter++;
                System.out.println("Now you have " + addListsCounter + " tasks in the list.");
            } else {
                System.out.println("Invalid input: Please try again.");
            }
            line = in.nextLine().trim();
        }
        System.out.println("Bye. Until we meet again!");
    }
}
