import java.util.ArrayList;
import java.util.Arrays;

public class LineSplitter {
    protected String description;
    protected String tempDescription;
    protected String newDescription;
    protected String byOrAt;
    protected String markOrUnmark;
    protected String[] arrOfDescription;
    protected ArrayList<String> fileContentStringLists;

    public LineSplitter(String description) {
        this.description = description;
    }

    public void splitLineFileContent() {
        fileContentStringLists = new ArrayList<String>(Arrays.asList(description.split("---")));
        if (fileContentStringLists.get(0).equalsIgnoreCase("todo")) {
            markOrUnmark = fileContentStringLists.get(1).trim();
            newDescription = fileContentStringLists.get(2).trim();
        } else if (fileContentStringLists.get(0).equalsIgnoreCase("deadline")) {
            markOrUnmark = fileContentStringLists.get(1).trim();
            newDescription = fileContentStringLists.get(2).trim();
            byOrAt = fileContentStringLists.get(3).trim();
        } else if (fileContentStringLists.get(0).equalsIgnoreCase("event")) {
            markOrUnmark = fileContentStringLists.get(1).trim();
            newDescription = fileContentStringLists.get(2).trim();
            byOrAt = fileContentStringLists.get(3).trim();
        }
    }

    public void splitLine() {
        if (description.startsWith("todo")) {
            newDescription = description.replace("todo", "").trim();
        } else if (description.startsWith("deadline")) {
            try {
                this.tempDescription = description.replace("deadline", "").trim();
                this.arrOfDescription = tempDescription.split("/by", 2);
                this.newDescription = arrOfDescription[0];
                this.byOrAt = arrOfDescription[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Oh no! Something went wrong.");
            }
        } else if (description.startsWith("event")) {
            try {
                this.tempDescription = description.replace("event", "").trim();
                this.arrOfDescription = tempDescription.split("/at", 2);
                this.newDescription = arrOfDescription[0];
                this.byOrAt = arrOfDescription[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Oh no! Something went wrong.");
            }
        }
    }

    public String getNewDescription() {
        return newDescription;
    }

    public String getByOrAt() {
        return byOrAt;
    }

    public String getMarkOrUnmark() {
        return markOrUnmark;
    }
}
