package GUI.Exceptions;

public class MissingStartTimeException extends Exception {
    /**
     * Konstruktor für die MissingStartTimeException. Diese Exception wird ausgelöst, wenn für einen Termin keine Startzeit angegeben wird.
     * Die Fehlermeldung "Die Startzeit des Termins muss angegeben werden." wird standardmäßig bereitgestellt.
     */
    public MissingStartTimeException() {
        super("Die Startzeit des Termins muss angegeben werden.");
    }
}