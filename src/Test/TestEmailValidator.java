package Test;

import Utilities.EmailValidator;
import org.junit.Test;
import static org.junit.Assert.*;
public class TestEmailValidator {
    /**
     * Bei dieser Klasse handelt es sich um die Testklasse mit Units für die Klasse EmailValidator.
     *
     * @author Tobias Rehm
     * version 1.0.0
     * @since 23.05.2023
     * Letzte Änderung: 29.07.2023
     */

    @Test
    public void testValidEmail_TID0801() {
        // Test ID: TID0801
        // Name: valide email
        // Input: "test@example.com"
        // erwartete Ausgabe: Wahr
        // Ergebnis: Positiv
        boolean result = EmailValidator.validate("test@example.com");
        assertTrue(result);
    }

    @Test
    public void testInvalidEmail_TID0802() {
        // Test ID: TID0802
        // Name: invalide email
        // Input: "testexample.com"
        // erwartete Ausgabe: Falsch
        // Ergebnis: Negativ
        boolean result = EmailValidator.validate("testexample.com");
        assertFalse(result);
    }

}