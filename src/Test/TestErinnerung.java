import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

public class TestErinnerung {

    @Test
    public void testConstructor_TID0801_constructorWithValidInput_REQ08_createsErinnerung() {
        // Test ID: TID0801
        // Name: constructorWithValidInput
        // Requirement ID: REQ08
        // Input: Valid time, message, isActivated
        // Expected behavior: Erinnerung object created successfully
        // Result: Positive

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
    public void testCompareTo_TID0802_compareTo_REQ08_differentTimes_returnsCorrectValue() {
        // Test ID: TID0802
        // Name: compareTo
        // Requirement ID: REQ08
        // Input: 2 Erinnerung objects with different times
        // Expected behavior: Returns negative if this time < other time,
        //                     positive if this time > other time,
        //                     zero if times are equal
        // Result: Positive

        Erinnerung erinnerung1 = new Erinnerung(LocalDateTime.of(2023, 3, 1, 15, 0), "", true);
        Erinnerung erinnerung2 = new Erinnerung(LocalDateTime.of(2023, 3, 1, 16, 0), "", true);

        assertTrue(erinnerung1.compareTo(erinnerung2) < 0);
        assertTrue(erinnerung2.compareTo(erinnerung1) > 0);
        assertEquals(0, erinnerung1.compareTo(erinnerung1));
    }

}