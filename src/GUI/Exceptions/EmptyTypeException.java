package GUI.Exceptions;
/**
 * @author Philipp Voß
 * @version 1.0.0
 * @since 30.05.2023
 * Letzte Änderung: 30.07.2023
 */
public class EmptyTypeException extends Exception {
    /**
     * Der Standardkonstruktor für die EmptyTypeException. Die Fehlermeldung ist vordefiniert und informiert den Nutzer,
     * dass der Typ des Termins nicht leer sein darf.
     */
    public EmptyTypeException() {
        super("Der Typ des Termins darf nicht leer sein.");
    }
}
