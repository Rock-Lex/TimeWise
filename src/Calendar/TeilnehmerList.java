package Calendar;

import java.util.ArrayList;
import java.util.List;
/**
 * Die TeilnehmerList Klasse repräsentiert eine Liste von Teilnehmern. Sie enthält Methoden zum Hinzufügen und Entfernen
 * von Teilnehmern sowie zum Abrufen der Liste der Teilnehmer.
 *
 * @author Philipp Voß
 * @version 1.0.0
 * @since 29.07.2023
 */
public class TeilnehmerList {

    private List<Teilnehmer> teilnehmerList;
    /**
     * Erstellt eine neue TeilnehmerListe. Die Liste wird initialisiert, aber es werden keine Teilnehmer hinzugefügt.
     */
    public TeilnehmerList() {
        this.teilnehmerList = new ArrayList<>();
    }
    /**
     * Fügt einen neuen Teilnehmer zur Teilnehmerliste hinzu.
     *
     * @param teilnehmer Der Teilnehmer, der hinzugefügt werden soll.
     */
    public void addTeilnehmer(Teilnehmer teilnehmer) {
        this.teilnehmerList.add(teilnehmer);
    }
    /**
     * Entfernt einen Teilnehmer aus der Teilnehmerliste.
     *
     * @param teilnehmer Der Teilnehmer, der entfernt werden soll.
     */
    public void removeTeilnehmer(Teilnehmer teilnehmer) {
        this.teilnehmerList.remove(teilnehmer);
    }
    /**
     * Gibt die Liste der Teilnehmer zurück.
     *
     * @return Die Liste der Teilnehmer.
     */
    public List<Teilnehmer> getTeilnehmerList() {
        return this.teilnehmerList;
    }
}
