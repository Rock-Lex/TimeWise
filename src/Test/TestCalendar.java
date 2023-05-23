package Test;

import Calendar.Kalender;
import Calendar.Termin;

public class TestCalendar {

    /**
     * Bei dieser Klasse handelt es sich um einen Test fur Calendar.
     * *
     * Autor: Oleksandr Kamenskyi
     * Version: 1.0.0
     * Erstellt am: 23.05.2023
     * Letzte Änderung: 23.05.2023
     */

    public TestCalendar() {

    }


    public void test() {
        System.out.println("Test started");
        Kalender kalender = new Kalender();
        System.out.println(kalender.getTermine().size());

        Termin termin1 = new Termin("Ein Tag","MT",true,"2022-12-21", "2022-12-21", "12:00","13:00");
        kalender.addTermin(termin1);

        System.out.println(kalender.getTermine().size());

        System.out.println("\n");







        System.out.println("Test ended");
    }
}
