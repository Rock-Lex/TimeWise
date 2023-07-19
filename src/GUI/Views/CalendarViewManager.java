package GUI.Views;

import Calendar.Termin;
import Calendar.TerminListe;
import GUI.monthView.MonthView;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

/**
 * Verwaltungsklasse für die verschiedenen Kalenderansichten.
 *
 * Autor: Philipp Voß
 * Version: 1.0
 * Erstellt am: 16.07.2023
 *
 */
public class CalendarViewManager {
    private Map<String, CalendarView> views;
    private CalendarView currentView;
    private YearMonth currentYearMonth;
    private TerminListe terminListe;

    public CalendarViewManager(TerminListe terminListe) {
        views = new HashMap<>();
        this.currentYearMonth = YearMonth.now();
        this.terminListe = terminListe;

        // Initialisiere die verschiedenen Views
        int currentYear = YearMonth.now().getYear();
        int currentMonth = YearMonth.now().getMonthValue();
        views.put("month", new MonthView(currentYear, currentMonth, this.terminListe));
        // Hier weitere Views hinzufügen


        // Monatsansicht als Standard gesetzt
        currentView = views.get("month");
    }
    public void nextPeriod(TerminListe terminListe) {
        currentView.nextPeriod();
        reloadAppointments(terminListe);
    }

    public void previousPeriod(TerminListe terminListe) {
        currentView.previousPeriod();
        reloadAppointments(terminListe);
    }

    private void reloadAppointments(TerminListe terminListe) {
        if (currentView instanceof MonthView) {
            MonthView monthView = (MonthView) currentView;
            monthView.clearAppointments();
            for (Termin termin : terminListe.getTermine()) {
                if (termin.getStart().getMonth() == monthView.getYearMonth().getMonth()
                        && termin.getStart().getYear() == monthView.getYearMonth().getYear()) {
                    monthView.addAppointment(termin);
                }
            }
            monthView.updateView(terminListe);
        }
    }

    public MonthView updateCurrentMonthView(TerminListe terminListe) {
        System.out.println("CalenderView Terminlistengröße: " + terminListe.getTermine().size());
        if (!(currentView instanceof MonthView)) {
            throw new IllegalArgumentException("Current view is not MonthView");
        }

        MonthView newView = (MonthView) currentView;
        YearMonth currentMonth = newView.getYearMonth();

        newView.clearAppointments();

        for (Termin termin : terminListe.getTermine()) {
            if (termin.getStart().getMonth() == currentMonth.getMonth() && termin.getStart().getYear() == currentMonth.getYear()) {
                newView.addAppointment(termin);
            }
        }

        newView.updateView(terminListe);
        return newView;
    }
    public CalendarView getCurrentView() {
        return currentView;
    }
}