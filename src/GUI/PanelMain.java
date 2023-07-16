package GUI;

import Calendar.Termin;
import GUI.PanelChange;
import GUI.Views.CalendarViewManager;
import GUI.monthView.MonthView;

import javax.swing.*;
import java.awt.*;
import java.time.YearMonth;
import java.util.Random;

/**
 * Diese Klasse repräsentiert das Hauptpanel der Anwendung.
 *
 * Autor: Philipp Voß
 * Version: 1.3
 * Erstellt am: 02.06.2023
 * Letzte Änderung: 16.07.2023
 *
 */
public class PanelMain extends JPanel {
    private JTabbedPane tabbedPane;
    private PanelChange panelChange;
    private JFrame mainFrame;
    private CalendarViewManager viewManager;
    private MonthView monthView;

    public PanelMain(){
        YearMonth currentYearMonth = YearMonth.now();
        viewManager = new CalendarViewManager();
        mainFrame = new JFrame("Main Panel");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setOpaque(false);
        setLayout(new BorderLayout());
        panelChange = new PanelChange(viewManager);
        monthView = new MonthView(currentYearMonth.getYear(), currentYearMonth.getMonthValue());

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Monatsansicht", monthView);

        add(panelChange, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);

        mainFrame.getContentPane().add(this);
        mainFrame.setSize(800, 600);
        mainFrame.setVisible(true);
    }
    public MonthView getMonthView() {
        return monthView;
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
}