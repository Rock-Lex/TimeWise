package Test;

import Calendar.Erinnerung;

import java.time.LocalDateTime;

/**
 * Testklasse für Klasse Erinnerung
 *
 * Autor: Philipp Voß
 * Erstelldatum: 23.05.2023
 */
public class TestErinnerung {
    public void test() {
        LocalDateTime time = LocalDateTime.now();
        String message = "Hallo";

        Erinnerung erinnerung = new Erinnerung(time, message, true);
        System.out.println(erinnerung.toString());

        Erinnerung erinnerung2 = new Erinnerung(LocalDateTime.now(), "123" ,  false);
        System.out.println(erinnerung2.toString());
    }
}
