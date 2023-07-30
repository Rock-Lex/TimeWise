package Calendar;

/**
 * Eine Klasse, die einen Teilnehmer repräsentiert.
 *
 * Autor: Oleksandr Kamenskyi
 * Version: 1.0.0
 * Erstellt am: 23.05.2023
 * Letzte Änderung: 23.05.2023
 */
public class Teilnehmer implements Comparable<Teilnehmer>{
    private String name;
    private String email;

    /**
     * Erstellt ein neues Teilnehmer-Objekt mit dem gegebenen Namen und der E-Mail-Adresse.
     *
     * @param name  Der Name des Teilnehmers.
     * @param email Die E-Mail-Adresse des Teilnehmers.
     */
    public Teilnehmer(String name, String email) {
        this.name = name;
        this.email = email;
    }
//  --------------- Getters und Setters ---------------
    /**
     * Gibt den Namen des Teilnehmers zurück.
     *
     * @return Der Name des Teilnehmers.
     */
    public String getName() {
        return this.name;
    }
    /**
     * Gibt die E-Mail-Adresse des Teilnehmers zurück.
     *
     * @return Die E-Mail-Adresse des Teilnehmers.
     */
    public String getEmail() {
        return this.email;
    }
    /**
     * Setzt den Namen des Teilnehmers.
     *
     * @param name Der neue Name des Teilnehmers.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Setzt die E-Mail-Adresse des Teilnehmers.
     *
     * @param email Die neue E-Mail-Adresse des Teilnehmers.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Gibt eine String-Repräsentation des Teilnehmer-Objekts zurück.
     *
     * @return String-Repräsentation des Teilnehmer-Objekts.
     */
    @Override
    public String toString() {
        return "Teilnehmer{" +
                "name=" + this.name + '\'' +
                ", email='" + this.email + '\'' +
                '}';
    }
    /**
     * Vergleicht dieses Teilnehmer-Objekt mit einem anderen Teilnehmer-Objekt.
     * Die Vergleichsbasis ist der Name des Teilnehmers.
     *
     * @param teilnehmer Das Teilnehmer-Objekt, mit dem dieses Teilnehmer-Objekt verglichen werden soll.
     * @return Ein negativer Integer, Null oder ein positiver Integer, je nachdem, ob der Name dieses Teilnehmers kleiner, gleich oder größer ist als der Name des angegebenen Teilnehmers.
     */
    @Override
    public int compareTo(Teilnehmer teilnehmer) {
        return this.name.compareTo(teilnehmer.name);
    }
}
