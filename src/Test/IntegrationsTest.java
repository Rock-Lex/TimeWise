package Test;

import static org.junit.Assert.*;
import java.time.LocalDateTime;
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

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

import Calendar.Termin;
import Calendar.Teilnehmer;
import Calendar.TerminListe;

public class IntegrationsTest {

    @Test
    public void testAddTerminWithTeilnehmer_TID0201_addTerminWithTeilnehmer() {

        // Test ID: TID0201
        // Name: addTerminWithTeilnehmer

        // Input: gültiger Termin, Teilnehmer, und TerminListe
        // erwartete Ausgabe: Termin und Teilnehmer Objekt erstellen und richtig verbinden
        // Ergebnis: Positiv

        // Erstelle Teilnehmer
        Teilnehmer teilnehmer = new Teilnehmer("John Doe", "john@email.com");

        // Erstelle Termin mit Teilnehmer
        LocalDateTime start = LocalDateTime.of(2023, 2, 15, 9, 0);
        LocalDateTime end = LocalDateTime.of(2023, 2, 15, 10, 0);
        Termin termin = new Termin("Meeting", "Meeting", false, start, end, teilnehmer);

        // Erstelle TerminListe und füge Termin hinzu
        TerminListe terminListe = new TerminListe();
        terminListe.addTermin(termin);

        // Termin wurde erfolgreich hinzugefügt
        assertEquals(1, terminListe.getTermine().size());
        assertEquals(termin, terminListe.getTermine().get(0));

        // Teilnehmer wurde erfolgreich zum Termin hinzugefügt
        assertEquals(1, termin.getTeilnehmerList().size());
        assertTrue(termin.getTeilnehmerList().contains(teilnehmer));
    }
}