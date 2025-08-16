import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateExample {
    public static void main(String[] args) {
        String dateString = "2024-05-13 14:30:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);
            ZonedDateTime date = localDateTime.atZone(ZoneId.of("Europe/Moscow"));
            System.out.println("Date: " + date);
        } catch (DateTimeParseException e) {
            System.err.println("Date parsing error: " + e.getMessage());
        }
    }
}