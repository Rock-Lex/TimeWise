//package GUI.Views;
//
//import Calendar.HolidaysList;
//import Calendar.Termin;
//import Calendar.TerminListe;
//import GUI.CalendarCell;
//import GUI.Exceptions.AppointmentMismatchMonthException;
//import GUI.Exceptions.AppointmentOutOfMonthRangeException;
//
//import javax.swing.*;
//import java.awt.*;
//import java.time.DayOfWeek;
//import java.time.LocalDate;
//import java.time.YearMonth;
//import java.time.format.DateTimeFormatter;
//import java.time.format.TextStyle;
//import java.util.Locale;
//
///**
// * Eine benutzerdefinierte Swing-Komponente zur Darstellung einer Wochenansicht im Kalender.
// *
// * Autor: Tobias Rehm
// * Version: 1.0.0
// * Erstellt am: 20.07.2023
// * Letzte Änderung: 27.07.2023
// */
//public class WeekView extends JPanel {
//    private TerminListe terminListe;
//    private YearMonth yearMonth;
//    private CalendarCell[] calendarCells;
//    private JLabel[] daysLabels;
//
//    /**
//     * Erstellt eine neue Wochenansicht mit dem angegebenen Jahr, Monat und Terminliste.
//     *
//     * @param year Das aktuelle Jahr als int.
//     * @param month Der aktuelle Monat als int.
//     * @param terminListe Eine Liste von Terminen
//     */
//    public WeekView(int year, int month, TerminListe terminListe) throws AppointmentOutOfMonthRangeException, AppointmentMismatchMonthException {
//        this.terminListe = terminListe;
//        yearMonth = YearMonth.of(year, month);
//        int numberOfDays = 7; // Woche hat immer 7 Tage
//        calendarCells = new CalendarCell[numberOfDays];
//        daysLabels = new JLabel[7];
//
//        setLayout(new GridLayout(0, 7));
//
//        for (int i = 0; i < 7; i++) {
//            DayOfWeek dayOfWeek = DayOfWeek.of((i + 1) % 7); // Start mit Montag
//            String dayName = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.GERMAN);
//            JLabel dayLabel = new JLabel(dayName, SwingConstants.CENTER);
//            daysLabels[i] = dayLabel;
//            add(dayLabel);
//        }
///
//        int currentDayOfMonth = LocalDate.now().getDayOfMonth(); // Aktueller Tag des Monats
//        int offset = (currentDayOfMonth + 6 - LocalDate.now().getDayOfWeek().getValue()) % 7; // Offset für den ersten Tag der Woche
//        LocalDate startDate = LocalDate.of(year, month, currentDayOfMonth - offset); // Anfang der Woche
//        for (int i = 0; i < numberOfDays; i++) {
//            CalendarCell cell = new CalendarCell(Integer.toString(startDate.getDayOfMonth()));
//            calendarCells[i] = cell;
//            add(cell);
//            startDate = startDate.plusDays(1); // Nächster Tag
//        }
//
//
//        // Termine hinzufügen
//        for (Termin termin : terminListe.getTermine()) {
//            LocalDate terminDate = termin.getStart().toLocalDate();
//            if (terminDate.isAfter(startDate.minusDays(1)) && terminDate.isBefore(startDate.plusDays(7))) {
//                addAppointment(termin);
//            }
//        }
//    }
//
//    public void nextWeek() {
//        this.yearMonth = this.yearMonth.plusWeeks(1);
//        updateView();
//    }
//
//    public void previousWeek() {
//        this.yearMonth = this.yearMonth.minusWeeks(1);
//        updateView();
//    }
//
//    public void currentWeek() {
//        this.yearMonth = YearMonth.now();
//        updateView();
//    }
//
//    public void updateView() {
//        LocalDate startDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
//        int currentDayOfMonth = startDate.getDayOfMonth();
//        int offset = (currentDayOfMonth + 6 - startDate.getDayOfWeek().getValue()) % 7;
//        startDate = startDate.minusDays(offset);
//
//        for (int i = 0; i < 7; i++) {
//            calendarCells[i].setText(Integer.toString(startDate.getDayOfMonth()));
//            startDate = startDate.plusDays(1);
//        }
//
//        // Termine aktualisieren
//        clearAppointments();
//        for (Termin termin : terminListe.getTermine()) {
//            LocalDate terminDate = termin.getStart().toLocalDate();
//            if (terminDate.isAfter(startDate.minusDays(1)) && terminDate.isBefore(startDate.plusDays(7))) {
//                addAppointment(termin);
//            }
//        }
//
//        revalidate();
//        repaint();
//    }
//
//    /**
//     * Setzt den aktuellen Tag in der Wochenansicht.
//     *
//     * @param day Der aktuelle Tag als int.
//     */
//    public void setToday(int day) {
//        for (CalendarCell cell : calendarCells) {
//            cell.setToday(false);
//        }
//        calendarCells[day - 1].setToday(true);
//    }
//
//    /**
//     * Fügt einen Termin zu einem bestimmten Tag in der Wochenansicht hinzu.
//     *
//     * @param appointment Der Termin als String.
//     */
//
//    public void addAppointment(Termin appointment) {
//        LocalDate terminDate = appointment.getStart().toLocalDate();
//        int dayOfWeek = terminDate.getDayOfWeek().getValue();
//        int index = (dayOfWeek + 5) % 7;
//        calendarCells[index].addAppointment(appointment.getTitle());
//    }
//
//    /**
//     * Löscht alle Termine aus den Kalenderzellen.
//     */
//    public void clearAppointments() {
//        for (CalendarCell cell : calendarCells) {
//            cell.clearAppointments();
//        }
//    }
//
//    public void getHolidays() {
//        HolidaysList holidaysList = new HolidaysList(yearMonth.getYear());
//    }
//
//    // Beispielanwendung zum Testen der Klasse.
//    public static void main(String[] args) throws AppointmentOutOfMonthRangeException, AppointmentMismatchMonthException {
//        JFrame frame = new JFrame("Week View Example");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        Termin termin1 = new Termin("Terminname 1", "Typ 1", false, "2023-06-02", "2023-06-02", "10:00", "11:30");
//        Termin termin2 = new Termin("Terminname 2", "Typ 2", false, "2023-06-01", "2023-06-01", "13:00", "14:30");
//        TerminListe terminListe = new TerminListe();
//        terminListe.addTermin(termin1);
//        terminListe.addTermin(termin2);
//
//        WeekView weekView = new WeekView(2023, 6, terminListe);
//        weekView.addAppointment(termin1);
//        weekView.addAppointment(termin2);
//
//        weekView.setToday(2);
//
//        frame.getContentPane().add(weekView);
//        frame.pack();
//        frame.setVisible(true);
//    }
//}