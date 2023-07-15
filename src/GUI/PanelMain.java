package GUI;

import Calendar.Termin;
import GUI.PanelChange;
import GUI.monthView.MonthView;

import javax.swing.*;
import java.awt.*;
import javax.swing.JPanel;
/**
 * Diese Klasse repräsentiert das Hauptpanel der Anwendung.
 *
 * Autor: Philipp Voß
 * Version: 1.2
 * Erstellt am: 02.06.2023
 * Letzte Änderung: 18.06.2023
 */
public class PanelMain extends JPanel{
    private JPanel panelMain;
    private JTabbedPane tabbedPane;
    private PanelChange panelChange;
    private JFrame mainFrame;

    public PanelMain(){
        mainFrame = new JFrame("Main Panel");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setOpaque(false);
        setLayout(new BorderLayout());
        MonthView monthView = new MonthView(2023, 6);
        panelChange = new PanelChange();

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Monatsansicht", monthView);

        add(panelChange, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);

        Termin termin1 = new Termin("Terminname 1", "Typ 1", false, "2023-06-01", "2023-06-01", "10:00", "11:30");
        Termin termin2 = new Termin("Terminname 2", "Typ 2", false, "2023-06-01", "2023-06-01", "13:00", "14:30");
        monthView.addAppointment(1, termin1);
        monthView.addAppointment(1, termin2);


        mainFrame.getContentPane().add(this);
        mainFrame.setSize(800, 600);
        mainFrame.setVisible(true);

    }
    public static void main(String[] args){
        PanelMain panelMain = new PanelMain();
    }
}
