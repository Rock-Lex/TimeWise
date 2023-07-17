package GUI.Views;

import Calendar.Termin;

import javax.swing.*;
import java.awt.*;
import java.time.YearMonth;

/**
 * Abstrakte Basisklasse für eine Kalender Ansicht.
 *
 * Autor: Philipp Voß
 * Version: 1.0
 * Erstellt am: 16.07.2023
 *
 */
public abstract class CalendarView extends JPanel {
    protected YearMonth yearMonth;
    private int year;
    private int month;

    public CalendarView(int year, int month) {
        yearMonth = YearMonth.of(year, month);
        setLayout(new GridLayout(0, 7));
        this.year = year;
        this.month = month;
    }

    public abstract void setToday(int day);

    public abstract void addAppointment(Termin appointment);

    public YearMonth getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(YearMonth yearMonth) {
        this.yearMonth = yearMonth;
    }

    public abstract void updateView();

    public int getYear() {
        return this.year;
    }

    public int getMonth() {
        return this.month;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

}
