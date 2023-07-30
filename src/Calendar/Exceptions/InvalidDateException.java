package Calendar.Exceptions;

/**
 * Eine Ausnahmeklasse, die ausgelöst wird, wenn ein ungültiges Datum eingegeben wird.
 * Erbt von IllegalArgumentException.
 *
 * @author Philipp Voß
 * @version 1.0
 * @since 30.07.2023
 */
public class InvalidDateException extends IllegalArgumentException {

    /**
     * Konstruktor für die InvalidDateException.
     *
     * Erstellt eine neue Instanz der InvalidDateException mit einer spezifischen Fehlermeldung.
     *
     * @param msg Die Fehlermeldung, die ausgegeben wird, wenn die Ausnahme geworfen wird.
     */
    public InvalidDateException(String msg) {
        super(msg);
    }
}
