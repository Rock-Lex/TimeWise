package IDgen;

import java.security.SecureRandom;
import java.util.Locale;

/**
 * Diese Klasse bietet Methoden zur Erstellung von eindeutigen Identifikationsnummern (IDs) für Termine.
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
     * Generiert eine eindeutige ID für einen Termin, die aus dem Termintyp und einer fortlaufenden Zählervariable besteht.
     *
     * @param type Typ des Termins, der als Präfix für die ID verwendet wird
     * @return Eine eindeutige ID, die aus dem Termintyp und einer fortlaufenden Zählervariable besteht
     */
    public static String generateID(String type) {
        counter++;
        String id = type + "_" + counter;
        return id;
    }

    /**
     * Generiert eine komplexere, potenziell eindeutige ID, die aus einer zufälligen Zeichenkette besteht.
     * Dies ist möglicherweise überflüssig und könnte in der Zukunft entfernt werden.
     *
     * @param length Die gewünschte Länge der generierten ID
     * @return Eine zufällige Zeichenkette der angegebenen Länge, die als ID verwendet werden kann
     */
    public static String generateID(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}