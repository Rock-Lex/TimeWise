package GUI.Exceptions;

public class MissingStartDateException extends Exception {
    /**
     * Konstruktor für die MissingStartDateException. Diese Exception wird ausgelöst, wenn für einen Termin kein Startdatum angegeben wird.
     * Die Fehlermeldung "Das Startdatum des Termins muss angegeben werden." wird standardmäßig bereitgestellt.
     */
    public MissingStartDateException() {
        super("Das Startdatum des Termins muss angegeben werden.");
    }
}