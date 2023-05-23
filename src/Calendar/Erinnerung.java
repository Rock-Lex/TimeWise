package Calendar;

import IDgen.IDGenerator;

import java.time.LocalDateTime;

/**
 * Bei dieser Klasse handelt es sich um einen Database Manager.
 * *
 * Autor: Oleksandr Kamenskyi
 * Version: 1.0.0
 * Erstellt am: 23.05.2023
 * Letzte Änderung: 23.05.2023
 */
public class Erinnerung implements Comparable<Erinnerung> {

    private String id;
    private LocalDateTime time;
    private String message;
    private Boolean isActivated;


    /**
     * Konstruktor für die Erstellung einer Erinnerung.
     *
     * @param time LocalDateTime der Erinnerung
     * @param message Text des messages
     * @param isActivated True - Activated, False - Disactivated
     */
    public Erinnerung(LocalDateTime time, String message, Boolean isActivated) {
        this.time = time;
        this.message = message;
        this.isActivated = isActivated;
        this.id = IDGenerator.generateID("ER");
    }

    /**
     *  Getters und Setters
     */

    public LocalDateTime getTime() {
        return this.time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean isActivated() {
        return this.isActivated;
    }

    public void setActivated(Boolean isActivated) {
        this.isActivated = isActivated;
    }

    public void setId(int id) {
        this.id = IDGenerator.generateID("ER");
    }

    @Override
    public String toString() {
        return "Erinnerung{" +
                "id=" + this.id + '\'' +
                ", time='" + this.time + '\'' +
                ", message='" + this.message + '\'' +
                ", isActivated='" + this.isActivated + '\'' +
                '}';
    }

    @Override
    public int compareTo(Erinnerung erinnerung) {

        Boolean isBefore = this.time.isBefore(erinnerung.time);
        int compareTo = 0;

        if (isBefore) {
            compareTo = 1;
        }
        return compareTo;
    }
}
