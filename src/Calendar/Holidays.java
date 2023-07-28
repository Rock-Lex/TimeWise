package Calendar;



import java.time.DayOfWeek;
import java.time.LocalDate;
/**
 * Eine Klasse zur Berechnung und Verwaltung der Feiertage in Berlin.
 *
 * Autor: Tobias Rehm
 * Version: 1.0.0
 * Erstellt am: 21.05.2023
 * Letzte Änderung: 23.05.2023
 */
public class Holidays {
	private String name;
	private int year;
	private LocalDate date;
	/**
	 * Konstruktor für die Erstellung eines Feiertags.
	 *
	 * @param name Der Name des Feiertags.
	 * @param date Das Datum des Feiertags als LocalDate.
	 */
	public Holidays(String name, LocalDate date) {
		this.name = name;
		//	this.year = year;
		this.date = date;
		// verwaltungsklasse integrieren -> Array
	}
	/**
	 * Berechnet den Ostersonntag für ein bestimmtes Jahr.
	 *
	 * @param year Das Jahr, für das der Ostersonntag berechnet werden soll.
	 * @return Das Datum des Ostersonntags als LocalDate.
	 */
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
	/**
	 * Berechnet den Buß- und Bettag für ein bestimmtes Jahr.
	 *
	 * @param year Das Jahr, für das der Buß- und Bettag berechnet werden soll.
	 * @return Das Datum des Buß- und Bettags als LocalDate.
	 */
	public static LocalDate BussUndBettag(int year){
		LocalDate nov23 = LocalDate.of(year, 11, 23);

		while(nov23.getDayOfWeek() != DayOfWeek.WEDNESDAY){
			nov23 = nov23.minusDays(1);
		}
		return nov23;
	}
	/**
	 * @return Der Name des Feiertags.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return Das Jahr des Feiertags.
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @return Das Datum des Feiertags als LocalDate.
	 */
	public LocalDate getDate() {
		return date;
	}
}

