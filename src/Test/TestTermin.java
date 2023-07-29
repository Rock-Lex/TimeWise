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
     * Autor: Oleksandr Kamenskyi
     * Version: 1.0.0
     * Erstellt am: 23.05.2023
     * Letzte Änderung: 29.07.2023
     */
        @Test
        public void testConstructor_TID0401_constructorWithValidInputs_REQ04_inputMeeting20200101to20200102_createsTermin() {
            // Test ID: TID0401
            // Name: constructorWithValidInputs
            // Requirement ID: REQ04
            // Input: Meeting from 2020-01-01 to 2020-01-02
            // Expected behavior: Termin object created successfully
            // Result: Positive

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
        public void testConstructor_TID0402_constructorWithInvalidInputs_REQ04_emptyTitleAndType_throwsException() {
            // Test ID: TID0402
            // Name: constructorWithInvalidInputs
            // Requirement ID: REQ04
            // Input: Empty title and type
            // Expected behavior: Throws EmptyFieldException
            // Result: Negative

            Exception exception = assertThrows(EmptyFieldException.class, () -> {
                new Termin("", "", false, "2020-01-01", "2020-01-02", "09:00", "12:00");
            });

            String expectedMessage = "Title und Typ dürfen nicht leer sein.";
            String actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }

    }