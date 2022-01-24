import java.util.Scanner;

public class Duke {
    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings("DM_DEFAULT_ENCODING")
    public static void main(String[] args) {
        System.out.println("Hello! I'm Earl Grey\nWhat can I do for you?");

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine().trim();

        Task[] addLists = new Task[100];
        int addListsCounter = 0;
        int addListPrintCounter = 1;

        while (!"bye".equals(line)) {
            if ("list".equals(line)) {
                System.out.println("Here are the tasks in your list:");
                for (Task addList : addLists) {
                    if (addList == null) {
                        break;
                    } else {
                        System.out.println(Integer.toString(addListPrintCounter) + ".[" + addList.getStatusIcon() + "]" + addList.getDescription());
                        addListPrintCounter++;
                    }
                }
                addListPrintCounter = 1;
            } else if (line.startsWith("mark")) {
                System.out.println("Nice! I've marked this task as done:");
                Task addList = addLists[Integer.parseInt(line.substring(5)) - 1];
                addList.markAsDone();
                System.out.println("    [" + addList.getStatusIcon() + "] " + addList.getDescription());
            } else if (line.startsWith("unmark")) {
                System.out.println("OK, I've marked this task as not done yet:");
                Task addList = addLists[Integer.parseInt(line.substring(7)) - 1];
                addList.markAsUndone();
                System.out.println("    [" + addList.getStatusIcon() + "] " + addList.getDescription());
            } else {
                Task t = new Task(line);
                addLists[addListsCounter] = t;
                addListsCounter++;
                System.out.println("added: " + line);
            }
            line = in.nextLine().trim();
        }

        System.out.println("Bye. Until we meet again!");
    }
}
