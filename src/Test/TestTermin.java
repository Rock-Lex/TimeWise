package Test;

import Calendar.Exceptions.EmptyFieldException;
import Calendar.Termin;
import IOManager.Database;

import java.time.LocalDateTime;
import java.util.List;
import java.time.LocalDateTime;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestTermin {
    /**
     * Bei dieser Klasse handelt es sich um die Testklasse mit Units für die Klasse Termin.
     *
     * @author Oleksandr Kamenskyi
     * version  1.0.0
     * @since 23.05.2023
     * Letzte Änderung: 29.07.2023
     */
        @Test
        public void testConstructor_TID0601_constructorWithValidInputs_inputMeeting20200101to20200102_createsTermin() {
            // Test ID: TID0601
            // Name: constructorWithValidInputs
            // Input: Meeting from 2020-01-01 to 2020-01-02
            // erwartete Ausgabe: Erstellt erfolgreich ein Objekt Termin
            // Ergebnis: Positiv

            LocalDateTime start = LocalDateTime.of(2020, 1, 1, 9, 0);
            LocalDateTime end = LocalDateTime.of(2020, 1, 2, 12, 0);
            Termin meeting = new Termin("Daily Standup", "Meeting", true, start, end);

            assertNotNull(meeting);
            assertEquals("Daily Standup", meeting.getTitle());
            assertTrue(meeting.isMultiDay());
            assertEquals(start, meeting.getStart());
            assertEquals(end, meeting.getEnd());
        }

        @Test
        public void testConstructor_TID0602_constructorWithInvalidInputs_emptyTitleAndType_throwsException() {
            // Test ID: TID0602
            // Name: constructorWithInvalidInputs
            // Input: Leerer title und type; nur Start und Ende
            // erwartete Ausgabe: Ausgabe einer EmptyFieldException
            // Ergebnis: Negativ

            Exception exception = assertThrows(EmptyFieldException.class, () -> {
                new Termin("", "", false, "2020-01-01", "2020-01-02", "09:00", "12:00");
            });

            String expectedMessage = "Title und Typ dürfen nicht leer sein.";
            String actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }

    }