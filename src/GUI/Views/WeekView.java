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
public class WeekView extends CalendarView {
    private TerminListe terminListe;
    private CalendarCell[] calendarCells;
    private JLabel[] daysLabels;
    private Database db;
    private YearMonth yearMonth;
    private ArrayList<Integer> weekNumbers = new ArrayList<>();;
    /**
     * Erstellt eine neue Wochenansicht mit dem angegebenen Jahr, Monat und Terminliste.
     *
     * @param year Das aktuelle Jahr als int.
     * @param month Der aktuelle Monat als int.
     * @param terminListe Eine Liste von Terminen
     */
    public WeekView(LocalDate shownDate, TerminListe terminListe, Database db) throws AppointmentOutOfMonthRangeException, AppointmentMismatchMonthException {
        super(shownDate, terminListe);
        this.db = db;
        this.terminListe = terminListe;

        this.shownDate = shownDate;
        int numberOfDays = 7; // Woche hat immer 7 Tage
        calendarCells = new CalendarCell[numberOfDays];
        daysLabels = new JLabel[7];

        setLayout(new GridLayout(0, 7));

        for (int i = 0; i < 7; i++) {
            DayOfWeek dayOfWeek = DayOfWeek.of(i + 1); // Start mit Montag
            String dayName = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.GERMAN);
            JLabel dayLabel = new JLabel(dayName, SwingConstants.LEFT);
            daysLabels[i] = dayLabel;
            add(dayLabel);
        }

        LocalDate today = LocalDate.now();
        int currentDayOfMonth = today.getDayOfMonth();
        int offset = (currentDayOfMonth + 6 - today.getDayOfWeek().getValue()) % 7;
        LocalDate startDate = today.minusDays(offset);
        for (int i = 0; i < 7; i++) {
            LocalDate date = startDate.plusDays(i);
            System.out.println(date.getDayOfMonth());
            weekNumbers.add(date.getDayOfMonth());
            CalendarCell cell = new CalendarCell(Integer.toString(date.getDayOfMonth()), terminListe, this, db);
            calendarCells[i] = cell;
            add(cell);
        }

        // Add appointments from the list
        for (Termin termin : terminListe.getTermine()) {
            LocalDate terminDate = termin.getStart().toLocalDate();
            if (terminDate.getMonth() == shownDate.getMonth() && terminDate.getYear() == shownDate.getYear()) {
                addAppointment(termin);
            }
        }
    }

    public void updateView() {
        LocalDate startDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
        int currentDayOfMonth = startDate.getDayOfMonth();
        int offset = (currentDayOfMonth + 6 - startDate.getDayOfWeek().getValue()) % 7;
        startDate = startDate.minusDays(offset);

        // Termine aktualisieren
        clearAppointments();
        for (Termin termin : terminListe.getTermine()) {
            LocalDate terminDate = termin.getStart().toLocalDate();
            if (terminDate.isAfter(startDate.minusDays(1)) && terminDate.isBefore(startDate.plusDays(7))) {
                addAppointment(termin);
            }
        }

        revalidate();
        repaint();
    }

    /**
     * Setzt den aktuellen Tag in der Wochenansicht.
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
     * Fügt einen Termin zu einem bestimmten Tag in der Wochenansicht hinzu.
     *
     * @param appointment Der Termin als String.
     */

    public void addAppointment(Termin appointment) {
        try {
        if (!appointment.getStart().getMonth().equals(shownDate.getMonth())) {
                throw new AppointmentMismatchMonthException("Fehler: Der Termin (" + appointment.getTitle() + ", " + appointment.getStart().toString() + ") gehört nicht zum aktuellen Monat (" + shownDate.getMonth() + ").");
        }
        String formattedAppointment = appointment.getStart().format(DateTimeFormatter.ofPattern("HH:mm")) +
                " - " +
                appointment.getEnd().format(DateTimeFormatter.ofPattern("HH:mm")) +
                " " +
                appointment.getTitle();

                int day = appointment.getStart().getDayOfMonth();
                if (!weekNumbers.contains(day)) {
                    throw new AppointmentOutOfMonthRangeException("Fehler: Der Tag des Termins ("+ appointment.getTitle() + ", " + appointment.getStart().toString() + ") liegt außerhalb des aktuellen Monats (" + shownDate.getMonth() + ").");
                }
                CalendarCell cell = calendarCells[weekNumbers.indexOf(day)];
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
     * Löscht alle Termine aus den Kalenderzellen.
     */
    public void clearAppointments() {
        for (CalendarCell cell : calendarCells) {
            cell.clearAppointments();
        }
    }

    public void getHolidays() {
        HolidaysList holidaysList = new HolidaysList(yearMonth.getYear());
    }

    // Beispielanwendung zum Testen der Klasse.
    public static void main(String[] args) throws AppointmentOutOfMonthRangeException, AppointmentMismatchMonthException {
        JFrame frame = new JFrame("Week View Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LocalDate shownDate = LocalDate.of(2023, 12, 1);
        Termin termin1 = new Termin("Terminname 1", "Typ 1", false, "2023-12-13", "2023-12-13", "10:00", "11:30");
        Termin termin2 = new Termin("Terminname 2", "Typ 2", false, "2023-12-14", "2023-12-14", "13:00", "14:30");
        Termin termin3 = new Termin("Terminname 2", "Typ 2", false, "2023-12-24", "2023-12-24", "13:00", "14:30");
        TerminListe terminListe = new TerminListe();
        terminListe.addTermin(termin1);
        terminListe.addTermin(termin2);
        terminListe.addTermin(termin3);

        Database db;
        
        db = null;

        WeekView weekView = new WeekView(shownDate, terminListe, db);
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

        weekNumbers.clear();
        this.removeAll();

        for (int i = 0; i < 7; i++) {
            DayOfWeek dayOfWeek = DayOfWeek.of(i + 1); // Start mit Montag
            String dayName = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.GERMAN);
            JLabel dayLabel = new JLabel(dayName, SwingConstants.LEFT);
            daysLabels[i] = dayLabel;
            add(dayLabel);
        }

        int currentDayOfMonth = shownDate.getDayOfMonth();
        int offset = (currentDayOfMonth + 6 - shownDate.getDayOfWeek().getValue()) % 7;
        LocalDate startDate = shownDate.minusDays(offset);
        for (int i = 0; i < 7; i++) {
            LocalDate date = startDate.plusDays(i);
            System.out.println(date.getDayOfMonth());
            weekNumbers.add(date.getDayOfMonth());
            CalendarCell cell = new CalendarCell(Integer.toString(date.getDayOfMonth()), terminListe, this, db);
            calendarCells[i] = cell;
            add(cell);
        }

        // Add appointments from the list
        for (Termin termin : terminListe.getTermine()) {
            LocalDate terminDate = termin.getStart().toLocalDate();
            if (terminDate.getMonth() == shownDate.getMonth() && terminDate.getYear() == shownDate.getYear()) {
                addAppointment(termin);
            }
        }

        revalidate();
        repaint();
    }

    @Override
    public void nextPeriod() {
        System.out.println(shownDate);
        this.shownDate = this.shownDate.plusDays(7);
        System.out.println(shownDate);
    }

    @Override
    public void previousPeriod() {
        this.shownDate = this.shownDate.minusDays(7);
    }

    @Override
    public void todaysPeriod() {
        this.shownDate= LocalDate.now();
    }

    public ArrayList<Integer> getWeekNumbers() {
        return weekNumbers;
    }
}