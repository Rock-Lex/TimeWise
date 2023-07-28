package GUI.Exceptions;

public class EmptyTypeException extends Exception {
    public EmptyTypeException() {
        super("Der Typ des Termins darf nicht leer sein.");
    }
}
