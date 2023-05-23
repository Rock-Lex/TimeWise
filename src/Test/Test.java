package Test;

public class Test {
    /**
     * Bei dieser Klasse handelt es sich um einen Test.
     * *
     * Autor: Oleksandr Kamenskyi
     * Version: 1.0.0
     * Erstellt am: 23.05.2023
     * Letzte Änderung: 23.05.2023
     */

    public static void main(String[] args) {
        TestCalendar testCalendar = new TestCalendar();
        TestTermin testTermin = new TestTermin();
        TestDatenbank testDatenbank = new TestDatenbank();
        TestErinnerung testErinnerung = new TestErinnerung();
        TestTeilnehmer testTeilnehmer = new TestTeilnehmer();


        //testCalendar.test();
        //testTermin.test();
        //testDatenbank.test();
        testErinnerung.test();
        testTeilnehmer.test();
    }
}
