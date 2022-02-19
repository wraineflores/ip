import java.util.ArrayList;
import java.util.Arrays;

public class InsertFileContent {
    protected String fileContent;
    protected ArrayList<Task> addLists;
    protected ArrayList<String> fileContentLists;

    public InsertFileContent(String fileContent, ArrayList<Task> addLists) {
        this.fileContent = fileContent;
        this.addLists = addLists;
        this.fileContentLists = new ArrayList<String>(Arrays.asList(fileContent.split("\n")));
    }

    public ArrayList<Task> getAddLists() {
        for (String fileContentList : fileContentLists) {
            if (fileContentList.startsWith("todo")) {
                todoFunc(fileContentList, addLists);
            } else if (fileContentList.startsWith("deadline")) {
                deadlineFunc(fileContentList, addLists);
            } else if (fileContentList.startsWith("event")) {
                eventFunc(fileContentList, addLists);
            }
        }
        return addLists;
    }

    private static void todoFunc(String line, ArrayList<Task> addLists) {
        LineSplitter lineSplitter;
        lineSplitter = getLineSplitter(line);
        Todo newTodo = new Todo(lineSplitter.getNewDescription());
        if (lineSplitter.getMarkOrUnmark().equalsIgnoreCase("1")) {
            newTodo.setAsDone();
        }
        addLists.add(newTodo);
    }

    private static void deadlineFunc(String line, ArrayList<Task> addLists) {
        LineSplitter lineSplitter;
        lineSplitter = getLineSplitter(line);
        Event newDeadline = new Event(lineSplitter.getNewDescription(), lineSplitter.getByOrAt());
        if (lineSplitter.getMarkOrUnmark().equalsIgnoreCase("1")) {
            newDeadline.setAsDone();
        }
        addLists.add(newDeadline);
    }

    private static void eventFunc(String line, ArrayList<Task> addLists) {
        LineSplitter lineSplitter;
        lineSplitter = getLineSplitter(line);
        Event newEvent = new Event(lineSplitter.getNewDescription(), lineSplitter.getByOrAt());
        if (lineSplitter.getMarkOrUnmark().equalsIgnoreCase("1")) {
            newEvent.setAsDone();
        }
        addLists.add(newEvent);
    }

    private static LineSplitter getLineSplitter(String line) {
        LineSplitter lineSplitter;
        lineSplitter = new LineSplitter(line);
        lineSplitter.splitLineFileContent();
        return lineSplitter;
    }
}
