package Test;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.time.LocalDateTime;
import org.junit.Test;

import Test.*;
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

    public void testAlle() throws ParseException {
        // Integration test
        TestIntegrations testIntegrations = new TestIntegrations();

        // Test ID: TID0201
        // Name: addTerminWithTeilnehmer
        testIntegrations.testAddTerminWithTeilnehmer_TID0201();

        // Tests für Erinnerung
        TestErinnerung testErinnerung = new TestErinnerung();

        // Test ID: TID0301
        // Name: constructorWithValidInput
        testErinnerung.testConstructor_TID0301_constructorWithValidInput_createsErinnerung();

        // Test ID: TID0302
        // Name: compareTo
        testErinnerung.testCompareTo_TID0302_compareTo_differentTimes_returnsCorrectValue();

        // Test zur Berechnung Ostersonntag
        TestHolidays testHolidays = new TestHolidays();

        // Test ID: TID0401
        // Name: calculateEasterSunday
        testHolidays.testCalculateEasterSunday_TID0401_calculateEasterSunday_input2020_returnsApril12();

        // Test ID: TID0402
        // Name: calculateEasterSunday
        testHolidays.testCalculateEasterSunday_TID0402_calculateEasterSunday_input2023_returnsApril9();

        // Test ID: TID0403
        // Name: calculateBussUndBettag
        testHolidays.testCalculateBussUndBettag_TID0403_calculateBussUndBettag_input2023_returnsNovember22();

        // Test ID: TID0404
        // Name: getHolidays
        testHolidays.testHolidaysList_TID0404_getHolidays_input2023_returnsHolidaysList();

        // Tests für Teilnehmer
        TestTeilnehmer testTeilnehmer = new TestTeilnehmer();

        // Test ID: TID0501
        // Name: constructorWithValidInput
        testTeilnehmer.testConstructor_TID0501_constructorWithValidInput_createsTeilnehmer();

        // Test ID: TID0502
        // Name: compareTo
        testTeilnehmer.testCompareTo_TID0502_compareTo_differentNames_returnsCorrectValue();

        // Tests für Termin
        TestTermin testTermin = new TestTermin();

        // Test ID: TID0601
        // Name: constructorWithValidInputs
        testTermin.testConstructor_TID0601_constructorWithValidInputs_inputMeeting20200101to20200102_createsTermin();

        // Test ID: TID0602
        // Name: constructorWithInvalidInputs
        testTermin.testConstructor_TID0602_constructorWithInvalidInputs_emptyTitleAndType_throwsException();

        // Tests für TerminListe
        TestTerminListe testTerminListe = new TestTerminListe();

        // Test ID: TID0701
        // Name: constructor
        testTerminListe.testConstructor_TID0701_constructor_createsTerminListe();

        // Test ID: TID0702
        // Name: addTermin
        testTerminListe.testAddTermin_TID0702_addTermin_validInput_addsTermin();

        // Test ID: TID0703
        // Name: removeTermin
        testTerminListe.testRemoveTermin_TID0703_removeTermin_validInput_removesTermin();

        // Test E-Mail Überprüfung
        TestEmailValidator testEmailValidator = new TestEmailValidator();

        // Test ID: TID0801
        // Name: valide email
        testEmailValidator.testValidEmail_TID0801();

        // Test ID: TID0802
        // Name: invalide email
        testEmailValidator.testInvalidEmail_TID0802();

        // Test Datum String (DateLabelFormatter)
        TestDateLabelFormatter testDateLabelFormatter = new TestDateLabelFormatter();

        // Test ID: TID0901
        // Name: valide date String
        testDateLabelFormatter.testValidDateString_TID0901();

        //Test ID: TID0902
        // Name: invalide date String
        testDateLabelFormatter.testInvalidDateString_TID0902();

        // Test Datenbank
        TestDatenbank testDatenbank = new TestDatenbank();

        // Test ID: TID1001
        // Name: Funktion, alle Funktionen in der Test Klasse TestDatenbank aufzurufen
        testDatenbank.testDatenbank();

        // Test Export und Import
        TestioICS testioICS = new TestioICS();

        // Test ID TID1101
        // Name: Export Kalender ICS
        testioICS.testIOICS();

    }

    public static void main(String[] args) throws ParseException {
        TestAlle testAlle = new TestAlle();
        testAlle.testAlle();
    }

}
