import java.util.Scanner;

public class Duke {
    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings("DM_DEFAULT_ENCODING")
    public static void main(String[] args) {
        System.out.println("Hello! I'm Earl Grey\nWhat can I do for you?");

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!"bye".equals(line)) {
            System.out.println(line);
            line = in.nextLine();
        }

        System.out.println("Bye. Until we meet again!");
    }
}
