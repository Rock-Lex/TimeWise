package Test;

import Calendar.Teilnehmer;

public class TestTeilnehmer {
	/**
	 * @author tobiasrehm
	 *Bei dieser Test-Klasse handelt es sich um die Test-Klasse für die Klasse Teilnehmer.
	 * *
	 * Autor: Tobias Rehm
	 * Version: 1.0.0
	 * Erstellt am: 21.05.2023
	 * Letzte Änderung: 23.05.2023
	 */
	public TestTeilnehmer() {
		
	}
	
	public static void test() {
		
		System.out.println("TEST FOR TEILNEHMER CLASS");
		System.out.println("TEST 1");
		
		Teilnehmer teilnehmer = new Teilnehmer("Günter", "günter@gmail.com");
		
		System.out.println(teilnehmer.getName());
		System.out.println(teilnehmer.getEmail());
		System.out.println(teilnehmer.toString());
		System.out.println(teilnehmer.compareTo(teilnehmer));
	}

}
