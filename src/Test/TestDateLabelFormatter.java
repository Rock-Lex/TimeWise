package Test;

import GUI.Utilities.DateLabelFormatter;
import org.junit.Test;
import java.text.ParseException;
import static org.junit.Assert.*;

public class TestDateLabelFormatter {
    /**
     * Bei dieser Klasse handelt es sich um die Testklasse mit Units für die Klasse DateLabelFormatter.
     *
     * Autor: Tobias Rehm
     * Version: 1.0.0
     * Erstellt am: 23.05.2023
     * Letzte Änderung: 29.07.2023
     */

    @Test
    public void testValidDateString_TID0901() throws ParseException {
        // Test ID: TID0901
        // Name: Valid date string
        // Input: "2023-07-29"
        // erwartete Ausgabe: Date object for July 29, 2023
        // Ergebnis: Positiv
        DateLabelFormatter formatter = new DateLabelFormatter();
        Object result = formatter.stringToValue("2023-07-29");
        assertNotNull(result);
        // additional assertions to check date details
    }

    @Test
    public void testInvalidDateString_TID0902() {
        // Test ID: TID0902
        // Name: Invalid date string
        // Input: "2023-02-29"
        // erwartete Ausgabe: ParseException
        // Ergebnis: Negativ
        DateLabelFormatter formatter = new DateLabelFormatter();
        try {
            formatter.stringToValue("2023-02-29");
            fail("ParseException expected");
        } catch (ParseException ex) {
            // expected
        }
    }

}