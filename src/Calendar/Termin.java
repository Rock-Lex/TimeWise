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
public class Termin implements Comparable<Termin>{
    private String id;
    private String title;
    private boolean multiDay = false;
    private LocalDateTime start;
    private LocalDateTime end;
    private String type;
    private String description;

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
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Termin{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", multiDay=" + multiDay +
                ", start=" + start +
                ", end=" + end +
                ", type='" + type + '\'' +
                '}';
    }
    @Override
    public int compareTo(Termin other) {
        if (this.start.isBefore(other.start) && this.end.isBefore(other.start)) {
            // Der aktuelle Termin this liegt vor dem anderen Termin other
            return -1;
        } else if (this.start.isAfter(other.end) && this.end.isAfter(other.end)) {
            // Der aktuelle Termin this liegt nach dem anderen Termin other
            return 1;
        } else {
            // Die Termine this und other überlappen sich oder haben den gleichen Zeitraum
            return 0;
        }
    }
}
