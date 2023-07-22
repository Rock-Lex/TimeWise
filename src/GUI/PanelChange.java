package GUI;

import Calendar.TerminListe;
import GUI.Exceptions.AppointmentMismatchMonthException;
import GUI.Exceptions.AppointmentOutOfMonthRangeException;
import GUI.Views.CalendarView;
import GUI.Views.CalendarViewManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Diese Klasse repräsentiert das Panel für die Änderung von Ansichten und
 * weiter- bzw. zurückblättern im Kalender.
 *
 * Autor: Philipp Voß
 * Version: 1.2
 * Erstellt am: 03.06.2023
 * Letzte Änderung: 19.07.2023
 */
public class PanelChange extends JPanel {
    private JPanel panelChange;
    private JPanel bottomPanel;
    private JButton btn_week;
    private JButton btn_month;
    private JButton btn_day;
    private JPanel monthChangePanel;
    private JPanel jumpPanel;
    private JButton btn_jumpTo;
    private JButton btn_today;
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
        btn_month = new JButton("Monat");
        btn_week = new JButton("Woche");
        btn_day = new JButton("Tag");
        btn_today = new JButton("Aktueller Tag");
        btn_jumpTo = new JButton("Springe zu");

        jumpPanel = new JPanel();
        jumpPanel.setLayout(new BoxLayout(jumpPanel, BoxLayout.Y_AXIS));
        jumpPanel.add(btn_today);
        jumpPanel.add(btn_jumpTo);

        monthChangePanel = new JPanel();
        monthChangePanel.setLayout(new BoxLayout(monthChangePanel, BoxLayout.X_AXIS));
        monthChangePanel.add(prevButton);
        monthChangePanel.add(jumpPanel);
        monthChangePanel.add(nextButton);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        bottomPanel.add(btn_day);
        bottomPanel.add(btn_week);
        bottomPanel.add(btn_month);

        add(monthChangePanel);
        add(bottomPanel);
        setSize(600, 100);
        setVisible(true);

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    viewManager.nextPeriod(terminListe);
                } catch (AppointmentOutOfMonthRangeException ex) {
                    throw new RuntimeException(ex);
                } catch (AppointmentMismatchMonthException ex) {
                    throw new RuntimeException(ex);
                }
                mainPanel.updateTabTitle();
            }
        });

        prevButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    viewManager.previousPeriod(terminListe);
                } catch (AppointmentOutOfMonthRangeException ex) {
                    throw new RuntimeException(ex);
                } catch (AppointmentMismatchMonthException ex) {
                    throw new RuntimeException(ex);
                }
                mainPanel.updateTabTitle();
            }
        });

        btn_today.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Springe zum aktuellen Tag
                try {
                    viewManager.jumpToCurrentDay();
                } catch (AppointmentOutOfMonthRangeException ex) {
                    throw new RuntimeException(ex);
                } catch (AppointmentMismatchMonthException ex) {
                    throw new RuntimeException(ex);
                }
                mainPanel.updateTabTitle();
            }
        });

        btn_jumpTo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(null, "Geben Sie ein Datum ein (YYYY-MM-DD):");

                if (input != null && !input.isEmpty()) {
                    try {
                        LocalDate date = LocalDate.parse(input);
                        viewManager.jumpToDate(date);
                        mainPanel.updateTabTitle();
                    } catch (DateTimeParseException dtpe) {
                        JOptionPane.showMessageDialog(null, "Ungültiges Datumsformat. Bitte geben Sie das Datum im Format YYYY-MM-DD ein.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    } catch (AppointmentOutOfMonthRangeException ex) {
                        throw new RuntimeException(ex);
                    } catch (AppointmentMismatchMonthException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

    }
}