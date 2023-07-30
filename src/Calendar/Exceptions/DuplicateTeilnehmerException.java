package Calendar.Exceptions;

/**
 * Eine Ausnahmeklasse, die ausgelöst wird, wenn versucht wird, einen Teilnehmer hinzuzufügen, der bereits existiert.
 * Erbt von der Java Exception Klasse.
 *
 * @author Philipp Voß
 * @version 1.0
 * @since 30.07.2023
 */
public class DuplicateTeilnehmerException extends Exception {
    /**
     * Konstruktor für die DuplicateTeilnehmerException.
     *
     * Erstellt eine neue Instanz der DuplicateTeilnehmerException mit einer spezifischen Fehlermeldung.
     *
     * @param message Die Fehlermeldung, die ausgegeben wird, wenn die Ausnahme geworfen wird.
     */
    public DuplicateTeilnehmerException(String message) {
        super(message);
    }
}
