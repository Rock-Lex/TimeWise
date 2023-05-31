package Test;

import Calendar.Termin;
import IOManager.Database;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.List;

public class TestDatenbank {

    /**
     * Bei dieser Klasse handelt es sich um einen Test fur Database.
     * *
     * Autor: Oleksandr Kamenskyi
     * Version: 1.0.0
     * Erstellt am: 23.05.2023
     * Letzte Änderung: 23.05.2023
     */

    TestDatenbank() {

    }
    public static void testDatenbankClass() {
        Database db = new Database();
        List<Termin> arr = db.getTerminArray();

        for (Termin termin : arr) {
            System.out.println(termin.getStart());
            db.deleteTermin(termin);
        }
    }

    public void test() {
        System.out.println("Test started");

        testDatenbankClass();

        System.out.println("Test ended");
    }

    public static void main(String[] args) {
        Database database = new Database();
        LocalDateTime lt = LocalDateTime.now();
        database.addTermin("Mehrere Tage", lt, lt,"AT", "Oleksandr Kamenskyi");
    }
}
