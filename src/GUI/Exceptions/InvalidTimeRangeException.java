package GUI.Exceptions;

public class InvalidTimeRangeException extends Exception {
    /**
     * Der Standardkonstruktor für die InvalidTimeRangeException. Die Fehlermeldung ist vordefiniert und informiert den
     * Nutzer, dass die Endzeit eines Termins nicht vor der Startzeit liegen darf.
     */
    public InvalidTimeRangeException() {
        super("Die Endzeit darf nicht vor der Startzeit liegen.");
    }
}
