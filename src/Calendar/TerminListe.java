package Calendar;

import IOManager.Database;
import IOManager.Exceptions.SQLPackageException;
import IOManager.Exceptions.WrongPathException;

import java.util.ArrayList;
import java.util.List;


/**
 * Die Klasse TerminListe repräsentiert den Kalender. Die Daten im Kalender sollen auf einer Datenbank basieren und
 * stellen eine Liste von Terminen zur Verfügung. Sie ermöglicht das Verwalten von Terminen.
 *
 * @author Simon Degmair
 * @version 1.1.2
 * @since am: 23.05.2023
 *
 */
public class TerminListe implements Comparable<TerminListe>{

    private Database database;
    private List<Termin> termine;
    /**
     * Erstellt eine neue TerminListe basierend auf einer gegebenen Datenbank und einer Liste von Terminen.
     *
     * @param database Die Datenbank, auf der der Kalender basiert.
     * @param termine  Die Liste von Terminen.
     * @throws RuntimeException Falls beim Erstellen der Datenbank eine Exception ausgelöst wird.
     */
    public TerminListe(Database database, List<Termin> termine) {

        try {
            this.database = new Database();
        } catch (WrongPathException | SQLPackageException e) {
            throw new RuntimeException(e);
        }

//        this.termine = this.database.getTerminArray();

    }
    /**
     * Erstellt eine leere TerminListe.
     */
    public TerminListe(){

    }

    /**
     * Gibt eine textuelle Repräsentation der TerminListe zurück.
     *
     * @return String-Repräsentation der TerminListe.
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
     * Fügt einen neuen Termin zur TerminListe hinzu.
     *
     * @param termin Das Termin-Objekt, das hinzugefügt werden soll.
     */
    public void addTermin(Termin termin) {
        if (termine == null) {
            termine = new ArrayList<>();
        }
        termine.add(termin);
    }

    /**
     * Entfernt einen Termin aus der TerminListe.
     *
     * @param termin Das Termin-Objekt, das entfernt werden soll.
     */
    public void removeTermin(Termin termin) {
        this.termine.remove(termin);
    }

    /**
     * Gibt die Datenbank zurück, auf der die TerminListe basiert.
     *
     * @return Die Datenbank der TerminListe.
     */
    public Database getDatabase() {
        return database;
    }

//    public void setDatabase(Database database) {
//        this.database = database;
//    }

    /**
     * Setzt die Liste der Termine für die TerminListe.
     *
     * @param termine Die Liste von Terminen.
     */
    public void setTermine(List<Termin> termine) {
        this.termine = termine;
    }


    /**
     * Gibt die Liste der Termine zurück.
     *
     * @return Die Liste von Terminen.
     */
    public List<Termin> getTermine() {
        return this.termine;
    }

    /**
     * Vergleicht diese TerminListe mit einer anderen TerminListe.
     * Die genaue Implementierung hängt von den spezifischen Anforderungen ab.
     *
     * @param other Die andere TerminListe, die verglichen werden soll.
     * @return Eine Ganzzahl, die angibt, ob diese TerminListe größer, kleiner oder gleich der anderen TerminListe ist.
     */
    @Override
    public int compareTo(TerminListe other) {
        return 1;
    }
}