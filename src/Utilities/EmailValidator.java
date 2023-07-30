package Utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Diese Klasse bietet einen Mechanismus zur Validierung von E-Mail-Adressen.
 * Sie nutzt eine reguläre Ausdruck (RegEx), um das Muster einer gültigen E-Mail-Adresse zu definieren.
 *
 * Autor: Philipp Voß
 * Version: 1.0
 * Erstellt am: 29.07.2023
 * Letzte Änderung: 29.07.2023
 */
public class EmailValidator {
    /**
     * Definiert das Muster einer gültigen E-Mail-Adresse.
     */
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    /**
     * Erzeugt ein Pattern-Objekt, das später für die Validierung verwendet werden kann.
     */
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    /**
     * Überprüft, ob die angegebene E-Mail-Adresse gültig ist.
     *
     * @param email Die zu überprüfende E-Mail-Adresse.
     * @return 'true' wenn die E-Mail-Adresse gültig ist, 'false' sonst.
     */
    public static boolean validate(final String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
