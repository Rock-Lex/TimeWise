package Test;

import org.junit.Test;

import Calendar.Termin;
import Calendar.TerminListe;

import static org.junit.Assert.*;
/**
 * Bei dieser Klasse handelt es sich um die Testklasse mit Units für die Klasse TerminListe
 *
 * Autor: Tobias Rehm
 * Version: 1.0.0
 * Erstellt am: 23.05.2023
 * Letzte Änderung: 29.07.2023
 */
public class TestTerminListe {

    @Test
    public void testConstructor_TID0501_constructor_REQ05_createsTerminListe() {
        // Test ID: TID0501
        // Name: constructor
        // Requirement ID: REQ05
        // Input: None
        // Expected behavior: TerminListe object created successfully
        // Result: Positive

        TerminListe terminListe = new TerminListe();

        assertTrue(terminListe instanceof TerminListe);
    }

    @Test
    public void testAddTermin_TID0502_addTermin_REQ05_validInput_addsTermin() {
        // Test ID: TID0502
        // Name: addTermin
        // Requirement ID: REQ05
        // Input: Valid Termin object
        // Expected behavior: Termin added to list
        // Result: Positive

        TerminListe terminListe = new TerminListe();
        Termin termin = new Termin("Meeting", "Meeting", false, "2020-01-01", "2020-01-01", "10:00", "11:00");
        terminListe.addTermin(termin);

        assertEquals(1, terminListe.getTermine().size());
        assertTrue(terminListe.getTermine().contains(termin));
    }

    @Test
    public void testRemoveTermin_TID0503_removeTermin_REQ05_validInput_removesTermin() {
        // Test ID: TID0503
        // Name: removeTermin
        // Requirement ID: REQ05
        // Input: Valid Termin in list
        // Expected behavior: Termin removed from list
        // Result: Positive

        TerminListe terminListe = new TerminListe();
        Termin termin1 = new Termin("Meeting 1", "Meeting", false, "2020-01-01", "2020-01-01", "10:00", "11:00");
        Termin termin2 = new Termin("Meeting 2", "Meeting", false, "2020-01-02", "2020-01-02", "10:00", "11:00");
        terminListe.addTermin(termin1);
        terminListe.addTermin(termin2);

        terminListe.removeTermin(termin1);

        assertEquals(1, terminListe.getTermine().size());
        assertTrue(terminListe.getTermine().contains(termin2));
        assertFalse(terminListe.getTermine().contains(termin1));
    }

}