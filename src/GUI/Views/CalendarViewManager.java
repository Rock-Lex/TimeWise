package GUI.Views;

import Calendar.Termin;
import Calendar.TerminListe;
import GUI.Exceptions.AppointmentMismatchMonthException;
import GUI.Exceptions.AppointmentOutOfMonthRangeException;
import IOManager.Database;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Verwaltungsklasse für die verschiedenen Kalenderansichten.
 *
 * @author  Philipp Voß
 * @version 1.3
 * @since 16.07.2023
 * Letzte Änderung: 19.07.2023
 */
public class CalendarViewManager {
    private Map<String, CalendarView> views;
    private CalendarView currentView;
    private YearMonth currentYearMonth;
    private TerminListe terminListe;
    private Database db;
    private LocalDate shownDate;

    // --------------------------- Konstruktor -------------------------------------------
    /**
     * Konstruktor für die Klasse CalendarViewManager.
     *
     * @param terminListe Eine Liste von Terminen.
     */
    public CalendarViewManager(TerminListe terminListe, Database db) throws AppointmentOutOfMonthRangeException, AppointmentMismatchMonthException {
        views = new HashMap<>();
        this.shownDate = LocalDate.now();
        this.terminListe = terminListe;
        this.db = db;
        // Initialisiere die verschiedenen Views
        int currentYear = YearMonth.now().getYear();
        int currentMonth = YearMonth.now().getMonthValue();
        views.put("month", new MonthView(shownDate, this.terminListe, db));
        // Hier weitere Views hinzufügen
        views.put("week", new WeekView(shownDate, this.terminListe, db));
        views.put("day", new DayView(shownDate, this.terminListe, db));
        // Monatsansicht als Standard gesetzt
        currentView = views.get("month");
    }

    // --------------------------- Perioden-Methoden -------------------------------------------
    /**
     * Wechselt zur nächsten Zeitspanne in der aktuellen Ansicht.
     *
     * @param terminListe Eine Liste von Terminen.
     */
    public void nextPeriod(TerminListe terminListe) throws AppointmentOutOfMonthRangeException, AppointmentMismatchMonthException {
        currentView.nextPeriod();
        System.out.println(terminListe.getTermine().size());
        reloadAppointments(terminListe);
    }

    /**
     * Wechselt zur vorherigen Zeitspanne in der aktuellen Ansicht.
     *
     * @param terminListe Eine Liste von Terminen.
     */
    public void previousPeriod(TerminListe terminListe) throws AppointmentOutOfMonthRangeException, AppointmentMismatchMonthException {
        currentView.previousPeriod();
        System.out.println(terminListe.getTermine().size());
        reloadAppointments(terminListe);
    }

    // --------------------------- Termine-Methoden -------------------------------------------
    /**
     * Aktualisiert die Termine in der aktuellen Ansicht.
     *
     * @param terminListe Eine Liste von Terminen.
     */
    private void reloadAppointments(TerminListe terminListe) throws AppointmentOutOfMonthRangeException, AppointmentMismatchMonthException {
        if (currentView instanceof MonthView) {
            MonthView monthView = (MonthView) currentView;
            monthView.clearAppointments();
            for (Termin termin : terminListe.getTermine()) {
                if (termin.getStart().getMonth() == monthView.getDate().getMonth()
                        && termin.getStart().getYear() == monthView.getDate().getYear()) {
                    monthView.addAppointment(termin);
                }
            }
            monthView.updateView(terminListe);
        }
        else if (currentView instanceof WeekView) {
            WeekView weekView = (WeekView) currentView;
            weekView.clearAppointments();
            for (Termin termin : terminListe.getTermine()) {
                
                if (weekView.getWeekNumbers().contains(termin.getStart().getDayOfYear())
                        && termin.getStart().getYear() == weekView.getDate().getYear()) {
                    weekView.addAppointment(termin);
                }
            }
            System.out.println(weekView.getWeekNumbers());
            weekView.updateView(terminListe);
        }
        else if (currentView instanceof DayView) {
            DayView dayView = (DayView) currentView;
            dayView.clearAppointments();
            for (Termin termin : terminListe.getTermine()) {
                if (termin.getStart().getDayOfMonth() == dayView.getDate().getDayOfMonth() &&
                    termin.getStart().getMonth() == dayView.getDate().getMonth() &&
                    termin.getStart().getYear() == dayView.getDate().getYear()) {
                    dayView.addAppointment(termin);
                }
            }
            dayView.updateView(terminListe);
        }
    }

    // --------------------------- Ansichts-Methoden -------------------------------------------
    /**
     * Gibt die aktuelle Ansicht zurück.
     *
     * @return Die aktuelle Ansicht.
     */
    public CalendarView getCurrentView() {
        return currentView;
    }

    /**
     * Markiert den aktuellen Tag in der Kalenderansicht und aktualisiert die Termine.
     *
     * @throws AppointmentOutOfMonthRangeException Wenn der aktuelle Tag außerhalb des angezeigten Monats liegt.
     * @throws AppointmentMismatchMonthException   Wenn der aktuelle Tag nicht mit dem angezeigten Monat übereinstimmt.
     */
    public void jumpToCurrentDay() throws AppointmentOutOfMonthRangeException, AppointmentMismatchMonthException {
        this.currentView.todaysPeriod();
        reloadAppointments(terminListe);
    }
    /**
     * Wechselt zur angegebenen Datumsansicht und aktualisiert die Termine.
     *
     * @param date Das Datum, zu dem gewechselt werden soll.
     * @throws AppointmentOutOfMonthRangeException Wenn das angegebene Datum außerhalb des angezeigten Monats liegt.
     * @throws AppointmentMismatchMonthException   Wenn das angegebene Datum nicht mit dem angezeigten Monat übereinstimmt.
     */
    public void jumpToDate(LocalDate date) throws AppointmentOutOfMonthRangeException, AppointmentMismatchMonthException {
        // Setzt das aktuelle Jahr und den aktuellen Monat auf das eingegebene Datum
        this.currentYearMonth = YearMonth.of(date.getYear(), date.getMonth());

        // Aktualisiert die Ansicht
        this.currentView.setDate(date);

        // Wenn die aktuelle Ansicht eine MonthView ist, aktualisieren Sie die Termine
        if (this.currentView instanceof MonthView) {
            ((MonthView) this.currentView).clearAppointments();
            for (Termin termin : this.terminListe.getTermine()) {
                if (termin.getStart().getYear() == currentYearMonth.getYear()
                        && termin.getStart().getMonth() == currentYearMonth.getMonth()) {
                    ((MonthView) this.currentView).addAppointment(termin);
                }
            }
            ((MonthView) this.currentView).updateView(this.terminListe);
        }
        else if (currentView instanceof WeekView) {
            WeekView weekView = (WeekView) currentView;
            weekView.clearAppointments();
            for (Termin termin : terminListe.getTermine()) {
                
                if (weekView.getWeekNumbers().contains(termin.getStart().getDayOfYear())
                        && termin.getStart().getYear() == weekView.getDate().getYear()) {
                    weekView.addAppointment(termin);
                }
            }
            System.out.println(weekView.getWeekNumbers());
            weekView.updateView(terminListe);
        }        else if (currentView instanceof DayView) {
            DayView dayView = (DayView) currentView;
            dayView.clearAppointments();
            for (Termin termin : terminListe.getTermine()) {
                if (termin.getStart().getDayOfMonth() == dayView.getDate().getDayOfMonth() &&
                    termin.getStart().getMonth() == dayView.getDate().getMonth() &&
                    termin.getStart().getYear() == dayView.getDate().getYear()) {
                    dayView.addAppointment(termin);
                }
            }
            dayView.updateView(terminListe);
        }
    }
    public void changeCurrentView(String category) {
        if (Arrays.asList("month", "week", "day").contains(category)) {
            currentView = views.get(category);
        }
    }
}
