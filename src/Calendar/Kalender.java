package Calendar;

import IOManager.Database;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Calendar;


/**
 *  Die Klasse Kalender repräsentiert den Kalender. Die Daten im Kalender sollen auf einer Datenbank basieren und
 *  stellen eine Liste von Terminen zur Verfügung. Sie ermöglicht das Verwalten von Terminen.
 *
 *  Autor: Simon Degmair
 *  Erstellt am: 23.05.2023
 *  Version: 1.0.1
 *
 */
public class Kalender implements Comparable<Kalender>{

    private Database database;
    private List<Termin> termine;

    public Kalender(Database database, List<Termin> termine) {

        this.database = new Database();

        this.termine = this.database.getTerminArray();

    }

    /**
     *
     * @return sb Daten in Kalender gespeichert
     */
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

    /**
     *
     * @param termin Objekt der Klasse Termin.
     */
    public void addTermin(Termin termin) {
        if (termine == null) {
            termine = new ArrayList<>();
        }
        termine.add(termin);
    }

    /**
     *
     * @param termin Objekt der Klasse Termin.
     */
    public void removeTermin(Termin termin) {
        this.termine.remove(termin);
    }

    /**
     *
     * @return Objekt der Klasse Database.
     */
    public Database getDatabase() {
        return database;
    }

//    public void setDatabase(Database database) {
//        this.database = database;
//    }

    public void setTermine(List<Termin> termine) {
        this.termine = termine;
    }

    /**
     *
     * @return Listenobjekt der Klasse Termine
     */
    public List<Termin> getTermine() {
        return this.termine;
    }

    /**
     *
     * @param other the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Kalender other) {
        return this.database.compareTo(other.database);
    }
}