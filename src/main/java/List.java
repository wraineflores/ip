import java.util.ArrayList;

/**
 * The purpose of this class is to print all the Task in the current list
 */
public class List {
    protected static ArrayList<Task> addLists;

    /**
     * Initialises the List class
     *
     * @param addLists The current list of task in the program
     */
    protected List(ArrayList<Task> addLists) {
        this.addLists = addLists;
    }

    /**
     * printList function prints out all the current tasks in the list
     */
    public static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < addLists.size(); i++) {
            if (addLists.get(i) == null) {
                continue;
            } else {
                System.out.println(Integer.toString(i + 1) + "." + addLists.get(i).toString().trim());
            }
        }
    }
}
