package GUI.Views;

import GUI.monthView.MonthView;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

public class CalendarViewManager {
    private Map<String, CalendarView> views;
    private CalendarView currentView;

    public CalendarViewManager() {
        views = new HashMap<>();

        // Initialisiere die verschiedenen Ansichten
        int currentYear = YearMonth.now().getYear();
        int currentMonth = YearMonth.now().getMonthValue();
        views.put("month", new MonthView(currentYear, currentMonth));
        // Fügen Sie hier weitere Ansichten hinzu (z.B. WeekView, DayView)...

        // Standardmäßig die Monatsansicht setzen
        currentView = views.get("month");
    }

    public void switchToView(String viewName) {
        if (!views.containsKey(viewName)) {
            throw new IllegalArgumentException("Unbekannte Ansicht: " + viewName);
        }

        currentView = views.get(viewName);
        currentView.updateView();  // Aktualisiere die Ansicht
    }

    public void nextMonth() {
        YearMonth nextMonth = currentView.getYearMonth().plusMonths(1);
        currentView.setYearMonth(nextMonth);
        currentView.updateView();
    }

    public void previousMonth() {
        YearMonth previousMonth = currentView.getYearMonth().minusMonths(1);
        currentView.setYearMonth(previousMonth);
        currentView.updateView();
    }

    public CalendarView getCurrentView() {
        return currentView;
    }
}

