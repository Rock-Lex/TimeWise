package GUI.monthView;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;


public class PanelMonthView extends JPanel {
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JButton button13;
    private JButton button14;
    private JButton button15;
    private JButton button16;
    private JButton button17;
    private JButton button18;
    private JButton button19;
    private JButton button20;
    private JButton button21;
    private JButton button22;
    private JButton button23;
    private JButton button24;
    private JButton button25;
    private JButton button26;
    private JButton button27;
    private JButton button28;
    private JButton button29;
    private JButton button30;
    private JButton button31;
    private JButton button32;
    private JButton button33;
    private JButton button34;
    private JButton button35;
    private JButton button36;
    private JButton button37;
    private JButton button38;
    private JButton button39;
    private JButton button40;
    private JButton button41;
    private JButton button42;

    private JButton[] dayButtons;

    public PanelMonthView() {
        setLayout(new GridLayout(6, 7)); // Festlegen des Layouts auf 6 Zeilen und 7 Spalten (für die 42 Tage im Kalendermonat)

        dayButtons = new JButton[42]; // Array für die Buttons der Tage erstellen

        // Schleife zum Erstellen der Buttons für die Tage
        for (int i = 0; i < 42; i++) {
            dayButtons[i] = new JButton();
            dayButtons[i].setBorderPainted(false); // Entfernen des Button-Rahmens
            dayButtons[i].setContentAreaFilled(false); // Entfernen der Hintergrundfüllung
            add(dayButtons[i]); // Button zum Panel hinzufügen
        }
    }
}
