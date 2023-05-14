package IDgen;

import java.security.SecureRandom;
import java.util.Locale;

/**
 * Bei dieser Klasse handelt es sich um einen ID Generator für die Erstellung von Termin IDs.
 *
 * Autor: Philipp Voß
 * Version: 1.0
 * Erstellt am: 14.05.2023
 * Letzte Änderung: 14.05.2023
 */
public class IDGenerator {
    private static int counter = 0;
    private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * Generiert eine ID, welche mit dem Termintyp anfängt.
     *
     * @param type Typ des Termins
     * @return
     */
    public static String generateID(String type) {
        counter++;
        String id = type + "_" + counter;
        return id;
    }

    /**
     * Für die Erstellung komplexer IDs. Vermutlich überflüssig und zu einem späteren Zeitpunkt gelöscht.
     * @param length Länge der ID
     * @return
     */
    public static String generateID(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}
