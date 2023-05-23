package Test;

import Calendar.Holidays;

/**
 * @author tobiasrehm
 *Bei dieser Test-Klasse handelt es sich um die Test-Klasse für die Klasse Holidays.
 * *
 * Autor: Tobias Rehm
 * Version: 1.0.0
 * Erstellt am: 21.05.2023
 * Letzte Änderung: 23.05.2023
 */


public class TestHolidays {

	public static void main(String[] args) {

		Holidays f1 = new Holidays("Neujahr", 1, 1);
		f1.display();
		
		Holidays f2 = new Holidays("internationaler Frauentag", 3, 8);
		f2.display();
		
		Holidays f3 = new Holidays("Tag der Arbeit", 5, 1);
		f3.display();
		
		Holidays f4 = new Holidays("Tag der Deutschen Einheit", 10, 3);
		f4.display();
		
		Holidays f5 = new Holidays("1. Weihnachtstag", 12, 25);
		f5.display();
		
		Holidays f6 = new Holidays("2. Wiehnachtstag", 12, 26);
		f6.display();
		 
		Holidays f7 = new Holidays("Sylvester", 12, 31);
		f7.display();
		
//		Osterfeiertage f8 = new Osterfeiertage("Ostersonntag", Osterfeiertage);
//		f8.display();
		
		
		
		System.out.println("1. Feiertag: ");
		f1.print();
		System.out.println();
		
		System.out.println("2. Feiertag: ");
		f2.print();
		System.out.println();
		
		System.out.println("3. Feiertag: ");
		f3.print();
		System.out.println();
		
		System.out.println("4. Feiertag: ");
		f4.print();
		System.out.println();

		System.out.println("5. Feiertag: ");
		f5.print();
		System.out.println();

		System.out.println("6. Feiertag: ");
		f6.print();
		System.out.println();

		System.out.println("7. Feiertag: ");
		f7.print();
		System.out.println();

		System.out.println("das sind alle gesetzlichen unveränderlichen Feiertage in Berlin");
		
		System.out.println();
//		System.out.println("Ostersonntag: " + Ostersonntag);
//		f8.print();
	}

}
