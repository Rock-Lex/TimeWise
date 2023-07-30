package GUI.Exceptions;
/**
 * @author Philipp Voß
 * @version 1.0.0
 * @since 30.05.2023
 * Letzte Änderung: 30.07.2023
 */
class InvalidYearMonthException extends Exception {
    /**
     * Konstruktor für die InvalidYearMonthException. Diese Exception wird ausgelöst, wenn ein ungültiges Jahr oder Monat
     * bei der Terminerstellung angegeben wird. Die spezifische Fehlermeldung wird durch den Parameter message bereitgestellt.
     *
     * @param message Eine genaue Beschreibung des Fehlers, der aufgetreten ist.
     */
    public InvalidYearMonthException(String message) {
        super(message);
    }
}
