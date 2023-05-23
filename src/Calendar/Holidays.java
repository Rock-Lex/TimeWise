package Calendar;

/**
 * @author tobiasrehm
 *Bei dieser Klasse handelt es sich um die Holidays Class zur Berechnung und Festlegung der Feiertage in Berlin.
 * *
 * Autor: Tobias Rehm
 * Version: 1.0.0
 * Erstellt am: 21.05.2023
 * Letzte Änderung: 23.05.2023
 */

public class Holidays {

	private String name;
	private int month;
	private int day;
//	private int year;

	public Holidays(String name, int month, int day) {
		this.name = name;
//		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	public void display() {
		System.out.println("\tFeiertag: " + this.name);
//		System.out.println("\tFeiertag: " + this.year);
		System.out.println("\tFeiertag: " + this.month);
		System.out.println("\tFeiertag: " + this.day);
	}
	
	/*public int getYear() {
		return year;
	}
	
	public void setYear(int stand) {
		this.year = year;
	}
*/	
	
	public void Osterfeiertage() {
		int year = 2023;

		int a = year % 4;
		int b = year % 7;
		int c = year % 19;
		int d = (19 * c + 24) % 30;
		int e = (2 * a + 4 * b + 6 * d + 5) % 7;
		int f = (c + 11 * d + 22 * e) / 451;
		
		int Ostern = 22 + d + e - (7 * f);
		
		int Ostersonntag = Ostern;
			if(Ostern > 31) {
				int z = 22 + d + e - (7 * f) - 9;
			}
			
			System.out.println(Ostersonntag);
	}
	
	public void print() {
		System.out.println(name + " " + day + "." + month + ".");
	}
}
