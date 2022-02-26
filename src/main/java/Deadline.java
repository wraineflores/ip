import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String by;
    protected LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        this.by = by.trim();
        parseDate();
    }

    public void parseDate() {
        try {
            this.date = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            System.out.println("OOPS!!! Please key in a correct by date.");
        }
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "    [D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " " + date.getDayOfWeek() + ")";
    }
}
