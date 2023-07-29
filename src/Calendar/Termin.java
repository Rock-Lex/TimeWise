package Calendar;

import Calendar.Exceptions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    private List<Teilnehmer> teilnehmerList;

    public List<Teilnehmer> getTeilnehmerList() {
        return teilnehmerList;
    }
    public String getTeilnehmerListAsString() {
        if (teilnehmerList == null || teilnehmerList.isEmpty()) {
            return "No participants";
        }

        StringBuilder sb = new StringBuilder();
        for (Teilnehmer teilnehmer : teilnehmerList) {
            sb.append(teilnehmer.getName());
            sb.append(" (");
            sb.append(teilnehmer.getEmail());
            sb.append("), ");
        }

        // Remove trailing comma and space
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }
    public void setTeilnehmerList(List<Teilnehmer> teilnehmerList) {
        this.teilnehmerList = teilnehmerList;
    }
    public void setTeilnehmerListFromString(String teilnehmerListString) {

        teilnehmerList = new ArrayList<>();

        String[] parts = teilnehmerListString.split(", ");
        for(String part : parts) {
            String[] nameEmail = part.split("\\(");
            String name = nameEmail[0];

            int emailStart = nameEmail[1].indexOf(")") + 1;
            String email = nameEmail[1].substring(emailStart);

            Teilnehmer teilnehmer = new Teilnehmer(name, email);
            teilnehmerList.add(teilnehmer);
        }

    }


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
     * Constructor to create a Termin with a Teilnehmer
     *
     * @param title The title of the Termin
     * @param type The type of the Termin
     * @param multiDay Whether it is a multi-day Termin
     * @param start The start date/time
     * @param end The end date/time
     * @param teilnehmer The Teilnehmer for the Termin
     */
    public Termin(String title, String type, boolean multiDay, LocalDateTime start, LocalDateTime end, Teilnehmer teilnehmer) {
        this(title, type, multiDay, start, end);

        this.teilnehmerList = new ArrayList<>();
        this.teilnehmerList.add(teilnehmer);
    }

    public Termin(String title, String type, boolean multiDay, LocalDateTime start, LocalDateTime end, List<Teilnehmer> teilnehmerList) {

        this(title, type, multiDay, start, end);

        teilnehmerList = new ArrayList<>();
        for(Teilnehmer t : teilnehmerList) {
            teilnehmerList.add(t);
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