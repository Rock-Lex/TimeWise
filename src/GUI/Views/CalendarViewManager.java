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
 * Version: 1.3
 * Erstellt am: 16.07.2023
 * Letzte Änderung: 19.07.2023
 */
public class CalendarViewManager {
    private Map<String, CalendarView> views;
    private CalendarView currentView;
    private YearMonth currentYearMonth;
    private TerminListe terminListe;
    /**
     * Konstruktor für die Klasse CalendarViewManager.
     *
     * @param terminListe Eine Liste von Terminen
     */
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
    /**
     * Wechselt zur nächsten Zeitspanne in der aktuellen Ansicht.
     *
     * @param terminListe Eine Liste von Terminen
     */
    public void nextPeriod(TerminListe terminListe) {
        currentView.nextPeriod();
        reloadAppointments(terminListe);
    }
    /**
     * Wechselt zur vorherigen Zeitspanne in der aktuellen Ansicht.
     *
     * @param terminListe Eine Liste von Terminen
     */
    public void previousPeriod(TerminListe terminListe) {
        currentView.previousPeriod();
        reloadAppointments(terminListe);
    }

    /**
     * Aktualisiert die Termine in der aktuellen Ansicht.
     *
     * @param terminListe Eine Liste von Terminen
     */
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

    /**
     * Gibt die aktuelle Ansicht zurück.
     *
     * @return Die aktuelle Ansicht
     */
    public CalendarView getCurrentView() {
        return currentView;
    }
}