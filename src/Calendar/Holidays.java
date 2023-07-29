package Calendar;

/**
 * @author tobiasrehm
 * Bei dieser Klasse handelt es sich um die Holidays Class zur Berechnung und Festlegung der Feiertage in Berlin.
 *
 * Autor: Tobias Rehm
 * Version: 1.0.0
 * Erstellt am: 21.05.2023
 * Letzte Änderung: 23.05.2023
 */

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Holidays {
	private String name;
	private int year;
	private LocalDate date;

	public Holidays(String name, LocalDate date) {
		this.name = name;
		//	this.year = year;
		this.date = date;
		// verwaltungsklasse integrieren -> Array
	}

	public static LocalDate calculateEasterSunday(int year) {
		int a = year % 19;
		int b = year / 100;
		int c = year % 100;
		int d = b / 4;
		int e = b % 4;
		int f = (b + 8) / 25;
		int g = (b - f + 1) / 3;
		int h = (19 * a + b - d - g + 15) % 30;
		int i = c / 4;
		int k = c % 4;
		int l = (32 + 2 * e + 2 * i - h - k) % 7;
		int m = (a + 11 * h + 22 * l) / 451;
		int n = (h + l - 7 * m + 114) / 31;
		int p = (h + l - 7 * m + 114) % 31;

		int day = p + 1;
		int month = n;

		// return day + month * 100; // Gib den Ostersonntag im Format MMTT zurück

		return LocalDate.of(year, month, day); // Gib den Ostersonntag im Format MMTT zurück
	}

	public static LocalDate BussUndBettag(int year){
		LocalDate nov23 = LocalDate.of(year, 11, 23);

		while(nov23.getDayOfWeek() != DayOfWeek.WEDNESDAY){
			nov23 = nov23.minusDays(1);
		}
		return nov23;
	}

	public String getName() {
		return name;
	}

	public int getYear() {
		return year;
	}

	public LocalDate getDate() {
		return date;
	}
}

