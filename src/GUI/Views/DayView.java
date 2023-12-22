package GUI.Views;

import Calendar.HolidaysList;
import Calendar.Termin;
import Calendar.TerminListe;
import GUI.CalendarCell;
import GUI.Exceptions.AppointmentMismatchMonthException;
import GUI.Exceptions.AppointmentOutOfMonthRangeException;
import IOManager.Database;
import IOManager.Exceptions.SQLPackageException;
import IOManager.Exceptions.WrongPathException;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Eine benutzerdefinierte Swing-Komponente zur Darstellung einer Wochenansicht im Kalender.
 *
 * @author Tobias Rehm
 * @version 1.0.0
 * @since 20.07.2023
 * Letzte Änderung: 27.07.2023
 */
public class DayView extends CalendarView {
    private TerminListe terminListe;
    private CalendarCell calendarCell;
    
    private JLabel dayLabel;
    private Database db;
    private YearMonth yearMonth;
    /**
     * Erstellt eine neue Wochenansicht mit dem angegebenen Jahr, Monat und Terminliste.
     *
     * @param year Das aktuelle Jahr als int.
     * @param month Der aktuelle Monat als int.
     * @param terminListe Eine Liste von Terminen
     */
    public DayView(LocalDate shownDate, TerminListe terminListe, Database db) throws AppointmentOutOfMonthRangeException, AppointmentMismatchMonthException {
        super(shownDate, terminListe);
        this.db = db;
        this.terminListe = terminListe;

        this.shownDate = shownDate;
        dayLabel = new JLabel();

        setLayout(new GridLayout(0, 2));

        DayOfWeek dayOfWeek = shownDate.getDayOfWeek();
        String dayName = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.GERMAN);
        dayLabel = new JLabel(dayName, SwingConstants.LEFT);
        add(dayLabel);

        LocalDate today = LocalDate.now();
        int currentDayOfMonth = today.getDayOfMonth();
        calendarCell = new CalendarCell(Integer.toString(shownDate.getDayOfMonth()), terminListe, this, db);
        add(calendarCell);

        // Add appointments from the list
        for (Termin termin : terminListe.getTermine()) {
            LocalDate terminDate = termin.getStart().toLocalDate();
            if (terminDate.getDayOfMonth()  == shownDate.getDayOfMonth() &&
                terminDate.getMonth()       == shownDate.getMonth() &&
                terminDate.getYear()        == shownDate.getYear()) {
                addAppointment(termin);
            }
        }
    }

    /**
     * Setzt den aktuellen Tag in der Wochenansicht.
     *
     * @param day Der aktuelle Tag als int.
     */
    public void setToday(int day) {
        calendarCell.setToday(false);
    }

    /**
     * Fügt einen Termin zu einem bestimmten Tag in der Wochenansicht hinzu.
     *
     * @param appointment Der Termin als String.
     */
        // !appointment.getStart().getMonth().equals(shownDate.getMonth())
    public void addAppointment(Termin appointment) {
        try {
            LocalDateTime terminDate = appointment.getStart();
            if (!(terminDate.getDayOfMonth()  == shownDate.getDayOfMonth() &&
                terminDate.getMonth()       == shownDate.getMonth() &&
                terminDate.getYear()        == shownDate.getYear())) {
                throw new AppointmentMismatchMonthException("Fehler: Der Termin (" + appointment.getTitle() + ", " + appointment.getStart().toString() + ") gehört nicht zum aktuellen Monat (" + shownDate.getMonth() + ").");
            }
            String formattedAppointment = appointment.getStart().format(DateTimeFormatter.ofPattern("HH:mm")) +
                " - " +
                appointment.getEnd().format(DateTimeFormatter.ofPattern("HH:mm")) +
                " " +
                appointment.getTitle();

                int day = appointment.getStart().getDayOfMonth();
                calendarCell.addAppointment(formattedAppointment, appointment);
        } catch (AppointmentMismatchMonthException e) {
            // Hier können Sie definieren, was passieren soll, wenn eine AppointmentMismatchMonthException auftritt.
            System.err.println(e.getMessage());
        }
    }

    /**
     * Löscht alle Termine aus den Kalenderzellen.
     */
    public void clearAppointments() {
        calendarCell.clearAppointments();
    }

    public void getHolidays() {
        HolidaysList holidaysList = new HolidaysList(yearMonth.getYear());
    }

    // Beispielanwendung zum Testen der Klasse.
    public static void main(String[] args) throws AppointmentOutOfMonthRangeException, AppointmentMismatchMonthException {
        JFrame frame = new JFrame("Week View Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LocalDate shownDate = LocalDate.of(2023, 12, 1);
        Termin termin1 = new Termin("Terminname 1", "Typ 1", false, "2023-12-22", "2023-12-22", "10:00", "11:30");
        Termin termin2 = new Termin("Terminname 2", "Typ 2", false, "2023-12-22", "2023-12-22", "13:00", "14:30");
        Termin termin3 = new Termin("Terminname 2", "Typ 2", false, "2023-12-22", "2023-12-22", "13:00", "14:30");
        TerminListe terminListe = new TerminListe();
        terminListe.addTermin(termin1);
        terminListe.addTermin(termin2);
        terminListe.addTermin(termin3);

        Database db;
        
        db = null;

        DayView weekView = new DayView(shownDate, terminListe, db);
        weekView.addAppointment(termin1);
        weekView.addAppointment(termin2);

        weekView.setToday(2);

        frame.getContentPane().add(weekView);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void updateView(TerminListe terminListe)
            throws AppointmentOutOfMonthRangeException, AppointmentMismatchMonthException {
        System.out.println("Update");

        this.removeAll();

        DayOfWeek dayOfWeek = shownDate.getDayOfWeek();
        String dayName = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.GERMAN);
        dayLabel = new JLabel(dayName, SwingConstants.LEFT);
        add(dayLabel);

        LocalDate today = LocalDate.now();
        int currentDayOfMonth = today.getDayOfMonth();
        calendarCell = new CalendarCell(Integer.toString(shownDate.getDayOfMonth()), terminListe, this, db);
        add(calendarCell);

        // Add appointments from the list
        for (Termin termin : terminListe.getTermine()) {
            LocalDate terminDate = termin.getStart().toLocalDate();
            if (terminDate.getDayOfMonth()  == shownDate.getDayOfMonth() &&
                terminDate.getMonth()       == shownDate.getMonth() &&
                terminDate.getYear()        == shownDate.getYear()) {
                addAppointment(termin);
            }
        }

        revalidate();
        repaint();
    }

    @Override
    public void nextPeriod() {
        System.out.println(shownDate);
        this.shownDate = this.shownDate.plusDays(1);
        System.out.println(shownDate);
    }

    @Override
    public void previousPeriod() {
        this.shownDate = this.shownDate.minusDays(1);
    }

    @Override
    public void todaysPeriod() {
        this.shownDate= LocalDate.now();
    }
}