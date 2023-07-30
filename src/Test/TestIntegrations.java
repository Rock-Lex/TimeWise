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
 * @author Tobias Rehm
 * @version 1.0.0
 * @since  23.05.2023
 * Letzte Änderung: 29.07.2023
 */

public class TestIntegrations {

    @Test
    public void testAddTerminWithTeilnehmer_TID0201() {
        // Test ID: TID0201
        // Name: addTerminWithTeilnehmer
        // Input: valider Teilnehmer, Termin und Termin-Liste
        // erwartete Ausgabe: Teilnehmer, Termin erstellen und zusammenführen
        // Ergebnis: Positiv

        // Erstelle Teilnehmer
        Teilnehmer teilnehmer = new Teilnehmer("John Doe", "john@email.com");

        // Erstelle Termin mit Teilnehmer/////////////////////
        TeilnehmerList teilnehmerList = new TeilnehmerList();
        teilnehmerList.addTeilnehmer(teilnehmer);

        // Erstelle Termin mit Teilnehmer//////////////////////////
        LocalDateTime start = LocalDateTime.of(2023, 2, 15, 9, 0);
        LocalDateTime end = LocalDateTime.of(2023, 2, 15, 10, 0);
        Termin termin = new Termin("Meeting", "Meeting", false, start, end, teilnehmerList);

        // Erstelle TerminListe und füge Termin hinzu
        TerminListe terminListe = new TerminListe();
        terminListe.addTermin(termin);

        // Termin wurde erfolgreich hinzugefügt
        assertEquals(1, terminListe.getTermine().size());
        assertEquals(termin, terminListe.getTermine().get(0));

        // Teilnehmer wurde erfolgreich zum Termin hinzugefügt
        assertEquals(1, termin.getTeilnehmerList().getTeilnehmerList().size());
        assertTrue(termin.getTeilnehmerList().getTeilnehmerList().contains(teilnehmer));

    }

}