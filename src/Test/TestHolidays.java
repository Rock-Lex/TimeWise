package Test;

import Calendar.Holidays;
import Calendar.HolidaysList;

/**
 * @author tobiasrehm
 *Bei dieser Test-Klasse handelt es sich um die Test-Klasse für die Klasse Holidays.
 * *
 * Autor: Tobias Rehm
 * Version: 1.0.0
 * Erstellt am: 21.05.2023
 * Letzte Änderung: 23.05.2023
 */

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.util.ArrayList;
import java.util.List;

public class TestHolidays {
	public static void main(String[] args) {
		HolidaysList holidaysList = new HolidaysList(2023);

		for (Holidays holidays1 : holidaysList.getHolidays()) { // Use getHolidays() to get the list of holidays
			System.out.println(holidays1.getName());
			System.out.println(holidays1.getDate());
		}
	}
}
