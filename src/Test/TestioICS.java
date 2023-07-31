package Test;

import IOManager.ioICS;
import org.junit.Test;

/**
 * Bei dieser Klasse handelt es sich um eine Testklasse für die Datenbank.
 *
 * @author Oleksandr Kamenskyi
 * version 1.0.0
 * @since 28.07.2023
 * Letzte Änderung: 30.07.2023
 */

public class TestioICS {
    ioICS ioi = new ioICS();

    public void testIOICS()
    {
        testExport();
    }

    @Test
    public void testExport() {
        // Test ID TID1101
        // Name: Export Kalender ICS
        // Input: Termin (Start, Ende) mit Bezeichnung, ID, Teilnehmer und Beschreibung
        // erwartete Ausgabe: Erstellt eine ICS Datei im root Directory ausgehend vom Programmcode
        // Ergebnis: Positiv

        ioi.exportICS("test",
                "uid1@example.com",
                "19970714T170000Z",
                "19970714T170000Z",
                "19970715T035959Z",
                "",
                "Termin for test");
    }

}
