/**
 * Executes the main Java programme
 * JavaDoc to be updated tomorrow
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Task> addLists = new ArrayList<>();
        TaskList.run(addLists);
    }
}
