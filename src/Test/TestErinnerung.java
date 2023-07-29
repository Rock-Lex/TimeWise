package Test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import Calendar.Erinnerung;
import org.junit.Test;

public class TestErinnerung {
    /**
     * Bei dieser Klasse handelt es sich um die Testklasse mit Units für die Klasse Erinnerungen.
     *
     * Autor: Tobias Rehm
     * Version: 1.0.0
     * Erstellt am: 23.05.2023
     * Letzte Änderung: 29.07.2023
     */
    @Test
    public void testConstructor_TID0301_constructorWithValidInput_createsErinnerung() {
        // Test ID: TID0301
        // Name: constructorWithValidInput
        // Input: gültige Parameter time, message, isActivated
        // erwartete Ausgabe: erstelltes Objekt Erinnerung
        // Ergebnis: Positiv

        LocalDateTime time = LocalDateTime.of(2023, 3, 1, 15, 30);
        String message = "Team meeting at 3:30 PM";
        Boolean isActivated = true;

        Erinnerung erinnerung = new Erinnerung(time, message, isActivated);

        assertNotNull(erinnerung);
        assertEquals(time, erinnerung.getTime());
        assertEquals(message, erinnerung.getMessage());
        assertEquals(isActivated, erinnerung.isActivated());
    }

    @Test
    public void testCompareTo_TID0302_compareTo_differentTimes_returnsCorrectValue() {
        // Test ID: TID0302
        // Name: compareTo
        // Input: 2 Objekte Erinnerung mit unterschiedlichen Zeiten
        // erwartete Ausgabe: Gibt negativ zurück, wenn: this time < other time; positiv, wenn: this time > other time ist; Null, wenn: die Zeiten gleich sind
        // Ergebnis: Positiv

        Erinnerung erinnerung1 = new Erinnerung(LocalDateTime.of(2023, 3, 1, 15, 0), "", true);
        Erinnerung erinnerung2 = new Erinnerung(LocalDateTime.of(2023, 3, 1, 16, 0), "", true);

        assertTrue(erinnerung1.compareTo(erinnerung2) < 0);
        assertTrue(erinnerung2.compareTo(erinnerung1) > 0);
        assertEquals(0, erinnerung1.compareTo(erinnerung1));
    }

}