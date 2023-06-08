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
        setLayout(new BorderLayout());

        // Erstelle die Buttons und füge sie dem Panel hinzu
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

        add(monthChangePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setSize(600, 200);
        setVisible(true);
    }
}
