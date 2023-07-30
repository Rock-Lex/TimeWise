package GUI.Exceptions;
/**
 * @author Philipp Voß
 * @version 1.0.0
 * @since 30.05.2023
 * Letzte Änderung: 30.07.2023
 */
public class InvalidDateRangeException extends Exception {
    /**
     * Der Standardkonstruktor für die InvalidDateRangeException. Die Fehlermeldung ist vordefiniert und informiert den
     * Nutzer, dass das Enddatum eines Termins nicht vor dem Startdatum liegen darf.
     */
    public InvalidDateRangeException() {
        super("Das Enddatum darf nicht vor dem Startdatum liegen.");
    }
}
