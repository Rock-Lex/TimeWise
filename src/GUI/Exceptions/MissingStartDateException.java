package GUI.Exceptions;

public class MissingStartDateException extends Exception {
    public MissingStartDateException() {
        super("Das Startdatum des Termins muss angegeben werden.");
    }
}