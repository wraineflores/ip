import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline represents an extension of the Task class
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDate date;

    /**
     * Initialise a new Deadline class
     *
     * @param description Description of a task the the user want to add in the list
     * @param by          Date when the user needs to do the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by.trim();
        parseDate();
    }

    /**
     * Parses from a String to a LocalDate
     */
    public void parseDate() {
        try {
            this.date = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            System.out.println("OOPS!!! Please key in a correct by date.");
        }
    }

    /**
     * Gets the Date in String format
     *
     * @return the date of the instantiated deadline
     */
    public String getBy() {
        return by;
    }

    /**
     * toString was modified for an easier way of viewing
     *
     * @return Deadline in a modified String format
     */
    @Override
    public String toString() {
        return "    [D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " " + date.getDayOfWeek() + ")";
    }
}
