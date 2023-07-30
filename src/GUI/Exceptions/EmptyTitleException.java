package GUI.Exceptions;
/**
 * @author Philipp Voß
 * @version 1.0.0
 * @since 30.05.2023
 * Letzte Änderung: 30.07.2023
 */
public class EmptyTitleException extends Exception {
    /**
     * Der Standardkonstruktor für die EmptyTitleException. Die Fehlermeldung ist vordefiniert und informiert den Nutzer,
     * dass der Titel des Termins nicht leer sein darf.
     */
    public EmptyTitleException() {
        super("Der Titel des Termins darf nicht leer sein.");
    }
}
