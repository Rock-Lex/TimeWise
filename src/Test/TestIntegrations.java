package Test;

import static org.junit.Assert.*;
import java.time.LocalDateTime;

import Calendar.TeilnehmerList;
import org.junit.Test;
import Calendar.Termin;
import Calendar.Teilnehmer;
import Calendar.TerminListe;
/**
 * Bei dieser Klasse handelt es sich um die Testklasse,
 * die die Integration zwischen den Klassen Termin, TerminListe und Teilnehmer prüft.
 *
 * Autor: Tobias Rehm
 * Version: 1.0.0
 * Erstellt am: 23.05.2023
 * Letzte Änderung: 29.07.2023
 */

public class TestIntegrations {

    @Test
    public void testAddTerminWithTeilnehmer() {

        // Create Teilnehmer
        Teilnehmer teilnehmer = new Teilnehmer("John Doe", "john@email.com");

        // Create TeilnehmerList and add Teilnehmer
        TeilnehmerList teilnehmerList = new TeilnehmerList();
        teilnehmerList.addTeilnehmer(teilnehmer);

        // Create Termin with TeilnehmerList
        LocalDateTime start = LocalDateTime.of(2023, 2, 15, 9, 0);
        LocalDateTime end = LocalDateTime.of(2023, 2, 15, 10, 0);
        Termin termin = new Termin("Meeting", "Meeting", false, start, end, teilnehmerList);

        // Create TerminListe and add Termin
        TerminListe terminListe = new TerminListe();
        terminListe.addTermin(termin);

        // Verify Termin added successfully
        assertEquals(1, terminListe.getTermine().size());
        assertEquals(termin, terminListe.getTermine().get(0));

        // Verify TeilnehmerList added successfully
        assertEquals(1, termin.getTeilnehmerList().getTeilnehmerList().size());
        assertTrue(termin.getTeilnehmerList().getTeilnehmerList().contains(teilnehmer));

    }

}