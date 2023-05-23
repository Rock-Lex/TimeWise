package Test;

import Calendar.Termin;
import IOManager.Database;

import java.util.List;

public class TestDatenbank {

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
}
