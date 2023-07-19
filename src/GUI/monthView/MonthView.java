package GUI.monthView;


import Calendar.Termin;
import Calendar.TerminListe;
import GUI.CalendarCell;
import GUI.Views.CalendarView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Eine benutzerdefinierte Swing-Komponente zur Darstellung einer Monatsansicht im Kalender.
 *
 * Autor: Philipp Voß
 * Version: 1.4
 * Erstellt am: 16.17.2023
 */
public class MonthView extends CalendarView {
    private TerminListe terminListe;
    private YearMonth yearMonth;
    private CalendarCell[] calendarCells;
    private JLabel[] daysLabels;

    /**
     * Erstellt eine neue Monatsansicht mit dem angegebenen Jahr und Monat.
     *
     * @param year Der aktuelle Jahr als int.
     * @param month Der aktuelle Monat als int.
     */
    public MonthView(int year, int month, TerminListe terminListe) {
        super(year, month, terminListe);
        this.terminListe = terminListe;
        yearMonth = YearMonth.of(year, month);
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

        int offset = (LocalDate.of(year, month, 1).getDayOfWeek().getValue() + 6) % 7;  // Erster Tag der ersten Woche im Monat
        for (int i = 0; i < offset; i++) {  // Hinzufügen leerer Zellen als Offset am Monatsanfang
            add(new JLabel());
        }

        for (int i = 1; i <= numberOfDays; i++) {
            CalendarCell cell = new CalendarCell(Integer.toString(i));
            calendarCells[i - 1] = cell;
            add(cell);
        }

        // Add appointments from the list
        for (Termin termin : terminListe.getTermine()) {
            addAppointment(termin);
        }
    }

    @Override
    public void nextPeriod() {
        this.yearMonth = this.yearMonth.plusMonths(1);
    }

    @Override
    public void previousPeriod() {
        this.yearMonth = this.yearMonth.minusMonths(1);
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
        // Vergleich des Monats des Termins mit dem Monat der `MonthView`
        if (appointment.getStart().getMonth() == yearMonth.getMonth()) {
            String formattedAppointment = appointment.getStart().format(DateTimeFormatter.ofPattern("HH:mm")) +
                    " - " +
                    appointment.getEnd().format(DateTimeFormatter.ofPattern("HH:mm")) +
                    " " +
                    appointment.getTitle();

            int day = appointment.getStart().getDayOfMonth(); // Extrahieren des Tages aus dem Termin
            if (day <= calendarCells.length) {
                CalendarCell cell = calendarCells[day - 1];
                cell.addAppointment(formattedAppointment, appointment);  // Appointment an UI Methode übergeben
            } else {
                // Behandlung des Fehlers, wenn der Tag des Termins größer ist als die Länge des `calendarCells` Arrays
                System.out.println("Fehler: Der Tag des Termins ("+ appointment.getTitle() + day + ") liegt außerhalb des aktuellen Monats (" + yearMonth.getMonth() + ").");
            }
        } else {
            // Behandlung des Fehlers, wenn der Monat des Termins nicht dem Monat der `MonthView` entspricht
            System.out.println("Fehler: Der Termin gehört nicht zum aktuellen Monat (" + yearMonth.getMonth() + ").");
            System.out.println("Es handelt sich um Termin: " + appointment.getTitle());
            System.out.println(appointment.toString());
        }
    }


    @Override
    public void updateView(TerminListe terminListe) {
        System.out.println("Update");

        this.removeAll();

        System.out.println(yearMonth);

        int numberOfDays = yearMonth.lengthOfMonth();
        calendarCells = new CalendarCell[numberOfDays];
        daysLabels = new JLabel[7];

        for (int i = 0; i < 7; i++) {
            DayOfWeek dayOfWeek = DayOfWeek.of((i) % 7 + 1);
            String dayName = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.GERMAN);
            JLabel dayLabel = new JLabel(dayName, SwingConstants.CENTER);
            daysLabels[i] = dayLabel;
            add(dayLabel);
        }

        int offset = (LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1).getDayOfWeek().getValue() + 6) % 7;
        for (int i = 0; i < offset; i++) {
            add(new JLabel());
        }

        for (int i = 1; i <= numberOfDays; i++) {
            CalendarCell cell = new CalendarCell(Integer.toString(i));
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

        revalidate();
        repaint();
    }


    @Override
    public void setYearMonth(YearMonth yearMonth) {
        super.setYearMonth(yearMonth);
        this.yearMonth = yearMonth;
    }

    public YearMonth getYearMonth() {
        return this.yearMonth;
    }

    public void clearAppointments() {
        for (CalendarCell cell : calendarCells) {
            cell.clearAppointments();
        }
    }
    /**
     * Beispielanwendung zum Testen der Klasse.
     *
     * @param args Kommandozeilenargumente (werden ignoriert).
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Month View Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Termin termin1 = new Termin("Terminname 1", "Typ 1", false, "2023-06-02", "2023-06-02", "10:00", "11:30");
        Termin termin2 = new Termin("Terminname 2", "Typ 2", false, "2023-06-01", "2023-06-01", "13:00", "14:30");
        TerminListe terminListe = new TerminListe();
        terminListe.addTermin(termin1);
        terminListe.addTermin(termin2);

        MonthView monthView = new MonthView(2023, 6, terminListe);
        monthView.addAppointment(termin1);
        monthView.addAppointment(termin2);

        monthView.setToday(18);

        frame.getContentPane().add(monthView);
        frame.pack();
        frame.setVisible(true);
    }
}
