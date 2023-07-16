package GUI;

import javax.swing.*;
import javax.swing.text.DefaultHighlighter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import Calendar.Termin;

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
    private Map<String, Termin> appointmentMap;


    /**
     * Erstellt eine neue Kalenderzelle mit dem angegebenen Tag.
     *
     * @param text Der Tag des Monats als String.
     */
    public CalendarCell(String text) {
        setLayout(new BorderLayout());
        label = new JLabel(text);
        label.setEnabled(false);
        add(label, BorderLayout.NORTH);
        defaultBackground = label.getBackground();
        textArea = new JTextArea();
        textArea.setEditable(false);
        appointmentMap = new HashMap<>();

        textArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int pos = textArea.viewToModel(e.getPoint());
                try {
                    int rowStart = javax.swing.text.Utilities.getRowStart(textArea, pos);
                    int rowEnd = javax.swing.text.Utilities.getRowEnd(textArea, pos);
                    String selectedText = textArea.getText().substring(rowStart, rowEnd).trim();
                    Termin appointment = appointmentMap.get(selectedText);
                    if (appointment != null) {
                        System.out.println(appointment.toString());
                    }
                    textArea.getHighlighter().removeAllHighlights();
                    textArea.getHighlighter().addHighlight(rowStart, rowEnd, DefaultHighlighter.DefaultPainter);
                } catch (Exception ex) {
                    // Do nothing
                }
            }
        });

        add(textArea, BorderLayout.CENTER);
    }

    /**
     * Setzt die Hintergrundfarbe des Labels auf gelb,
     * wenn der Tag der aktuelle Tag ist.
     *
     * @param isToday true, wenn der Tag der aktuelle Tag ist,
     *                false sonst.
     */
    public void setToday(boolean isToday) {
        if (isToday) {
            label.setBackground(Color.YELLOW);
        } else {
            label.setBackground(defaultBackground);
        }
    }

    /**
     * Fügt einen Termin zum Textfeld hinzu.
     *
     * @param appointment Der Termin als String.
     */
    public void addAppointment(String appointmentText, Termin appointment) {
        textArea.append(appointmentText + "\n");
        appointmentMap.put(appointmentText, appointment);
    }

    /**
     * Beispielanwendung zum Testen der Klasse.
     *
     * @param args Kommandozeilenargumente (werden ignoriert).
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calendar Cell Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CalendarCell cell = new CalendarCell("1");
        Termin termin1 = new Termin("Terminname 1", "Typ 1", false, "2023-06-01", "2023-06-01", "10:00", "11:30");
        Termin termin2 = new Termin("Terminname 2", "Typ 2", false, "2023-06-01", "2023-06-01", "13:00", "14:30");
        cell.addAppointment("10:00 - 11:30 Terminname 1", termin1);
        cell.addAppointment("13:00 - 14:30 Terminname 2", termin2);

        frame.getContentPane().add(cell);
        frame.pack();
        frame.setVisible(true);
    }
}