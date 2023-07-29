package Calendar;

import Calendar.Exceptions.EmptyFieldException;
import Calendar.Exceptions.InvalidDateException;

import java.time.LocalDateTime;

public class RepeatAppointment extends Termin{
    private String frequency;
    private LocalDateTime endRepeatDate;

    /**
     * Konstruktor für die Erstellung eines wiederholten Termins.
     *
     * @param title Name des Termins
     * @param type Typ des Termins
     * @param multiDay Handelt es sich um einen mehrtägigen Termin?
     * @param start Startdatum und -zeit des Termins
     * @param end Enddatum und -zeit des Termins
     * @param frequency Häufigkeit der Wiederholung
     * @param endRepeatDate Datum, bis wann die Wiederholung enden soll
     */
    public RepeatAppointment(String title, String type, boolean multiDay, LocalDateTime start, LocalDateTime end, String frequency, LocalDateTime endRepeatDate) {
        super(title, type, multiDay, start, end);

        if(frequency == null || frequency.trim().isEmpty()){
            throw new EmptyFieldException("Frequency darf nicht leer sein.");
        }

        if(endRepeatDate != null && endRepeatDate.isBefore(start)){
            throw new InvalidDateException("Enddatum für Wiederholungen muss nach dem Startdatum liegen.");
        }

        this.frequency = frequency;
        this.endRepeatDate = endRepeatDate;
    }

    // Getter und Setter
    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public LocalDateTime getEndRepeatDate() {
        return endRepeatDate;
    }

    public void setEndRepeatDate(LocalDateTime endRepeatDate) {
        this.endRepeatDate = endRepeatDate;
    }

    /**
     * @return String mit allen Daten zu einem wiederholten Termin.
     */
    @Override
    public String toString() {
        return "RepeatAppointment{" +
                "id='" + getId() + '\'' +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", multiDay=" + isMultiDay() +
                ", start=" + getStart() +
                ", end=" + getEnd() +
                ", type='" + getType() + '\'' +
                ", frequency='" + frequency + '\'' +
                ", endRepeatDate=" + endRepeatDate +
                '}';
    }
}
