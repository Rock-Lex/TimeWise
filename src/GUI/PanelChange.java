package GUI;

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
 * Letzte Änderung: 08.06.2023
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



    public PanelChange(CalendarViewManager viewManager,PanelMain mainPanel){
        this.viewManager = viewManager;
        this.mainPanel = mainPanel;


        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Erstelle die Buttons und füge sie dem Panel hinzu
        prevButton = new JButton("Zurück");
        nextButton = new JButton("Weiter");
        monatButton = new JButton("Monat");
        wocheButton = new JButton("Woche");
        tagButton = new JButton("Tag");
        aktuellerTagButton = new JButton("Aktueller Tag");
        springeNachButton = new JButton("Springe nach");

        // Hinzufügen von jumpPanel, welches aus 2 Buttons besteht
        // 1. Button: Zum aktuellen Tag springen
        // 2. Button: Zu eingegebenen Datum springen
        jumpPanel = new JPanel();
        jumpPanel.setLayout(new BoxLayout(jumpPanel, BoxLayout.Y_AXIS));
        jumpPanel.add(aktuellerTagButton);
        jumpPanel.add(springeNachButton);

        // Hinzufügen von monthChangePanel, welches aus 2 Buttons besteht und dem jumpPanel
        // besteht.
        // 1. Button: Blätter zum vorherigen Monat/Woche/Tag je nach aktueller Ansicht
        // 2. Button: Blättere zum nächsten Monat/Woche/Tag je nach aktueller Ansicht
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
                viewManager.nextMonth();
                mainPanel.updateCurrentMonthLabel();
                mainPanel.updateTabTitle();
            }
        });

        prevButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewManager.previousMonth();
                mainPanel.updateCurrentMonthLabel();
                mainPanel.updateTabTitle();

            }
        });

        monatButton.addActionListener(e -> viewManager.switchToView("month"));
        wocheButton.addActionListener(e -> wocheAction());
        tagButton.addActionListener(e -> tagAction());
        aktuellerTagButton.addActionListener(e -> aktuellerTagAction());
        springeNachButton.addActionListener(e -> springeNachAction());
    }

    private void prevAction() {
        // Implementieren Sie die Funktion für den "Zurück"-Button hier...
    }

    private void nextAction() {

    }

    private void monatAction() {
        // Implementieren Sie die Funktion für den "Monat"-Button hier...
    }

    private void wocheAction() {
        // Implementieren Sie die Funktion für den "Woche"-Button hier...
    }

    private void tagAction() {
        // Implementieren Sie die Funktion für den "Tag"-Button hier...
    }

    private void aktuellerTagAction() {
        // Implementieren Sie die Funktion für den "Aktueller Tag"-Button hier...
    }

    private void springeNachAction() {
        // Implementieren Sie die Funktion für den "Springe nach"-Button hier...
    }
}
