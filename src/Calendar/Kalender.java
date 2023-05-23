package Calendar;

import IOManager.Database;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Calendar;

public class Kalender implements Comparable<Kalender>{

    private Database database;
    private List<Termin> termine;

    public Kalender(Database database, List<Termin> termine) {

        this.database = new Database();

        this.termine = this.database.getTerminArray();

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Kalender\n{")
                .append("database=").append(database.getDatabaseName());
        for(Termin termin : termine){
            sb.append(", termine=").append(termin.getTitle());
            sb.append(", termine=").append(termin.getStart());
            sb.append(", termine=").append(termin.getEnd());
            sb.append("\n");
        }

        sb.append('}');
        return sb.toString();
    }

    public void addTermin(Termin termin) {
        if (termine == null) {
            termine = new ArrayList<>();
        }
        termine.add(termin);
    }

    public void removeTermin(Termin termin) {
        this.termine.remove(termin);
    }

    public Database getDatabase() {
        return database;
    }

//    public void setDatabase(Database database) {
//        this.database = database;
//    }

    public void setTermine(List<Termin> termine) {
        this.termine = termine;
    }

    public List<Termin> getTermine() {
        return this.termine;
    }

    @Override
    public int compareTo(Kalender other) {
        return 0;
    }
}