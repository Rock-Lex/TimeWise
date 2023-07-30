package GUI.Exceptions;
/**
 * @author Philipp Voß
 * @version 1.0.0
 * @since 30.05.2023
 * Letzte Änderung: 30.07.2023
 */
public class MissingStartDateException extends Exception {
    /**
     * Konstruktor für die MissingStartDateException. Diese Exception wird ausgelöst, wenn für einen Termin kein Startdatum angegeben wird.
     * Die Fehlermeldung "Das Startdatum des Termins muss angegeben werden." wird standardmäßig bereitgestellt.
     */
    public MissingStartDateException() {
        super("Das Startdatum des Termins muss angegeben werden.");
    }
}