package GUI;

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

        mainFrame.getContentPane().add(this);
        mainFrame.setSize(800, 600);
        mainFrame.setVisible(true);

    }
    public static void main(String[] args){
        PanelMain panelMain = new PanelMain();
    }
}
