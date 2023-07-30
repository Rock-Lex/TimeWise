package Test;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.time.LocalDateTime;
import org.junit.Test;

import Calendar.*;
/**
 * Bei dieser Klasse handelt es sich um eine Testklasse, die alle Testklassen zusammenfasst.
 *
 * @author Tobias Rehm
 * @version 1.1.0
 * @since 23.05.2023
 * Letzte Änderung: 30.07.2023
 */
public class TestAlle {

    // Integration test

    @Test
    public void testAddTerminWithTeilnehmer() {
        // Test ID: TID0201
        // Name: addTerminWithTeilnehmer
    }

    // Tests für Erinnerung

    @Test
    public void testConstructor_TID0301_constructorWithValidInput_createsErinnerung() {
        // Test ID: TID0301
        // Name: constructorWithValidInput
    }

    @Test
    public void testCompareTo_TID0302_compareTo_differentTimes_returnsCorrectValue() {
        // Test ID: TID0302
        // Name: compareTo
    }

    // Test zur Berechnung Ostersonntag

    @Test
    public void testCalculateEasterSunday_TID0401_calculateEasterSunday_input2020_returnsApril12() {
        // Test ID: TID0401
        // Name: calculateEasterSunday
    }

    @Test
    public void testCalculateEasterSunday_TID0402_calculateEasterSunday_input2023_returnsApril9() {
        // Test ID: TID0402
        // Name: calculateEasterSunday
    }

    @Test
    public void testCalculateBussUndBettag_TID0403_calculateBussUndBettag_input2023_returnsNovember22() {
        // Test ID: TID0403
        // Name: calculateBussUndBettag
    }

    @Test
    public void testHolidaysList_TID0404_getHolidays_input2023_returnsHolidaysList() {
        // Test ID: TID0404
        // Name: getHolidays
    }

    // Tests für Teilnehmer

    @Test
    public void testConstructor_TID0501_constructorWithValidInput_createsTeilnehmer() {
        // Test ID: TID0501
        // Name: constructorWithValidInput
    }

    @Test
    public void testCompareTo_TID0502_compareTo_differentNames_returnsCorrectValue() {
        // Test ID: TID0502
        // Name: compareTo
    }

    // Tests für Termin

    @Test
    public void testConstructor_TID0601_constructorWithValidInputs_inputMeeting20200101to20200102_createsTermin() {
        // Test ID: TID0601
        // Name: constructorWithValidInputs
    }

    @Test
    public void testConstructor_TID0602_constructorWithInvalidInputs_emptyTitleAndType_throwsException() {
        // Test ID: TID0602
        // Name: constructorWithInvalidInputs
    }

    // Tests für TerminListe

    @Test
    public void testConstructor_TID0701_constructor_createsTerminListe() {
        // Test ID: TID0701
        // Name: constructor
    }

    // Test E-Mail Überprüfung

    @Test
    public void testValidEmail_TID0801() {
        // Test ID: TID0801
        // Name: valide email
    }
    @Test
    public void testInvalidEmail_TID0802() {
        // Test ID: TID0802
        // Name: invalide email
    }

    // Test Datum String

    @Test
    public void testValidDateString_TID0901() throws ParseException {
        // Test ID: TID0901
        // Name: valide date String
    }

    @Test
    public void testInvalidDateString_TID0902() {
        // Test ID: TID0902
        // Name: invalide date String
    }

}