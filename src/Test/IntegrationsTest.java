package Test;

import static org.junit.Assert.*;
import java.time.LocalDateTime;
import org.junit.Test;
import Calendar.Termin;
import Calendar.Teilnehmer;
import Calendar.TerminListe;
/**
 * Bei dieser Klasse handelt es sich um die Testklasse mit Units für die Klasse TerminListe
 *
 * Autor: Tobias Rehm
 * Version: 1.0.0
 * Erstellt am: 23.05.2023
 * Letzte Änderung: 29.07.2023
 */
public class IntegrationsTest {

    @Test
    public void testAddTerminWithTeilnehmer_TID0701_addTerminWithTeilnehmer_REQ07() {
        // Test ID: TID0701
        // Name: addTerminWithTeilnehmer
        // Requirement ID: REQ07

        // Input: Valid Termin, Teilnehmer, and TerminListe
        // Expected behavior: Termin and Teilnehmer objects created and associated correctly
        // Result: Positive

        // Create Teilnehmer
        Teilnehmer teilnehmer = new Teilnehmer("John Doe", "john@email.com");

        // Create Termin
        LocalDateTime start = LocalDateTime.of(2023, 2, 15, 9, 0);
        LocalDateTime end = LocalDateTime.of(2023, 2, 15, 10, 0);
        Termin termin = new Termin("Meeting", "Meeting", false, start, end);

        // Create TerminListe and add Termin
        TerminListe terminListe = new TerminListe();
        terminListe.addTermin(termin);

        // Assert Termin was added successfully
        assertEquals(1, terminListe.getTermine().size());
        assertEquals(termin, terminListe.getTermine().get(0));

        // Add Teilnehmer to Termin
        termin.getTeilnehmerList().add(teilnehmer);

        // Assert Teilnehmer was added to Termin
        assertEquals(1, termin.getTeilnehmerList().size());
        assertTrue(termin.getTeilnehmerList().contains(teilnehmer));
    }
}