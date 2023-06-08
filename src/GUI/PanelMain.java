package GUI;

import javax.swing.*;

import GUI.monthView.*;

import java.awt.*;
/**
 * Diese Klasse repräsentiert das Hauptpanel der Anwendung.
 *
 * Autor: Philipp Voß
 * Version: 1.1
 * Erstellt am: 02.06.2023
 * Letzte Änderung: 08.06.2023
 */
public class PanelMain extends JPanel{
    private JPanel panelMain;
    private JTabbedPane tabbedPane;
    private JPanel panelChange;
    private JFrame mainFrame;

    public PanelMain(){
        mainFrame = new JFrame("Main Panel");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setOpaque(false);
        setLayout(new BorderLayout());
        MonthView monthView = new MonthView();
        panelChange = new PanelChange();

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Monatsansicht", monthView);

        add(panelChange, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);

        mainFrame.add(this, BorderLayout.CENTER);
        mainFrame.setSize(800, 600);
        mainFrame.setVisible(true);

    }
    public static void main(String[] args){
        PanelMain panelMain = new PanelMain();
    }
}
