package Calendar;

import IDgen.IDGenerator;

import java.time.LocalDateTime;

/**
 * Eine Klasse, die eine Erinnerung repräsentiert und verwaltet.
 * Die Erinnerung enthält Informationen wie Zeitpunkt, Nachricht und Aktivierungsstatus.
 *
 * @author: Oleksandr Kamenskyi
 * @version : 1.0.0
 * @since 23.05.2023
 * Letzte_Änderung: 23.05.2023
 */
public class Erinnerung implements Comparable<Erinnerung> {

    // Attribute der Klasse
    private String id;
    private LocalDateTime time;
    private String message;
    private Boolean isActivated;

    /**
     * Konstruktor für die Erstellung einer Erinnerung.
     *
     * @param time        Der Zeitpunkt der Erinnerung als LocalDateTime.
     * @param message     Der Text der Erinnerung.
     * @param isActivated Gibt an, ob die Erinnerung aktiviert ist (true) oder deaktiviert (false).
     */
    public Erinnerung(LocalDateTime time, String message, Boolean isActivated) {
        this.time = time;
        this.message = message;
        this.isActivated = isActivated;
        this.id = IDGenerator.generateID("ER");
    }

    // Getter und Setter

    /**
     * @return Der Zeitpunkt der Erinnerung als LocalDateTime.
     */
    public LocalDateTime getTime() {
        return this.time;
    }

    /**
     * Setzt den Zeitpunkt der Erinnerung.
     *
     * @param time Der neue Zeitpunkt der Erinnerung als LocalDateTime.
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    /**
     * @return Der Text der Erinnerung.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Setzt den Text der Erinnerung.
     *
     * @param message Der neue Text der Erinnerung.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return True, wenn die Erinnerung aktiviert ist, andernfalls False.
     */
    public Boolean isActivated() {
        return this.isActivated;
    }

    /**
     * Setzt den Aktivierungsstatus der Erinnerung.
     *
     * @param isActivated True, wenn die Erinnerung aktiviert sein soll, andernfalls False.
     */
    public void setActivated(Boolean isActivated) {
        this.isActivated = isActivated;
    }

    /**
     * @return Die ID der Erinnerung als String.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Setzt die ID der Erinnerung.
     *
     * @param id Die neue ID der Erinnerung als String.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gibt eine Zeichenkette dar, die die Informationen der Erinnerung enthält.
     *
     * @return Die Zeichenkette mit den Informationen der Erinnerung.
     */
    @Override
    public String toString() {
        return "Erinnerung{" +
                "id='" + this.id + '\'' +
                ", time=" + this.time +
                ", message='" + this.message + '\'' +
                ", isActivated=" + this.isActivated +
                '}';
    }

    /**
     * Vergleicht die Erinnerung mit einer anderen Erinnerung anhand ihres Zeitpunkts.
     * Diese Methode wird für die Sortierung von Erinnerungen verwendet.
     *
     * @param erinnerung Die andere Erinnerung, mit der verglichen werden soll. Wenn diese null ist, wird eine NullPointerException ausgelöst.
     * @return Ein Wert kleiner als 0, wenn diese Erinnerung vor der anderen liegt;
     *         ein Wert größer als 0, wenn diese Erinnerung nach der anderen liegt;
     *         andernfalls 0, wenn beide Erinnerungen den gleichen Zeitpunkt haben.
     */
    @Override
    public int compareTo(Erinnerung erinnerung) {
        return this.time.compareTo(erinnerung.time);
    }

}