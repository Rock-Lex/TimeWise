package Calendar;

import Calendar.Exceptions.EmptyFieldException;
import Calendar.Exceptions.InvalidDateException;

import java.time.LocalDateTime;

public class RepeatAppointment extends Termin{
    private String frequency;
    private LocalDateTime endRepeatDate;

    /**
     * Erstellt ein neues RepeatAppointment-Objekt mit den angegebenen Daten.
     *
     * @param title Name des Termins.
     * @param type Typ des Termins.
     * @param multiDay Gibt an, ob es sich um einen mehrtägigen Termin handelt.
     * @param start Startdatum und -zeit des Termins.
     * @param end Enddatum und -zeit des Termins.
     * @param frequency Frequenz der Wiederholung des Termins.
     * @param endRepeatDate Datum, an dem die Wiederholungen enden sollen.
     *
     * @throws EmptyFieldException Wenn das Feld "Frequenz" leer ist.
     * @throws InvalidDateException Wenn das Enddatum für die Wiederholungen vor dem Startdatum liegt.
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

    /**
     * Gibt die Frequenz der Wiederholungen zurück.
     *
     * @return Die Frequenz der Wiederholungen.
     */
    public String getFrequency() {
        return frequency;
    }
    /**
     * Setzt die Frequenz der Wiederholungen.
     *
     * @param frequency Die neue Frequenz der Wiederholungen.
     */
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
    /**
     * Gibt das Enddatum der Wiederholungen zurück.
     *
     * @return Das Enddatum der Wiederholungen.
     */
    public LocalDateTime getEndRepeatDate() {
        return endRepeatDate;
    }

    /**
     * Setzt das Enddatum der Wiederholungen.
     *
     * @param endRepeatDate Das neue Enddatum der Wiederholungen.
     */
    public void setEndRepeatDate(LocalDateTime endRepeatDate) {
        this.endRepeatDate = endRepeatDate;
    }

    /**
     * Gibt eine String-Repräsentation des RepeatAppointment-Objekts zurück.
     *
     * @return Eine String-Repräsentation des RepeatAppointment-Objekts.
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
