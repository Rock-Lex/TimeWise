import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Bei dieser Klasse handelt es sich um die Testklasse mit Units für die Klasse Teilnehmer
 *
 * Autor: Tobias Rehm
 * Version: 1.0.0
 * Erstellt am: 23.05.2023
 * Letzte Änderung: 29.07.2023
 */
public class TestTeilnehmer {
	@Test
	public void testConstructor_TID0601_constructorWithValidInput_REQ06_createsTeilnehmer() {
		// Test ID: TID0601
		// Name: constructorWithValidInput
		// Requirement ID: REQ06
		// Input: Valid name and email
		// Expected behavior: Teilnehmer object created successfully
		// Result: Positive

		String name = "John Doe";
		String email = "john.doe@email.com";

		Teilnehmer teilnehmer = new Teilnehmer(name, email);

		assertNotNull(teilnehmer);
		assertEquals(name, teilnehmer.getName());
		assertEquals(email, teilnehmer.getEmail());
	}

	@Test
	public void testCompareTo_TID0602_compareTo_REQ06_differentNames_returnsCorrectValue() {
		// Test ID: TID0602
		// Name: compareTo
		// Requirement ID: REQ06
		// Input: 2 Teilnehmer objects with different names
		// Expected behavior: Returns negative if this name < other name,
		//                     positive if this name > other name,
		//                     zero if names are equal
		// Result: Positive

		Teilnehmer teilnehmer1 = new Teilnehmer("Alice", "");
		Teilnehmer teilnehmer2 = new Teilnehmer("Bob", "");

		assertTrue(teilnehmer1.compareTo(teilnehmer2) < 0);
		assertTrue(teilnehmer2.compareTo(teilnehmer1) > 0);
		assertEquals(0, teilnehmer1.compareTo(teilnehmer1));
	}

}