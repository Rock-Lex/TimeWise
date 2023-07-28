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
     * Konstruktor für die Erstellung eines Teilnehmers.
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
     * Setzt die E-Mail-Adresse des Teilnehmers.
     *
     * @param email Die E-Mail-Adresse des Teilnehmers.
     */
    public String getEmail() {
        return this.email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Teilnehmer{" +
                "name=" + this.name + '\'' +
                ", email='" + this.email + '\'' +
                '}';
    }
    @Override
    public int compareTo(Teilnehmer teilnehmer) {
        return this.name.compareTo(teilnehmer.name);
    }
}
