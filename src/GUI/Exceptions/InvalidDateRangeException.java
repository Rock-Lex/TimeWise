package GUI.Exceptions;

public class InvalidDateRangeException extends Exception {
    public InvalidDateRangeException() {
        super("Das Enddatum darf nicht vor dem Startdatum liegen.");
    }
}
