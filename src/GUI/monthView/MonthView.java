package GUI.monthView;


import Calendar.Termin;
import GUI.CalendarCell;
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
 * Version: 1.3
 * Erstellt am: 18.06.2023
 */
public class MonthView extends JPanel {
    private YearMonth yearMonth;
    private CalendarCell[] calendarCells;
    private JLabel[] daysLabels;


    /**
     * Erstellt eine neue Monatsansicht mit dem angegebenen Jahr und Monat.
     *
     * @param year Der aktuelle Jahr als int.
     * @param month Der aktuelle Monat als int.
     */
    public MonthView(int year, int month) {
        yearMonth = YearMonth.of(year, month);
        int numberOfDays = yearMonth.lengthOfMonth();
        calendarCells = new CalendarCell[numberOfDays];
        daysLabels = new JLabel[7];

        setLayout(new GridLayout(0, 7));

        // Add days of the week
        for (int i = 0; i < 7; i++) {
            DayOfWeek dayOfWeek = DayOfWeek.of((i) % 7 + 1);  // Start with Monday
            String dayName = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.GERMAN);
            JLabel dayLabel = new JLabel(dayName, SwingConstants.CENTER);
            daysLabels[i] = dayLabel;
            add(dayLabel);
        }

        int offset = (LocalDate.of(year, month, 1).getDayOfWeek().getValue() + 6) % 7;  // Day of the week of the first day of the month
        for (int i = 0; i < offset; i++) {  // Add empty cells for days before the start of the month
            add(new JLabel());
        }

        for (int i = 1; i <= numberOfDays; i++) {
            CalendarCell cell = new CalendarCell(Integer.toString(i));
            calendarCells[i - 1] = cell;
            add(cell);
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
     * @param day Der Tag als int.
     * @param appointment Der Termin als String.
     */



    public void addAppointment(int day, Termin appointment) {
        String formattedAppointment = appointment.getStart().format(DateTimeFormatter.ofPattern("HH:mm")) +
                " - " +
                appointment.getEnd().format(DateTimeFormatter.ofPattern("HH:mm")) +
                " " +
                appointment.getTitle();

        CalendarCell cell = calendarCells[day - 1];
        cell.addAppointment(formattedAppointment, appointment);  // Pass the appointment object to the method
    }


    /**
     * Beispielanwendung zum Testen der Klasse.
     *
     * @param args Kommandozeilenargumente (werden ignoriert).
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Month View Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MonthView monthView = new MonthView(2023, 6);
        Termin termin1 = new Termin("Terminname 1", "Typ 1", false, "2023-06-01", "2023-06-01", "10:00", "11:30");
        Termin termin2 = new Termin("Terminname 2", "Typ 2", false, "2023-06-01", "2023-06-01", "13:00", "14:30");
        monthView.addAppointment(1, termin1);
        monthView.addAppointment(1, termin2);

        monthView.setToday(18);

        frame.getContentPane().add(monthView);
        frame.pack();
        frame.setVisible(true);
    }

}
