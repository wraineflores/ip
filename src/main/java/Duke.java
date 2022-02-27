/**
 * Duke a checklist system that can store & save, add & delete,
 * mark & unmark tasks in the list.
 *
 * @author wraineflores
 * @version 1.0
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Duke {
    /**
     * Duke starts to run as a program
     *
     * @param args Part of the main argument
     * @throws FileNotFoundException Exception thrown in case no file has been previously created
     */
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Task> addLists = new ArrayList<>();
        TaskList.run(addLists);
    }
}
