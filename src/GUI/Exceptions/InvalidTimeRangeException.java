package GUI.Exceptions;

public class InvalidTimeRangeException extends Exception {
    public InvalidTimeRangeException() {
        super("Die Endzeit darf nicht vor der Startzeit liegen.");
    }
}
