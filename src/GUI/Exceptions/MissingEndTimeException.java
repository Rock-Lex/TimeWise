package GUI.Exceptions;

public class MissingEndTimeException extends Exception {
    /**
     * Konstruktor für die MissingEndTimeException. Diese Exception wird ausgelöst, wenn für einen Termin keine Endzeit angegeben wird.
     * Die Fehlermeldung "Die Endzeit des Termins muss angegeben werden." wird standardmäßig bereitgestellt.
     */
    public MissingEndTimeException() {
        super("Die Endzeit des Termins muss angegeben werden.");
    }
}