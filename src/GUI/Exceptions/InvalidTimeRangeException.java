package GUI.Exceptions;
/**
 * @author Philipp Voß
 * @version 1.0.0
 * @since 30.05.2023
 * Letzte Änderung: 30.07.2023
 */
public class InvalidTimeRangeException extends Exception {
    /**
     * Der Standardkonstruktor für die InvalidTimeRangeException. Die Fehlermeldung ist vordefiniert und informiert den
     * Nutzer, dass die Endzeit eines Termins nicht vor der Startzeit liegen darf.
     */
    public InvalidTimeRangeException() {
        super("Die Endzeit darf nicht vor der Startzeit liegen.");
    }
}
