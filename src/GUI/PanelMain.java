package GUI;

import Calendar.Termin;
import GUI.PanelChange;
import GUI.Views.CalendarViewManager;
import GUI.monthView.MonthView;

import javax.swing.*;
import java.awt.*;
/**
 * Diese Klasse repräsentiert das Hauptpanel der Anwendung.
 *
 * Autor: Philipp Voß
 * Version: 1.3
 * Erstellt am: 02.06.2023
 * Letzte Änderung: 16.07.2023
 */
public class PanelMain extends JPanel {
    private JTabbedPane tabbedPane;
    private PanelChange panelChange;
    private JFrame mainFrame;
    private CalendarViewManager viewManager;
    private MonthView monthView;

    public PanelMain(){
        viewManager = new CalendarViewManager();
        mainFrame = new JFrame("Main Panel");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setOpaque(false);
        setLayout(new BorderLayout());
        panelChange = new PanelChange(viewManager);
        monthView = new MonthView(2023, 6);

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
        GUI.PanelMain panelMain = new GUI.PanelMain();
        Termin termin1 = new Termin("Terminname 1", "Typ 1", false, "2023-06-03", "2023-06-03", "10:00", "11:30");
        Termin termin2 = new Termin("Terminname 2", "Typ 2", false, "2023-06-01", "2023-06-01", "13:00", "14:30");
        panelMain.getMonthView().addAppointment(termin1);
        panelMain.getMonthView().addAppointment(termin2);
    }
}