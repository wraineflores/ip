package dukepackage;

public class List {
    protected Task[] addLists;

    public List(Task[] addLists) {
        this.addLists = addLists;
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < addLists.length; i++) {
            if (addLists[i] == null) {
                break;
            } else {
                System.out.println(Integer.toString(i + 1) + "." + addLists[i].toString().trim());
            }
        }
    }
}
