package GUI;

import Calendar.Termin;
import GUI.PanelChange;
import GUI.Views.CalendarView;
import GUI.Views.CalendarViewManager;
import GUI.monthView.MonthView;

import javax.swing.*;
import java.awt.*;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Random;

/**
 * Diese Klasse repräsentiert das Hauptpanel der Anwendung.
 *
 * Autor: Philipp Voß
 * Version: 1.4
 * Erstellt am: 02.06.2023
 * Letzte Änderung: 16.07.2023
 *
 */
public class PanelMain extends JPanel {
    private JTabbedPane tabbedPane;
    private PanelChange panelChange;
    private JFrame mainFrame;
    private CalendarViewManager viewManager;
    private CalendarView monthView;
    JLabel currentMonthLabel;


    public PanelMain(){
        YearMonth currentYearMonth = YearMonth.now();
        viewManager = new CalendarViewManager();

        mainFrame = new JFrame("Main Panel");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setOpaque(false);
        setLayout(new BorderLayout());

        // Hole die monthView von viewManager
        monthView = viewManager.getCurrentView();
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab(String.valueOf(currentYearMonth), monthView);
        panelChange = new PanelChange(viewManager, this);
        add(panelChange, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);

        mainFrame.getContentPane().add(this);
        mainFrame.setSize(800, 600);
        mainFrame.setVisible(true);
        currentMonthLabel = new JLabel(viewManager.getCurrentView().getYearMonth().toString());
        add(currentMonthLabel, BorderLayout.SOUTH);
    }

    public MonthView getMonthView() {
        return (MonthView) monthView;
    }

    public static void main(String[] args){
        PanelMain panelMain = new GUI.PanelMain();

        YearMonth currentYearMonth = YearMonth.now();

        Random random = new Random();

        for (int i = 1; i <= 50; i++) {
            // Zufälliger Tag
            int day = random.nextInt(currentYearMonth.lengthOfMonth()) + 1;
            // Zufällige Startzeit
            int startHour = random.nextInt(13) + 6;
            int startMinute = (random.nextInt(4) * 15)%60;
            // Jeder Termin dauert 2 genau Stunden
            int endHour = (startHour + 2) % 24;
            int endMinute = startMinute;

            // Erstellen von Termin mit zufälligen Tag und Startzeit
            Termin termin = new Termin(
                    "Terminname " + i,
                    "Typ " + i,
                    false,
                    String.format("%d-%02d-%02d", currentYearMonth.getYear(), currentYearMonth.getMonthValue(), day),
                    String.format("%d-%02d-%02d", currentYearMonth.getYear(), currentYearMonth.getMonthValue(), day),
                    String.format("%02d:%02d", startHour, startMinute),
                    String.format("%02d:%02d", endHour, endMinute)
            );

            // Hinzufügen des Termins
            panelMain.getMonthView().addAppointment(termin);
        }
    }

    public void updateMonthView(MonthView newView) {
        tabbedPane.remove(monthView);
        monthView = newView;
        String monthName = String.valueOf(monthView.getYearMonth().getMonth());
        int year = monthView.getYearMonth().getYear();
        tabbedPane.addTab(monthName + " " + year, monthView);
    }
    public void updateCurrentMonthLabel() {
        currentMonthLabel.setText(viewManager.getCurrentView().getYearMonth().toString());
    }

    public void updateTabTitle() {
        String currentMonthAndYear = String.valueOf(monthView.getYearMonth());
        tabbedPane.setTitleAt(0, currentMonthAndYear);
    }
}