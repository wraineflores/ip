/**
 * Todo represents an extension of the Task class
 */
public class Todo extends Task {
    /**
     * Initialise a new Todo class
     *
     * @param description Description of a task the the user want to add in the list
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * toString was modified for an easier way of viewing
     *
     * @return Todo class in a modified String format
     */
    @Override
    public String toString() {
        return "    [T]" + super.toString();
    }
}
