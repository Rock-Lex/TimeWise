package Calendar;

/**
 * Bei dieser Klasse handelt es sich um einen Teilnehmer Class.
 * *
 * Autor: Oleksandr Kamenskyi
 * Version: 1.0.0
 * Erstellt am: 23.05.2023
 * Letzte Änderung: 23.05.2023
 */

public class Teilnehmer {
    private String name;
    private String email;

    /**
     * Konstruktor für die Erstellung eines Teilnehmers.
     *
     * @param name Name des Teilnehmers
     * @param email Email des Termins
     */
    Teilnehmer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /**
     *  Getters und Setters
     */

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
