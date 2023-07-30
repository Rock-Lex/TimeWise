package IOManager.Exceptions;

/**
 * Bei dieser Klasse handelt es sich um einen IO Manager Class.
 * *
 * @author Oleksandr Kamenskyi
 * @version 1.0.0
 * @since 19.06.2023
 * Letzte Änderung: 19.06.2023
 */

public class WrongPathException extends Exception{
    /**
     * Erzeugt eine neue WrongPathException mit der angegebenen Detailnachricht und Ursache.
     *
     * @param message Die Detailnachricht (wird später durch Throwable.getMessage() abgerufen).
     * @param cause Die Ursache (wird später durch Throwable.getCause() abgerufen). Ein null-Wert ist zulässig und zeigt an, dass die Ursache nicht vorhanden ist oder unbekannt ist.
     */
    public WrongPathException(String message, Throwable cause) {
        super(message, cause);
    }
    /**
     * Erzeugt eine neue WrongPathException mit der angegebenen Detailnachricht.
     *
     * @param message Die Detailnachricht (wird später durch Throwable.getMessage() abgerufen).
     */
    public WrongPathException(String message) {
        super(message);
    }
    /**
     * Erzeugt eine neue WrongPathException mit der angegebenen Ursache.
     *
     * @param cause Die Ursache (wird später durch Throwable.getCause() abgerufen). Ein null-Wert ist zulässig und zeigt an, dass die Ursache nicht vorhanden ist oder unbekannt ist.
     */
    public WrongPathException(Throwable cause) {
        super(cause);
    }
}

