import java.time.LocalDateTime;

public class MeterReading {
    private double reading;
    private LocalDateTime dateTime;

    // Constructor
    public MeterReading(double reading, LocalDateTime dateTime) {
        this.reading = reading;
        this.dateTime = dateTime;
    }

    // Getters and setters
    public double getReading() {
        return reading;
    }

    public void setReading(double reading) {
        this.reading = reading;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    // Override toString() method for debugging purposes
    @Override
    public String toString() {
        return "MeterReading{" +
                "reading=" + reading +
                ", dateTime=" + dateTime +
                '}';
    }
}