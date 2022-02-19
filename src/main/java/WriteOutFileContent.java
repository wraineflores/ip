import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class WriteOutFileContent {
    protected ArrayList<Task> addLists;

    public WriteOutFileContent(ArrayList<Task> addLists) {
        this.addLists = addLists;
    }

    public void getWriteForAll() throws FileNotFoundException {
        File f = new File("savedDukeData.txt");
        PrintWriter pw = new PrintWriter(f);
        for (Task addlist : addLists) {
            if (addlist instanceof Todo) {
                pw.println("todo---" + addlist.getStatusIcon() + "---" + addlist.getDescription());
            } else if (addlist instanceof Deadline) {
                pw.println("deadline---" + addlist.getStatusIcon() + "---" + addlist.getDescription() + "---" + ((Deadline) addlist).getBy());
            } else if (addlist instanceof Event) {
                pw.println("event---" + addlist.getStatusIcon() + "---" + addlist.getDescription() + "---" + ((Event) addlist).getAt());
            }
        }
        pw.close();
    }
}
