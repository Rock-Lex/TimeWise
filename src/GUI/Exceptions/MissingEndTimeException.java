package GUI.Exceptions;

public class MissingEndTimeException extends Exception {
    public MissingEndTimeException() {
        super("Die Endzeit des Termins muss angegeben werden.");
    }
}