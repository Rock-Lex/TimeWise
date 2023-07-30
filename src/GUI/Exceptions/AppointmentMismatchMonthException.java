package GUI.Exceptions;

public class AppointmentMismatchMonthException extends Exception {
    /**
     * Konstruktor für die AppointmentMismatchMonthException.
     *
     * @param message Die Fehlermeldung, die angezeigt wird, wenn die Exception ausgelöst wird.
     */
    public AppointmentMismatchMonthException(String message) {
        super(message);
    }
}
