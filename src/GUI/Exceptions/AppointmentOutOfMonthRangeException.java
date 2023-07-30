package GUI.Exceptions;

public class AppointmentOutOfMonthRangeException extends Exception {
    /**
     * Konstruktor für die AppointmentOutOfMonthRangeException.
     *
     * @param message Die Fehlermeldung, die angezeigt wird, wenn die Exception ausgelöst wird.
     */
    public AppointmentOutOfMonthRangeException(String message) {
        super(message);
    }
}
