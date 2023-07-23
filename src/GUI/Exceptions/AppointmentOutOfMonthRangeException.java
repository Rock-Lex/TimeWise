package GUI.Exceptions;

public class AppointmentOutOfMonthRangeException extends Exception {
    public AppointmentOutOfMonthRangeException(String message) {
        super(message);
    }
}
