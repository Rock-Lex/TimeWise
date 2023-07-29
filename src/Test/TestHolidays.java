package Test;

import java.time.LocalDate;

import Calendar.Holidays;
import Calendar.HolidaysList;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Bei dieser Klasse handelt es sich um die Testklasse mit Units für die Klasse Holidays.
 *
 * Autor: Philipp Voß
 * Version: 1.0.0
 * Erstellt am: 23.05.2023
 * Letzte Änderung: 29.07.2023
 */
public class TestHolidays {

	@Test
	public void testCalculateEasterSunday_TID0401_calculateEasterSunday_input2020_returnsApril12() {
		// Test ID: TID0401
		// Name: calculateEasterSunday
		// Input: year = 2020
		// erwartete Ausgabe: Rückgabe von LocalDate für den Ostersonntag 2020: 12. April
		// Ergebnis: Positiv

		LocalDate expected = LocalDate.of(2020, 4, 12);
		LocalDate actual = Holidays.calculateEasterSunday(2020);
		assertEquals(expected, actual);
	}

	@Test
	public void testCalculateEasterSunday_TID0402_calculateEasterSunday_input2023_returnsApril9() {
		// Test ID: TID0402
		// Name: calculateEasterSunday
		// Input: 2023
		// erwartete Ausgabe: Rückgabe von LocalDate für den Ostersonntag 2023: April 9
		// Ergebnis: Positiv

		LocalDate expected = LocalDate.of(2023, 4, 9);
		LocalDate actual = Holidays.calculateEasterSunday(2023);
		assertEquals(expected, actual);
	}

	@Test
	public void testCalculateBussUndBettag_TID0403_calculateBussUndBettag_input2023_returnsNovember22() {
		// Test ID: TID0403
		// Name: calculateBussUndBettag
		// Input: 2023
		// erwartete Ausgabe: Rückgabe von LocalDate für den Buß- und Bettag 2023: November 22
		// Ergebnis: Positiv

		LocalDate expected = LocalDate.of(2023, 11, 22);
		LocalDate actual = Holidays.BussUndBettag(2023);
		assertEquals(expected, actual);
	}

	@Test
	public void testHolidaysList_TID0404_getHolidays_input2023_returnsHolidaysList() {
		// Test ID: TID0404
		// Name: getHolidays
		// Input: 2023
		// erwartete Ausgabe: Rückgabe von Liste der Feiertage für das Jahr 2023
		// Ergebnis: Positiv

		HolidaysList holidaysList = new HolidaysList(2023);
		assertFalse(holidaysList.getHolidays().isEmpty());
		assertEquals(2023, holidaysList.getHolidays().get(0).getYear());
	}

}