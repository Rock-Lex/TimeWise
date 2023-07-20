package GUI.Views;

import Calendar.Termin;
import Calendar.TerminListe;

import javax.swing.*;
import java.awt.*;
import java.time.YearMonth;

/**
 * Abstrakte Basisklasse für eine Kalender Ansicht.
 *
 * Autor: Philipp Voß
 * Version: 1.2
 * Erstellt am: 16.07.2023
 * Letzte Änderung: 19.07.2023
 */
public abstract class CalendarView extends JPanel {
    protected YearMonth yearMonth;
    private int year;
    private int month;
    private TerminListe terminListe;
    /**
     * Erstellt eine neue Kalenderansicht mit dem angegebenen Jahr, Monat und Terminliste.
     *
     * @param year Das Jahr der Kalenderansicht
     * @param month Der Monat der Kalenderansicht
     * @param terminListe Die Terminliste für die Kalenderansicht
     */
    public CalendarView(int year, int month, TerminListe terminListe) {
        yearMonth = YearMonth.of(year, month);
        setLayout(new GridLayout(0, 7));
        this.year = year;
        this.month = month;
    }
    /**
     * Setzt den aktuellen Tag in der Kalenderansicht.
     *
     * @param day Der aktuelle Tag als int
     */
    public abstract void setToday(int day);

    /**
     * Fügt einen Termin zur Kalenderansicht hinzu.
     *
     * @param appointment Der Termin, der hinzugefügt werden soll
     */
    public abstract void addAppointment(Termin appointment);

    /**
     * Gibt das Jahr und den Monat der Kalenderansicht zurück.
     *
     * @return Das YearMonth-Objekt der Kalenderansicht
     */
    public YearMonth getYearMonth() {
        return yearMonth;
    }
    /**
     * Setzt das Jahr und den Monat der Kalenderansicht.
     *
     * @param yearMonth Das YearMonth-Objekt mit dem neuen Jahr und Monat
     */
    public void setYearMonth(YearMonth yearMonth) {
        this.yearMonth = yearMonth;
    }
    /**
     * Aktualisiert die Kalenderansicht mit der angegebenen Terminliste.
     *
     * @param terminListe Die aktualisierte Terminliste
     */
    public abstract void updateView(TerminListe terminListe);
    /**
     * Wechselt zur nächsten Zeitspanne in der Kalenderansicht.
     */
    public abstract void nextPeriod();
    /**
     * Wechselt zur vorherigen Zeitspanne in der Kalenderansicht.
     */
    public abstract void previousPeriod();
    /**
     * Gibt das Jahr der Kalenderansicht zurück.
     *
     * @return Das Jahr der Kalenderansicht
     */
    public int getYear() {
        return this.year;
    }
    /**
     * Gibt den Monat der Kalenderansicht zurück.
     *
     * @return Der Monat der Kalenderansicht
     */
    public int getMonth() {
        return this.month;
    }
    /**
     * Setzt das Jahr der Kalenderansicht.
     *
     * @param year Das Jahr, das gesetzt werden soll
     */
    public void setYear(int year) {
        this.year = year;
    }
    /**
     * Setzt den Monat der Kalenderansicht.
     *
     * @param month Der Monat, der gesetzt werden soll
     */
    public void setMonth(int month) {
        this.month = month;
    }
}