package GUI.Views;

import Calendar.Termin;
import Calendar.TerminListe;
import GUI.monthView.MonthView;

import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Locale;
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


    public CalendarViewManager() {
        views = new HashMap<>();
        this.currentYearMonth = YearMonth.now();

        // Initialisiere die verschiedenen Views
        int currentYear = YearMonth.now().getYear();
        int currentMonth = YearMonth.now().getMonthValue();
        views.put("month", new MonthView(currentYear, currentMonth));
        // Hier weitere Views hinzufügen


        // Monatsansicht als Standard gesetzt
        currentView = views.get("month");
    }

    public void switchToView(String viewName) {
        if (!views.containsKey(viewName)) {
            throw new IllegalArgumentException("Unbekannte Ansicht: " + viewName);
        }

        currentView = views.get(viewName);
        // Aktualisieren der Ansicht
        currentView.updateView();
    }

    public void nextMonth(TerminListe terminListe) {
        YearMonth nextMonth = currentView.getYearMonth().plusMonths(1);

        if (currentView instanceof MonthView) {
            ((MonthView) currentView).setYearMonth(nextMonth);
        }

        updateCurrentMonthView(terminListe);
        reloadAppointments(terminListe);
    }

    public void previousMonth(TerminListe terminListe) {
        YearMonth previousMonth = currentView.getYearMonth().minusMonths(1);

        if (currentView instanceof MonthView) {
            ((MonthView) currentView).setYearMonth(previousMonth);
        }

        updateCurrentMonthView(terminListe);
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
            monthView.updateView();
        }
    }

    public MonthView updateCurrentMonthView(TerminListe terminListe) {
        System.out.println("CalenderView Terminlistengröße: " + terminListe.getTermine().size());
        if (!(currentView instanceof MonthView)) {
            throw new IllegalArgumentException("Current view is not MonthView");
        }

        MonthView newView = (MonthView) currentView;
        YearMonth currentMonth = newView.getYearMonth();

        // Lösche alle Termine der aktuellen Ansicht
        newView.clearAppointments();

        // Füge nur die Termine des aktuellen Monats zur Ansicht hinzu
        for (Termin termin : terminListe.getTermine()) {
            if (termin.getStart().getMonth() == currentMonth.getMonth() && termin.getStart().getYear() == currentMonth.getYear()) {
                newView.addAppointment(termin);
            }
        }

        newView.updateView();
        return newView;
    }
    public CalendarView getCurrentView() {
        return currentView;
    }
    public String getCurrentMonthAndYear() {
        Month month = currentYearMonth.getMonth();
        int year = currentYearMonth.getYear();

        // formatiert den Monat in seine volle textuelle Darstellung, z.B. "January", "February", etc.
        String monthName = month.getDisplayName(TextStyle.FULL, Locale.GERMANY);

        // gibt den formatierten String zurück
        return monthName + " " + year;
    }
}