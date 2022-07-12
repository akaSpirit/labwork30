import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Record {
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;

    public Record(LocalDateTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalDateTime getTimeStart() {
        return timeStart;
    }

    public LocalDateTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeStart(LocalDateTime timeStart) {
        this.timeStart = timeStart;
    }

    public void setTimeEnd(LocalDateTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public void printRecord() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy - HH:mm");
        String start = String.format("%s", dtf.format(timeStart));
        String end;
        if (timeEnd != null) end = String.format("%s", dtf.format(timeEnd));
        else end = "Car still on parking";
        System.out.printf(" %19s | %19s |%n", start, end);
        System.out.println("+-------------+---------------------+---------------------+");
    }
}
