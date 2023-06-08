package GUI;

import javax.swing.*;
import java.awt.*;

public class PanelChange extends JPanel {
    private JPanel panelChange;
    private JButton prevButton;
    private JButton nextButton;
    private JButton monatButton;
    private JButton wocheButton;
    private JButton tagButton;
    private JButton aktuellerTagButton;
    private JButton springeNachButton;
    private JPanel buttonPanel;
    private JPanel monthChangePanel;
    private JPanel jumpPanel;

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
