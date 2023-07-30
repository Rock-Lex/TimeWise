package GUI.Exceptions;

public class InvalidDateRangeException extends Exception {
    /**
     * Der Standardkonstruktor für die InvalidDateRangeException. Die Fehlermeldung ist vordefiniert und informiert den
     * Nutzer, dass das Enddatum eines Termins nicht vor dem Startdatum liegen darf.
     */
    public InvalidDateRangeException() {
        super("Das Enddatum darf nicht vor dem Startdatum liegen.");
    }
}
