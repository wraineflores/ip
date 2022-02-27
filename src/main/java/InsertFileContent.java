import java.util.ArrayList;
import java.util.Arrays;

/**
 * InsertFileContent represents the insertion of the file that is read to the main program
 */
public class InsertFileContent {
    protected String fileContent;
    protected ArrayList<Task> addLists;
    protected ArrayList<String> fileContentLists;

    /**
     * Initialise the InsertFileContent class
     *
     * @param fileContent The contents of the file as one long string
     * @param addLists    The main list of the program
     */
    public InsertFileContent(String fileContent, ArrayList<Task> addLists) {
        this.fileContent = fileContent;
        this.addLists = addLists;
        this.fileContentLists = new ArrayList<String>(Arrays.asList(fileContent.split("\n")));
    }

    /**
     * Gets the main list from fileContentLists
     *
     * @return The main list back
     */
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

    /**
     * The todo function is called when a line needs to be processed
     *
     * @param line     The next line that needs to be processed then added into the main list
     * @param addLists The main list of the program
     */
    private static void todoFunc(String line, ArrayList<Task> addLists) {
        Parser parser;
        parser = getParser(line);
        Todo newTodo = new Todo(parser.getNewDescription());
        if (parser.getMarkOrUnmark().equalsIgnoreCase("1")) {
            newTodo.setAsDone();
        }
        addLists.add(newTodo);
    }

    /**
     * The deadline function is called when a line needs to be processed
     *
     * @param line     The next line that needs to be processed then added into the main list
     * @param addLists The main list of the program
     */
    private static void deadlineFunc(String line, ArrayList<Task> addLists) {
        Parser parser;
        parser = getParser(line);
        Event newDeadline = new Event(parser.getNewDescription(), parser.getByOrAt());
        if (parser.getMarkOrUnmark().equalsIgnoreCase("1")) {
            newDeadline.setAsDone();
        }
        addLists.add(newDeadline);
    }

    /**
     * The event function is called when a line needs to be processed
     *
     * @param line     The next line that needs to be processed then added into the main list
     * @param addLists The main list of the program
     */
    private static void eventFunc(String line, ArrayList<Task> addLists) {
        Parser parser;
        parser = getParser(line);
        Event newEvent = new Event(parser.getNewDescription(), parser.getByOrAt());
        if (parser.getMarkOrUnmark().equalsIgnoreCase("1")) {
            newEvent.setAsDone();
        }
        addLists.add(newEvent);
    }

    /**
     * Gets the parsed object class from the line
     *
     * @param line The next line that needs to be processed then added into the main list
     * @return A parsed class object
     */
    private static Parser getParser(String line) {
        Parser parser;
        parser = new Parser(line);
        parser.splitLineFileContent();
        return parser;
    }
}
