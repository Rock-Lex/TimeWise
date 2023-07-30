package GUI.Exceptions;
/**
 * @author Philipp Voß
 * @version 1.0.0
 * @since 30.05.2023
 * Letzte Änderung: 30.07.2023
 */
public class MissingStartTimeException extends Exception {
    /**
     * Konstruktor für die MissingStartTimeException. Diese Exception wird ausgelöst, wenn für einen Termin keine Startzeit angegeben wird.
     * Die Fehlermeldung "Die Startzeit des Termins muss angegeben werden." wird standardmäßig bereitgestellt.
     */
    public MissingStartTimeException() {
        super("Die Startzeit des Termins muss angegeben werden.");
    }
}