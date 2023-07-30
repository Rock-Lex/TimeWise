package GUI;

import javax.swing.*;
import javax.swing.text.DefaultHighlighter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.TreeMap;

import Calendar.Termin;
import Calendar.TerminListe;
import GUI.Appointment.AppointmentForm;
import GUI.Views.CalendarView;

/**
 * Eine benutzerdefinierte Swing-Komponente zur Darstellung von Kalenderzellen.
 *
 * Autor: Philipp Voß
 * Version: 1.3
 * Erstellt am: 08.06.2023
 * Letzte Änderung: 16.07.2023
 */
public class CalendarCell extends JPanel {
    private JLabel label;
    private Color defaultBackground;
    private JTextArea textArea;
    private TreeMap<String, Termin> appointmentMap;
    private TerminListe terminListe;

    /**
     * Erstellt eine neue Kalenderzelle mit dem angegebenen Tag.
     *
     * @param text Der Tag des Monats als String.
     */

    public CalendarCell(String text, TerminListe terminListe, CalendarView monthView) {
        setLayout(new BorderLayout());

        label = new JLabel(text);

        label.setEnabled(false);
        add(label, BorderLayout.NORTH);
        defaultBackground = label.getBackground();
        textArea = new JTextArea();
        textArea.setEditable(false);
        appointmentMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // Extract the start times and end times from the strings
                LocalTime startTime1 = LocalTime.parse(o1.split(" - ")[0]);
                LocalTime endTime1 = LocalTime.parse(o1.split(" - ")[1].split(" ")[0]);
                String name1 = o1.split(" ")[2];

                LocalTime startTime2 = LocalTime.parse(o2.split(" - ")[0]);
                LocalTime endTime2 = LocalTime.parse(o2.split(" - ")[1].split(" ")[0]);
                String name2 = o2.split(" ")[2];

                // Compare the start times, then end times, then names
                int compareStartTimes = startTime1.compareTo(startTime2);
                if (compareStartTimes != 0) return compareStartTimes;

                int compareEndTimes = endTime1.compareTo(endTime2);
                if (compareEndTimes != 0) return compareEndTimes;

                return name1.compareTo(name2);
            }

        });

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
        textArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Doppelklick erkannt
                    int pos = textArea.viewToModel(e.getPoint());
                    try {
                        int rowStart = javax.swing.text.Utilities.getRowStart(textArea, pos);
                        int rowEnd = javax.swing.text.Utilities.getRowEnd(textArea, pos);
                        String selectedText = textArea.getText().substring(rowStart, rowEnd).trim();
                        Termin appointment = appointmentMap.get(selectedText);
                        if (appointment != null) {
                            // Rufe die Appointment Klasse auf und übergebe den ausgewählten Termin
                            new AppointmentForm(appointment, terminListe, monthView).showUI();
                        }
                        textArea.getHighlighter().removeAllHighlights();
                        textArea.getHighlighter().addHighlight(rowStart, rowEnd, DefaultHighlighter.DefaultPainter);
                    } catch (Exception ex) {
                        // Do nothing
                    }
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
        // Füge Termin zur TreeMap hinzu
        appointmentMap.put(appointmentText, appointment);

        // TextAre löschen und mit sortierten Terminen füllen
        textArea.setText("");
        for (String sortedAppointmentText : appointmentMap.keySet()) {
            textArea.append(sortedAppointmentText + "\n");
        }
    }

    /**
     * Löscht alle Termine und leert das Textfeld.
     */
    public void clearAppointments() {
        appointmentMap.clear();
        textArea.setText("");
    }
}