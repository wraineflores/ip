import java.util.ArrayList;

public class List {
    //protected static Object[] addLists;
    protected static ArrayList<Task> addLists;

    protected List(ArrayList<Task> addLists) {
        this.addLists = addLists;
    }

    static void printList() {
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
