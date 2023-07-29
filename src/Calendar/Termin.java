package Calendar;

import Calendar.Exceptions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import IDgen.IDGenerator;

/**
 * Bei dieser Klasse handelt es sich um einen Termin.
 *
 * Autor: Philipp Voß
 * Version: 1.2
 * Erstellt am: 14.05.2023
 * Letzte Änderung: 21.06.2023
 */
public class Termin implements Comparable<Termin>{
    private String id;
    private String title;
    private boolean multiDay = false;
    private LocalDateTime start;
    private LocalDateTime end;
    private String type;
    private String description;

    public List<Teilnehmer> getTeilnehmerList() {
        return teilnehmerList;
    }

    public void setTeilnehmerList(List<Teilnehmer> teilnehmerList) {
        this.teilnehmerList = teilnehmerList;
    }

    private List<Teilnehmer> teilnehmerList;


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
    public Termin(String title, String type, boolean multiDay, String startDate, String endDate, String startTime, String endTime) {
        if(title == null || title.trim().isEmpty() || type == null || type.trim().isEmpty()){
            throw new EmptyFieldException("Title und Typ dürfen nicht leer sein.");
        }

        this.title = title;
        this.type = type;
        this.start = dateTimeFormatter(startDate, startTime);
        this.end = dateTimeFormatter(endDate, endTime);
        this.multiDay = !startDate.equals(endDate);

        this.id = IDGenerator.generateID(type);

        if(this.start.isAfter(this.end)){
            throw new InvalidDateException("Startdatum muss vor dem Enddatum liegen.");
        }
    }

    public Termin(String title, String type, boolean multiDay, LocalDateTime start, LocalDateTime end) {
        if(title == null || title.trim().isEmpty() || type == null || type.trim().isEmpty()){
            throw new EmptyFieldException("Title und Typ dürfen nicht leer sein.");
        }

        this.title = title;
        this.type = type;
        this.start = start;
        this.end = end;
        this.multiDay = !start.toLocalDate().equals(end.toLocalDate());

        this.id = IDGenerator.generateID(type);

        if(this.start.isAfter(this.end)){
            throw new InvalidDateException("Startdatum muss vor dem Enddatum liegen.");
        }
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

    /**
     * @return String mit allen Daten zu einem Termin.
     */
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

    /**
     * @param other the object to be compared.
     * @return Integer, welcher den Zustand widerspiegelt.
     */
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
    /**
     * Gibt das Startdatum des Termins als String zurück.
     *
     * @return Startdatum des Termins im Format "yyyy-MM-dd"
     */
    public String getStartDate() {
        return start.toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    /**
     * Gibt die Startzeit des Termins als String zurück.
     *
     * @return Startzeit des Termins im Format "HH:mm"
     */
    public String getStartTime() {
        return start.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));
    }
    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }
    /**
     * Gibt das Enddatum des Termins als String zurück.
     *
     * @return Enddatum des Termins im Format "yyyy-MM-dd"
     */
    public String getEndDate() {
        return end.toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    /**
     * Gibt die Endzeit des Termins als String zurück.
     *
     * @return Endzeit des Termins im Format "HH:mm"
     */
    public String getEndTime() {
        return end.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }
}
