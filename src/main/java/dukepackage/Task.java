package dukepackage;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        System.out.println("Nice! I've marked this task as done:");
        isDone = true;
    }

    public void markAsUndone() {
        System.out.println("OK, I've marked this task as not done yet:");
        isDone = false;
    }

    public void printAdded() {
        System.out.println("Got it. I've added this task:");
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
