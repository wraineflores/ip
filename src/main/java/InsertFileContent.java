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
        Parser parser;
        parser = getParser(line);
        Todo newTodo = new Todo(parser.getNewDescription());
        if (parser.getMarkOrUnmark().equalsIgnoreCase("1")) {
            newTodo.setAsDone();
        }
        addLists.add(newTodo);
    }

    private static void deadlineFunc(String line, ArrayList<Task> addLists) {
        Parser parser;
        parser = getParser(line);
        Event newDeadline = new Event(parser.getNewDescription(), parser.getByOrAt());
        if (parser.getMarkOrUnmark().equalsIgnoreCase("1")) {
            newDeadline.setAsDone();
        }
        addLists.add(newDeadline);
    }

    private static void eventFunc(String line, ArrayList<Task> addLists) {
        Parser parser;
        parser = getParser(line);
        Event newEvent = new Event(parser.getNewDescription(), parser.getByOrAt());
        if (parser.getMarkOrUnmark().equalsIgnoreCase("1")) {
            newEvent.setAsDone();
        }
        addLists.add(newEvent);
    }

    private static Parser getParser(String line) {
        Parser parser;
        parser = new Parser(line);
        parser.splitLineFileContent();
        return parser;
    }
}
