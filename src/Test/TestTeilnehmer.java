package Test;

import static org.junit.Assert.*;

import Calendar.Teilnehmer;
import org.junit.Test;

/**
 * Bei dieser Klasse handelt es sich um die Testklasse mit Units für die Klasse Teilnehmer.
 *
 * Autor: Tobias Rehm
 * Version: 1.0.0
 * Erstellt am: 23.05.2023
 * Letzte Änderung: 29.07.2023
 */
public class TestTeilnehmer {
	@Test
	public void testConstructor_TID0501_constructorWithValidInput_createsTeilnehmer() {
		// Test ID: TID0501
		// Name: constructorWithValidInput
		// Input: Valid name and email
		// erwartete Ausgabe: Objekt Teilnehmer erstellen
		// Ergebnis: Positiv

		String name = "John Doe";
		String email = "john.doe@email.com";

		Teilnehmer teilnehmer = new Teilnehmer(name, email);

		assertNotNull(teilnehmer);
		assertEquals(name, teilnehmer.getName());
		assertEquals(email, teilnehmer.getEmail());
	}

	@Test
	public void testCompareTo_TID0502_compareTo_differentNames_returnsCorrectValue() {
		// Test ID: TID0502
		// Name: compareTo
		// Input: 2 Teilnehmer Objekte mit verschiedenen Namen
		// erwartete Ausgabe: Gibt negativ zurück, wenn: this name < other name, positiv, wenn: this name > other name, Null, wenn: die Namen gleich sind
		//
		// Ergebnis: Positiv

		Teilnehmer teilnehmer1 = new Teilnehmer("Alice", "");
		Teilnehmer teilnehmer2 = new Teilnehmer("Bob", "");

		assertTrue(teilnehmer1.compareTo(teilnehmer2) < 0);
		assertTrue(teilnehmer2.compareTo(teilnehmer1) > 0);
		assertEquals(0, teilnehmer1.compareTo(teilnehmer1));
	}

}