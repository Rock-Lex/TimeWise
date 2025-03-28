package Calendar.Exceptions;

/**
 * Eine Ausnahmeklasse, die ausgelöst wird, wenn eine ungültige E-Mail-Adresse eingegeben wird.
 * Erbt von der Java Exception Klasse.
 *
 * @author Philipp Voß
 * @version 1.0
 * @since 30.07.2023
 */
public class InvalidEmailException extends Exception {

    /**
     * Konstruktor für die InvalidEmailException.
     *
     * Erstellt eine neue Instanz der InvalidEmailException mit einer spezifischen Fehlermeldung.
     *
     * @param message Die Fehlermeldung, die ausgegeben wird, wenn die Ausnahme geworfen wird.
     */
    public InvalidEmailException(String message) {
        super(message);
    }
}

