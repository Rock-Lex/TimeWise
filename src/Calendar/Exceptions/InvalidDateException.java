package Calendar.Exceptions;

/**
 * Eine Ausnahmeklasse, die ausgelöst wird, wenn ein ungültiges Datum eingegeben wird.
 * Erbt von IllegalArgumentException.
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
