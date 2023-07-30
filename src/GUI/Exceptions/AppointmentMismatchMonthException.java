package GUI.Exceptions;

/**
 * @author Philipp Voß
 * @version 1.0.0
 * @since 30.05.2023
 * Letzte Änderung: 30.07.2023
 */
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
