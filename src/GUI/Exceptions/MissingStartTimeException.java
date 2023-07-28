package GUI.Exceptions;

public class MissingStartTimeException extends Exception {
    public MissingStartTimeException() {
        super("Die Startzeit des Termins muss angegeben werden.");
    }
}