package GUI.Exceptions;

public class EmptyTypeException extends Exception {
    /**
     * Der Standardkonstruktor für die EmptyTypeException. Die Fehlermeldung ist vordefiniert und informiert den Nutzer,
     * dass der Typ des Termins nicht leer sein darf.
     */
    public EmptyTypeException() {
        super("Der Typ des Termins darf nicht leer sein.");
    }
}
