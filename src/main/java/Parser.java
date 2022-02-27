import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The class that describes how the the program parses inputs
 */
public class Parser {
    protected String description;
    protected String tempDescription;
    protected String newDescription;
    protected String byOrAt;
    protected String markOrUnmark;
    protected String[] arrOfDescriptions;
    protected ArrayList<String> fileContentStringLists;
    protected LocalDate date;

    /**
     * Initialises the Parser class
     *
     * @param description Description of a task the the user want to add in the list
     */
    public Parser(String description) {
        this.description = description;
    }

    /**
     * This function parses the FileContent
     */
    public void splitLineFileContent() {
        fileContentStringLists = new ArrayList<String>(Arrays.asList(description.split("---")));
        if (fileContentStringLists.get(0).equalsIgnoreCase("todo")) {
            splitFileFunction();
        } else if (fileContentStringLists.get(0).equalsIgnoreCase("deadline")) {
            deadlineOrEventSplitFileFunction();
        } else if (fileContentStringLists.get(0).equalsIgnoreCase("event")) {
            deadlineOrEventSplitFileFunction();
        }
    }

    /**
     * This is a subfunction of deadlineOrEventSplitFileFunction
     */
    private void splitFileFunction() {
        markOrUnmark = fileContentStringLists.get(1).trim();
        newDescription = fileContentStringLists.get(2).trim();
    }

    /**
     * This function splits the String for event and deadline feature
     */
    private void deadlineOrEventSplitFileFunction() {
        splitFileFunction();
        byOrAt = fileContentStringLists.get(3).trim();
    }

    /**
     * This is the parsing function for the main loop in TaskList
     */
    public void splitLine() {
        if (description.startsWith("todo")) {
            newDescription = description.replace("todo", "").trim();
        } else if (description.startsWith("deadline")) {
            try {
                this.tempDescription = description.replace("deadline", "").trim();
                this.arrOfDescriptions = tempDescription.split("/by", 2);
                this.newDescription = arrOfDescriptions[0];
                this.byOrAt = arrOfDescriptions[1].trim();
                this.date = LocalDate.parse(byOrAt);
            } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                System.out.println("Oh no! Something went wrong.");
            }
        } else if (description.startsWith("event")) {
            try {
                this.tempDescription = description.replace("event", "").trim();
                this.arrOfDescriptions = tempDescription.split("/at", 2);
                this.newDescription = arrOfDescriptions[0];
                this.byOrAt = arrOfDescriptions[1].trim();
                this.date = LocalDate.parse(byOrAt);
            } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                System.out.println("Oh no! Something went wrong.");
            }
        }
    }

    /**
     * Gets the date in LocalDate
     *
     * @return The date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * This is the description that is eventually be seen by the viewer
     *
     * @return The description
     */
    public String getNewDescription() {
        return newDescription;
    }

    /**
     * Gets the date in String
     *
     * @return The date in String
     */
    public String getByOrAt() {
        return byOrAt;
    }

    /**
     * Gets the status of the line that was read if it was previously marked or unmarked
     *
     * @return Mark or Unmark status
     */
    public String getMarkOrUnmark() {
        return markOrUnmark;
    }
}
