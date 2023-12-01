package GUI.Views;


import Calendar.HolidaysList;
import Calendar.Termin;
import Calendar.TerminListe;
import GUI.CalendarCell;
import GUI.Exceptions.AppointmentMismatchMonthException;
import GUI.Exceptions.AppointmentOutOfMonthRangeException;
import IOManager.Database;

import javax.swing.*;
import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Eine benutzerdefinierte Swing-Komponente zur Darstellung einer Monatsansicht im Kalender.
 *
 * @author  Philipp Voß
 * @version  1.6
 * @since 16.17.2023
 * Letzte Änderung: 19.07.2023
 */
public class MonthView extends CalendarView {
    // ... Klassenvariablen ...
    private TerminListe terminListe;
    private CalendarCell[] calendarCells;
    private JLabel[] daysLabels;
    private Database db;
    private YearMonth yearMonth;

// ----------- Konstruktoren -----------
    /**
     * Erstellt eine neue Monatsansicht mit dem angegebenen Jahr und Monat.
     *
     * @param year Der aktuelle Jahr als int.
     * @param month Der aktuelle Monat als int.
     * @param terminListe Eine Liste von Terminen
     */
    public MonthView(LocalDate shownDate, TerminListe terminListe, Database db) throws AppointmentOutOfMonthRangeException, AppointmentMismatchMonthException {
        super(shownDate, terminListe);
        this.db = db;
        this.terminListe = terminListe;
        this.yearMonth = YearMonth.from(shownDate);
        this.shownDate = shownDate;
        int numberOfDays = yearMonth.lengthOfMonth();
        calendarCells = new CalendarCell[numberOfDays];
        daysLabels = new JLabel[7];

        setLayout(new GridLayout(0, 7));

        for (int i = 0; i < 7; i++) {
            DayOfWeek dayOfWeek = DayOfWeek.of((i) % 7 + 1);  // Start with Monday
            String dayName = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.GERMAN);
            JLabel dayLabel = new JLabel(dayName, SwingConstants.CENTER);
            daysLabels[i] = dayLabel;
            add(dayLabel);
        }

        int offset = (shownDate.withDayOfMonth(1).getDayOfWeek().getValue() + 6) % 7;  // Erster Tag der ersten Woche im Monat
        for (int i = 0; i < offset; i++) {  // Hinzufügen leerer Zellen als Offset am Monatsanfang
            add(new JLabel());
        }

        for (int i = 1; i <= numberOfDays; i++) {
            CalendarCell cell = new CalendarCell(Integer.toString(i), terminListe, this, db);
            calendarCells[i - 1] = cell;
            add(cell);
        }

        // Add appointments from the list
        for (Termin termin : terminListe.getTermine()) {
            LocalDate terminDate = termin.getStart().toLocalDate();
            if(terminDate.getMonth() == yearMonth.getMonth() && terminDate.getYear() == yearMonth.getYear()){
                addAppointment(termin);
            }
        }
    }

    /**
     * Setzt den aktuellen Tag im Kalender.
     *
     * @param day Der aktuelle Tag als int.
     */
    public void setToday(int day) {
        for (CalendarCell cell : calendarCells) {
            cell.setToday(false);
        }
        calendarCells[day - 1].setToday(true);
    }

    /**
     * Fügt einen Termin zu einem bestimmten Tag im Kalender hinzu.
     *
     * @param appointment Der Termin als String.
     */
    public void addAppointment(Termin appointment) {
        try {
            if (appointment.getStart().getMonth() != shownDate.getMonth()) {
                throw new AppointmentMismatchMonthException("Fehler: Der Termin (" + appointment.getTitle() + ", " + appointment.getStart().toString() + ") gehört nicht zum aktuellen Monat (" + shownDate.getMonth() + ").");
            }
            String formattedAppointment = appointment.getStart().format(DateTimeFormatter.ofPattern("HH:mm")) +
                    " - " +
                    appointment.getEnd().format(DateTimeFormatter.ofPattern("HH:mm")) +
                    " " +
                    appointment.getTitle();

            int day = appointment.getStart().getDayOfMonth();
            if (day > calendarCells.length) {
                throw new AppointmentOutOfMonthRangeException("Fehler: Der Tag des Termins ("+ appointment.getTitle() + ", " + appointment.getStart().toString() + ") liegt außerhalb des aktuellen Monats (" + shownDate.getMonth() + ").");
            }
            CalendarCell cell = calendarCells[day - 1];
            cell.addAppointment(formattedAppointment, appointment);
        } catch (AppointmentOutOfMonthRangeException e) {
            // Hier können Sie definieren, was passieren soll, wenn eine AppointmentOutOfMonthRangeException auftritt.
            System.err.println(e.getMessage());
        } catch (AppointmentMismatchMonthException e) {
            // Hier können Sie definieren, was passieren soll, wenn eine AppointmentMismatchMonthException auftritt.
            System.err.println(e.getMessage());
        }
    }

    /**
     * Aktualisiert die Ansicht des Kalenders basierend auf einer neuen Terminliste.
     *
     * @param terminListe Die neue Terminliste, die zur Aktualisierung der Ansicht verwendet werden soll.
     * @throws AppointmentOutOfMonthRangeException Wenn der Tag eines Termins in der Liste außerhalb des aktuellen Monats liegt.
     * @throws AppointmentMismatchMonthException Wenn der Monat eines Termins in der Liste nicht mit dem Monat der MonthView übereinstimmt.
     */
    @Override
    public void updateView(TerminListe terminListe) throws AppointmentOutOfMonthRangeException, AppointmentMismatchMonthException {
        System.out.println("Update");

        this.removeAll();

        int numberOfDays = shownDate.lengthOfMonth();
        calendarCells = new CalendarCell[numberOfDays];
        daysLabels = new JLabel[7];
        System.out.println(shownDate.getMonth());
        for (int i = 0; i < 7; i++) {
            DayOfWeek dayOfWeek = DayOfWeek.of((i) % 7 + 1);
            String dayName = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.GERMAN);
            JLabel dayLabel = new JLabel(dayName, SwingConstants.CENTER);
            daysLabels[i] = dayLabel;
            add(dayLabel);
        }

        int offset = (shownDate.withDayOfMonth(1).getDayOfWeek().getValue() + 6) % 7;
        for (int i = 0; i < offset; i++) {
            add(new JLabel());
        }

        for (int i = 1; i <= numberOfDays; i++) {
            CalendarCell cell = new CalendarCell(Integer.toString(i), terminListe, this, db);
            calendarCells[i - 1] = cell;
            add(cell);
        }

        // Add appointments from the list
        for (Termin termin : terminListe.getTermine()) {
            LocalDate terminDate = termin.getStart().toLocalDate();
            if(terminDate.getMonth() == shownDate.getMonth() && terminDate.getYear() == shownDate.getYear()){
                addAppointment(termin);
            }
        }

        revalidate();
        repaint();
    }

    /**
     * Löscht alle Termine aus den Kalenderzellen.
     */
    public void clearAppointments() {
        for (CalendarCell cell : calendarCells) {
            cell.clearAppointments();
        }
    }
    /**
     * Ruft die Feiertage für das aktuelle Jahr ab.
     */
    public void getHolidays(){
        HolidaysList holidaysList = new HolidaysList(yearMonth.getYear());
    }

    public YearMonth getYearMonth() {
        return this.yearMonth;
    }

    // ----------- Überschriebene Methoden -----------

    @Override
    public void nextPeriod() {
        this.shownDate = this.shownDate.plusMonths(1);
        this.yearMonth = YearMonth.from(this.shownDate);
    }
    @Override
    public void previousPeriod() {
        this.shownDate = shownDate.minusMonths(1);
        this.yearMonth = YearMonth.from(shownDate);
    }
    @Override
    public void todaysPeriod() {
        this.yearMonth=YearMonth.now();
    }

}
