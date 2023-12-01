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
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
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
    private YearMonth yearMonth;
    private CalendarCell[] calendarCells;
    private JLabel[] daysLabels;
    private Database db;
    /**
     * Erstellt eine neue Wochenansicht mit dem angegebenen Jahr, Monat und Terminliste.
     *
     * @param year Das aktuelle Jahr als int.
     * @param month Der aktuelle Monat als int.
     * @param terminListe Eine Liste von Terminen
     */
    public WeekView(int year, int month, TerminListe terminListe, Database db) throws AppointmentOutOfMonthRangeException, AppointmentMismatchMonthException {
        super(year, month, terminListe);
        this.terminListe = terminListe;
        yearMonth = YearMonth.of(year, month);
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

    public void currentWeek() {
        this.yearMonth = YearMonth.now();
        updateView();
    }

    public void updateView() {
        LocalDate startDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
        int currentDayOfMonth = startDate.getDayOfMonth();
        int offset = (currentDayOfMonth + 6 - startDate.getDayOfWeek().getValue()) % 7;
        startDate = startDate.minusDays(offset);

//        for (int i = 0; i < 7; i++) {
//            calendarCells[i].setText(Integer.toString(startDate.getDayOfMonth()));
//            startDate = startDate.plusDays(1);
//        }

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
        LocalDate terminDate = appointment.getStart().toLocalDate();
        int dayOfWeek = terminDate.getDayOfWeek().getValue();
        int index = (dayOfWeek + 5) % 7;
//        calendarCells[index].addAppointment(appointment.getTitle());
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

        Termin termin1 = new Termin("Terminname 1", "Typ 1", false, "2023-12-02", "2023-12-02", "10:00", "11:30");
        Termin termin2 = new Termin("Terminname 2", "Typ 2", false, "2023-12-01", "2023-12-01", "13:00", "14:30");
        TerminListe terminListe = new TerminListe();
        terminListe.addTermin(termin1);
        terminListe.addTermin(termin2);

        Database db;
        
        db = null;

        WeekView weekView = new WeekView(2023, 12, terminListe, db);
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateView'");
    }

    @Override
    public void nextPeriod() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'nextPeriod'");
    }

    @Override
    public void previousPeriod() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'previousPeriod'");
    }
}