import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event represents an extension of the Task class
 */
public class Event extends Task {
    protected String at;
    protected LocalDate date;

    /**
     * Initialise a new Event class
     *
     * @param description Description of a task the the user want to add in the list
     * @param at          Date when the user needs to do the task
     */
    public Event(String description, String at) {
        super(description);
        this.at = at.trim();
        parseDate();
    }

    /**
     * Parses from a String to a LocalDate
     */
    public void parseDate() {
        try {
            this.date = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            System.out.println("OOPS!!! Please key in a correct at date.");
        }
    }

    /**
     * Gets the Date in String format
     *
     * @return the date of the instantiated deadline
     */
    public String getAt() {
        return at;
    }

    /**
     * toString was modified for an easier way of viewing
     *
     * @return Deadline in a modified String format
     */
    @Override
    public String toString() {
        return "    [E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " " + date.getDayOfWeek() + ")";
    }
}
