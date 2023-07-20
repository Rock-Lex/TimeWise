package GUI;

import Calendar.TerminListe;
import GUI.Views.CalendarView;
import GUI.Views.CalendarViewManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Diese Klasse repräsentiert das Panel für die Änderung von Ansichten und
 * weiter- bzw. zurückblättern im Kalender.
 *
 * Autor: Philipp Voß
 * Version: 1.1
 * Erstellt am: 03.06.2023
 * Letzte Änderung: 19.07.2023
 */
public class PanelChange extends JPanel {
    private JPanel panelChange;
    private JPanel buttonPanel;
    private JButton wocheButton;
    private JButton monatButton;
    private JButton tagButton;
    private JPanel monthChangePanel;
    private JPanel jumpPanel;
    private JButton springeNachButton;
    private JButton aktuellerTagButton;
    private JButton nextButton;
    private JButton prevButton;
    private CalendarViewManager viewManager;
    private CalendarView calendarView;
    private PanelMain mainPanel;
    private TerminListe terminListe;

    /**
     * Erstellt ein neues PanelChange-Objekt mit dem angegebenen CalendarViewManager,
     * PanelMain und TerminListe.
     *
     * @param viewManager Der CalendarViewManager für die Ansichtsverwaltung
     * @param mainPanel Das Hauptpanel, zu dem dieses PanelChange gehört
     * @param terminListe Die Terminliste für den Kalender
     */
    public PanelChange(CalendarViewManager viewManager,PanelMain mainPanel, TerminListe terminListe){
        this.viewManager = viewManager;
        this.mainPanel = mainPanel;
        this.terminListe = terminListe;
        System.out.println("Anzahl der Termine in terminListe in PanelChange: " + this.terminListe.getTermine().size());

        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        prevButton = new JButton("Zurück");
        nextButton = new JButton("Weiter");
        monatButton = new JButton("Monat");
        wocheButton = new JButton("Woche");
        tagButton = new JButton("Tag");
        aktuellerTagButton = new JButton("Aktueller Tag");
        springeNachButton = new JButton("Springe nach");

        jumpPanel = new JPanel();
        jumpPanel.setLayout(new BoxLayout(jumpPanel, BoxLayout.Y_AXIS));
        jumpPanel.add(aktuellerTagButton);
        jumpPanel.add(springeNachButton);

        monthChangePanel = new JPanel();
        monthChangePanel.setLayout(new BoxLayout(monthChangePanel, BoxLayout.X_AXIS));
        monthChangePanel.add(prevButton);
        monthChangePanel.add(jumpPanel);
        monthChangePanel.add(nextButton);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        buttonPanel.add(tagButton);
        buttonPanel.add(wocheButton);
        buttonPanel.add(monatButton);

        add(monthChangePanel);
        add(buttonPanel);
        setSize(600, 100);
        setVisible(true);

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewManager.nextPeriod(terminListe);
                mainPanel.updateTabTitle();
            }
        });

        prevButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewManager.previousPeriod(terminListe);
                mainPanel.updateTabTitle();
            }
        });
    }
}