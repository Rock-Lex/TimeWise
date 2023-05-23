package Calendar;

import IOManager.Database;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Calendar;
public class Kalender {

    private Database database;
    private List<Termin> termine;

    public Kalender() {

        this.database = new Database();

        this.termine = this.database.getTerminArray();

    }

    public void addTermin(Termin termin) {
        termine.add(termin);
    }

    public void removeTermin(Termin termin) {
        termine.remove(termin);
    }

}