package GUI.Views;

import Calendar.Termin;
import Calendar.TerminListe;
import GUI.Exceptions.AppointmentMismatchMonthException;
import GUI.Exceptions.AppointmentOutOfMonthRangeException;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;

public abstract class CalendarView extends JPanel {
    protected LocalDate shownDate;
    private TerminListe terminListe;

    public CalendarView(LocalDate date, TerminListe terminListe) {
        this.shownDate = date;
        setLayout(new GridLayout(0, 7));
    }

    public abstract void setToday(int day);

    public abstract void addAppointment(Termin appointment) throws AppointmentOutOfMonthRangeException, AppointmentMismatchMonthException;

    public LocalDate getDate() {
        return shownDate;
    }

    public void setDate(LocalDate date) {
        this.shownDate = date;
    }

    public abstract void updateView(TerminListe terminListe) throws AppointmentOutOfMonthRangeException, AppointmentMismatchMonthException;

    public abstract void nextPeriod();

    public abstract void previousPeriod();

    public int getYear() {
        return this.shownDate.getYear();
    }

    public int getMonth() {
        return this.shownDate.getMonthValue();
    }

    public void setYear(int year) {
        this.shownDate = this.shownDate.withYear(year);
    }

    public void setMonth(int month) {
        this.shownDate = this.shownDate.withMonth(month);
    }

    public YearMonth getYearMonth() {
        return YearMonth.from(this.shownDate);
    }

    public void todaysPeriod() {
    }
}