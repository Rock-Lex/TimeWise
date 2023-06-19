package IOManager.Exceptions;

/**
 * Bei dieser Klasse handelt es sich um einen IO Manager Class.
 * *
 * Autor: Oleksandr Kamenskyi
 * Version: 1.0.0
 * Erstellt am: 19.06.2023
 * Letzte Änderung: 19.06.2023
 */

public class WrongPathException extends Exception{
    public WrongPathException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongPathException(String message) {
        super(message);
    }

    public WrongPathException(Throwable cause) {
        super(cause);
    }
}

