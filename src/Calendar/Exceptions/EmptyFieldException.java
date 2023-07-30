package Calendar.Exceptions;

/**
 * Eine Ausnahmeklasse, die ausgelöst wird, wenn ein Feld, das einen Wert erfordert, leer gelassen wird.
 * Erbt von IllegalArgumentException.
 *
 * @author Philipp Voß
 * @version 1.0
 * @since 30.07.2023
 */
public class EmptyFieldException extends IllegalArgumentException {

    /**
     * Konstruktor für die EmptyFieldException.
     *
     * Erstellt eine neue Instanz der EmptyFieldException mit einer spezifischen Fehlermeldung.
     *
     * @param msg Die Fehlermeldung, die ausgegeben wird, wenn die Ausnahme geworfen wird.
     */
    public EmptyFieldException(String msg) {
        super(msg);
    }
}

