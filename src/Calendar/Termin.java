package Calendar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import IDgen.IDGenerator;
/**
 * Bei dieser Klasse handelt es sich um einen Termin.
 * *
 * Autor: Philipp Voß
 * Version: 1.0.1
 * Erstellt am: 14.05.2023
 * Letzte Änderung: 14.05.2023
 */
public class Termin {
    String id;
    public String title;
    boolean multiDay = false;
    public LocalDateTime start;
    public LocalDateTime end;
    public String type;

    /**
     * Konstruktor für die Erstellung eines Termins.
     *
     * @param title Name des Termins
     * @param type Typ des Termins
     * @param multiDay Handelt es sich um einen mehrtägigen Termin?
     * @param startDate Startdatum des Termins
     * @param endDate Enddatum des Termins
     * @param startTime Startzeit des Termins
     * @param endTime Endzeit des Termins
     */
    public Termin(String title, String type, boolean multiDay, String startDate, String endDate, String startTime, String endTime){
        if(multiDay){
            this.title = title;
            this.type = type;
            this.start = dateTimeFormatter(startDate, startTime);
            this.end = dateTimeFormatter(endDate, endTime);
        } else {
            this.title = title;
            this.type = type;
            this.start = dateTimeFormatter(startDate, startTime);
            this.end = dateTimeFormatter(startDate, endTime);
        }

        this.id = IDGenerator.generateID(type);
    }

    /**
     * Diese Methode formatiert das übergebende Datum in das LocalDateTime Format.
     *
     * @param date Tag des zu formatierenden Termins
     * @param time Zeit des zu formatierenden Termins
     * @return Formatiertes Datum
     */
    private LocalDateTime dateTimeFormatter(String date, String time){
        String dateTimeString = date + "T" + time;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        return LocalDateTime.parse(dateTimeString, formatter);
    }


    // Getter und Setter
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isMultiDay() {
        return multiDay;
    }

    public void setMultiDay(boolean multiDay) {
        this.multiDay = multiDay;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
