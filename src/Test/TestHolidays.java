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
	public void testCalculateEasterSunday_TID0101_calculateEasterSunday_REQ01_input2020_returnsApril12() {
		// Test ID: TID0101
		// Name: calculateEasterSunday
		// Requirement ID: REQ01
		// Input: 2020
		// Expected system behavior: Returns LocalDate for Easter Sunday April 12, 2020
		// Result: Positive

		LocalDate expected = LocalDate.of(2020, 4, 12);
		LocalDate actual = Holidays.calculateEasterSunday(2020);
		assertEquals(expected, actual);
	}

	@Test
	public void testCalculateEasterSunday_TID0102_calculateEasterSunday_REQ01_input2023_returnsApril9() {
		// Test ID: TID0102
		// Name: calculateEasterSunday
		// Requirement ID: REQ01
		// Input: 2023
		// Expected system behavior: Returns LocalDate for Easter Sunday April 9, 2023
		// Result: Positive

		LocalDate expected = LocalDate.of(2023, 4, 9);
		LocalDate actual = Holidays.calculateEasterSunday(2023);
		assertEquals(expected, actual);
	}

	@Test
	public void testCalculateBussUndBettag_TID0201_calculateBussUndBettag_REQ02_input2023_returnsNovember22() {
		// Test ID: TID0201
		// Name: calculateBussUndBettag
		// Requirement ID: REQ02
		// Input: 2023
		// Expected system behavior: Returns LocalDate for BussUndBettag November 22, 2023
		// Result: Positive

		LocalDate expected = LocalDate.of(2023, 11, 22);
		LocalDate actual = Holidays.BussUndBettag(2023);
		assertEquals(expected, actual);
	}

}