package GUI;

import javax.swing.*;
import java.awt.*;
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

    public PanelChange(){
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
    }
}
