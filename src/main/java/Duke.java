import java.util.Scanner;

public class Duke {
    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings("DM_DEFAULT_ENCODING")
    public static void main(String[] args) {
        System.out.println("Hello! I'm Earl Grey\nWhat can I do for you?");

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        String[] addLists = new String[100];
        int addListsCounter = 0;
        int addListPrintCounter = 1;

        while (!"bye".equals(line)) {
            if (!"list".equals(line)) {
                addLists[addListsCounter] = line;
                addListsCounter++;
                System.out.println("added: " + line);
            } else {
                for (String addList : addLists) {
                    if (addList == null) {
                        break;
                    } else {
                        System.out.println(Integer.toString(addListPrintCounter) + ". " + addList);
                        addListPrintCounter++;
                    }
                }
                addListPrintCounter = 1;
            }
            line = in.nextLine();
        }

        System.out.println("Bye. Until we meet again!");
    }
}
