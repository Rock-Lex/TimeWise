package GUI.Exceptions;

public class EmptyTitleException extends Exception {
    /**
     * Der Standardkonstruktor für die EmptyTitleException. Die Fehlermeldung ist vordefiniert und informiert den Nutzer,
     * dass der Titel des Termins nicht leer sein darf.
     */
    public EmptyTitleException() {
        super("Der Titel des Termins darf nicht leer sein.");
    }
}
