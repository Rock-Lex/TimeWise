package GUI;

import javax.swing.*;
import javax.swing.text.DefaultHighlighter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Eine benutzerdefinierte Swing-Komponente zur Darstellung von Kalenderzellen.
 *
 * Autor: Philipp Voß
 * Version: 1.1
 * Erstellt am: 08.06.2023
 * Letzte Änderung: 15.06.2023
 */
public class CalendarCell extends JPanel {
    private JLabel label;
    private Color defaultBackground;
    private JTextArea textArea;

    /**
     * Erstellt eine neue Kalenderzelle mit dem angegebenen Text.
     *
     * @param text Der Text, der in der Zelle angezeigt werden soll.
     */
    public CalendarCell(String text) {
        setLayout(new BorderLayout());
        label = new JLabel(text);
        label.setEnabled(false);
        add(label, BorderLayout.NORTH);
        defaultBackground = label.getBackground();
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int pos = textArea.viewToModel(e.getPoint());
                try {
                    int rowStart = javax.swing.text.Utilities.getRowStart(textArea, pos);
                    int rowEnd = javax.swing.text.Utilities.getRowEnd(textArea, pos);
                    String selectedText = textArea.getText().substring(rowStart, rowEnd).trim();
                    System.out.println(selectedText);
                    textArea.getHighlighter().removeAllHighlights();
                    textArea.getHighlighter().addHighlight(rowStart, rowEnd, DefaultHighlighter.DefaultPainter);
                } catch (Exception ex) {
                    // Do nothing
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        add(textArea, BorderLayout.CENTER);
    }

    /**
     * Legt fest, ob die Zelle das heutige Datum darstellt.
     *
     * @param isToday true, wenn die Zelle das heutige Datum darstellt; andernfalls false.
     */
    public void setToday(boolean isToday) {
        if (isToday) {
            label.setBackground(Color.YELLOW);
        } else {
            label.setBackground(defaultBackground);
        }
    }

    /**
     * Fügt einen Termin zur Zelle hinzu.
     *
     * @param appointment Der Termin, der hinzugefügt werden soll.
     */
    public void addAppointment(String appointment) {
        textArea.append(appointment + "\n");
    }

    /**
     * Ein Beispielprogramm zum Testen der Kalenderzelle.
     *
     * @param args Die Befehlszeilenargumente (nicht verwendet).
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calendar Cell Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CalendarCell cell = new CalendarCell("1");
        cell.addAppointment("10:00 - 11:30 Terminname 1");
        cell.addAppointment("13:00 - 14:30 Terminname 2");

        frame.getContentPane().add(cell);
        frame.pack();
        frame.setVisible(true);
    }
}