package Test;

import Calendar.Kalender;
import Calendar.Termin;
import IOManager.Database;

import java.util.ArrayList;
import java.util.List;

public class TestCalendar {
    public TestCalendar() {

    }




    public void test() {
        System.out.println("Test started");

        List<Termin> termine = new ArrayList<>();

        Database database = new Database("bla-keks", "Haha");
        Kalender kalender = new Kalender(database, termine);
        System.out.println(kalender.getTermine().size());

        Termin termin1 = new Termin("Ein Tag","MT",true,"2022-12-21", "2022-12-21", "12:00","13:00");
        Termin termin2 = new Termin("Zwei Tag","AT",true,"2023-12-21", "2023-12-21", "12:00","13:00");

        termine.add(termin1);
        termine.add(termin2);

        System.out.println(kalender.getTermine().size());

        System.out.println("\n");

        System.out.println(kalender.toString());





        System.out.println("Test ended");
    }
}
