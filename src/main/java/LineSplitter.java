public class LineSplitter {
    protected String description;
    protected String tempDescription;
    protected String newDescription;
    protected String byOrAt;
    protected String[] arrOfDescription;

    public LineSplitter(String description) {
        this.description = description;
    }

    public void splitLine() {
        if (description.startsWith("todo")) {
            newDescription = description.replace("todo", "").trim();
        } else if (description.startsWith("deadline")) {
            tempDescription = description.replace("deadline", "").trim();
            arrOfDescription = tempDescription.split("/by", 2);
            newDescription = arrOfDescription[0];
            byOrAt = arrOfDescription[1];
        } else if (description.startsWith("event")) {
            tempDescription = description.replace("event", "").trim();
            arrOfDescription = tempDescription.split("/at", 2);
            newDescription = arrOfDescription[0];
            byOrAt = arrOfDescription[1];
        }
    }

    public String getNewDescription() {
        return newDescription;
    }

    public String getByOrAt() {
        return byOrAt;
    }
}
