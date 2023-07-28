package GUI.Exceptions;

public class EmptyTitleException extends Exception {
    public EmptyTitleException() {
        super("Der Titel des Termins darf nicht leer sein.");
    }
}
