package Test;

import org.junit.Test;

import Calendar.Termin;
import Calendar.TerminListe;

import static org.junit.Assert.*;
/**
 * Bei dieser Klasse handelt es sich um die Testklasse mit Units für die Klasse TerminListe.
 *
 *  @author Tobias Rehm
 * @version 1.0.0
 * @since 23.05.2023
 * Letzte Änderung: 29.07.2023
 */
public class TestTerminListe {

    @Test
    public void testConstructor_TID0701_constructor_createsTerminListe() {
        // Test ID: TID0701
        // Name: constructor
        // Input: None
        // erwartete Ausgabe: TerminListe Objekt erfolgreich erstellen
        // Ergebnis: Positiv

        TerminListe terminListe = new TerminListe();

        assertTrue(terminListe instanceof TerminListe);
    }

    @Test
    public void testAddTermin_TID0702_addTermin_validInput_addsTermin() {
        // Test ID: TID0702
        // Name: addTermin
        // Input: valides Objekt Termin
        // erwartete Ausgabe: Termin zur Liste hinzufügen
        // Ergebnis: Positiv

        TerminListe terminListe = new TerminListe();
        Termin termin = new Termin("Meeting", "Meeting", false, "2020-01-01", "2020-01-01", "10:00", "11:00");
        terminListe.addTermin(termin);

        assertEquals(1, terminListe.getTermine().size());
        assertTrue(terminListe.getTermine().contains(termin));
    }

    @Test
    public void testRemoveTermin_TID0703_removeTermin_validInput_removesTermin() {
        // Test ID: TID0703
        // Name: removeTermin
        // Input: valider Termin in Liste
        // erwartete Ausgabe: Termin wird aus der Liste gelöscht
        // Ergebnis: Positiv

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