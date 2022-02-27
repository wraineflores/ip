/**
 * Task represents the basic class the a user would want to do in a checklist system
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initialise a new Task class
     *
     * @param description Description of a task the the user want to add in the list
     */
    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    /**
     * Converts the boolean isDone to a String representing "1" or "0"
     *
     * @return The String of "1" or "0"
     */
    public String getStatusIcon() {
        return (isDone ? "1" : "0");
    }

    /**
     * Changes the boolean value of isDone to true
     */
    public void markAsDone() {
        System.out.println("Nice! I've marked this task as done:");
        isDone = true;
    }

    /**
     * Changes the boolean value of isDone to true
     */
    public void setAsDone() {
        isDone = true;
    }

    /**
     * Changes the boolean value of isDone to false
     */
    public void markAsUndone() {
        System.out.println("OK, I've marked this task as not done yet:");
        isDone = false;
    }

    /**
     * Acknowledges that a task was added in the list
     */
    public void printAdded() {
        System.out.println("Got it. I've added this task:");
    }

    /**
     * Gets the description of the Task class
     *
     * @return The description of the task when call on
     */
    public String getDescription() {
        return description;
    }

    /**
     * toString was modified for an easier way of viewing
     *
     * @return Task class in a modified String format
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
