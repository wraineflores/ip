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
}
