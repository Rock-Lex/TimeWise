package Test;

import Utilities.DateLabelFormatter;
import org.junit.Test;
import java.text.ParseException;
import static org.junit.Assert.*;

public class TestDateLabelFormatter {
    /**
     * Bei dieser Klasse handelt es sich um die Testklasse mit Units für die Klasse DateLabelFormatter.
     *
     * @author Tobias Rehm
     * version 1.0.0
     * @since 23.05.2023
     * Letzte Änderung: 29.07.2023
     */

    @Test
    public void testValidDateString_TID0901() throws ParseException {
        // Test ID: TID0901
        // Name: valide date String
        // Input: "2023-07-29"
        // erwartete Ausgabe: Datum des 29. Juli 2023
        // Ergebnis: Positiv
        DateLabelFormatter formatter = new DateLabelFormatter();
        Object result = formatter.stringToValue("2023-07-29");
        assertNotNull(result);
    }

    @Test
    public void testInvalidDateString_TID0902() {
        // Test ID: TID0902
        // Name: invalide date String
        // Input: "2023-02-29"
        // erwartete Ausgabe: ParseException
        // Ergebnis: Negativ
        DateLabelFormatter formatter = new DateLabelFormatter();
        try {
            formatter.stringToValue("2023-02-29");
            fail("ParseException expected");
        } catch (ParseException ex) {
        }
    }

}