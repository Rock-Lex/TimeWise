package GUI;


import Calendar.Termin;
import Calendar.TerminListe;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeParseException;
import java.util.Properties;
import java.util.Date;
import java.time.ZoneId;

import GUI.Utilities.DateLabelFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 * Eine GUI zur Erstellung von Terminen.
 * Diese Klasse ermöglicht das Hinzufügen, Bearbeiten und Speichern von Terminen
 * durch die Benutzeroberfläche.
 *
 * Autor: Simon Degmair
 * Version: 1.3
 * Erstellt am: 18.07.2023
 * Letzte Änderung: 22.07.2023
 */
public class Appointment {
    private TerminListe appointments;
    private JFrame frame;
    private JPanel mainPanel;
    private JButton editButton;
    private JButton cancelButton;
    private JButton saveButton;
    private JCheckBox checkBoxMehrtagig;
    private JTextField textFieldTitel;
    private JTextField textFieldStartdatum;
    private JTextField textFieldStartzeit;
    private JTextField textFieldEnddatum;
    private JTextField textFieldEndzeit;
    private JTextField textFieldTyp;
    private JTextField textFieldTerminbeschreibung;
    private String[] columnNames = {"Titel", "Mehrtägig", "Startdatum", "Startzeit", "Enddatum", "Endzeit", "Typ", "Terminbeschreibung"};
    private boolean running = true;
    private boolean isEditing = false;
    private Termin currentTermin;

    private JDatePickerImpl datePickerStart;
    private JDatePickerImpl datePickerEnd;

    /**
     * Konstruktor für die Erstellung einer neuen Termin-GUI ohne initialen Termin.
     * Erzeugt ein neues Appointment-Objekt ohne einen initialen Termin.
     * Ruft den anderen Konstruktor auf und setzt die Bearbeitung auf den deaktivierten Zustand.
     */
    public Appointment(TerminListe terminListe) {
        this(null, terminListe);  // Ruft den anderen Konstruktor mit null und terminListe als Argument auf
    }

    /**
     * Konstruktor für die Erstellung einer Termin-GUI mit einem initialen Termin.
     * Erzeugt ein neues Appointment-Objekt mit einem initialen Termin, falls vorhanden.
     * Der Konstruktor ruft den anderen Konstruktor auf und setzt die Bearbeitung auf den deaktivierten Zustand.
     *
     * @param termin Der Termin, der angezeigt und bearbeitet werden soll.
     */
    public Appointment(Termin termin, TerminListe terminListe) {
        this.appointments = terminListe;
        this.frame = new JFrame("Termin App");
        this.mainPanel = new JPanel(new BorderLayout());
        this.editButton = new JButton("Bearbeiten");
        this.cancelButton = new JButton("Abbrechen");
        this.saveButton = new JButton("Speichern");

        UtilDateModel modelStart = new UtilDateModel();
        UtilDateModel modelEnd = new UtilDateModel();

        Properties p = new Properties();
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

        // Erstellen der Textfelder und der Checkbox
        this.textFieldTitel = new JTextField(15);
        this.checkBoxMehrtagig = new JCheckBox();
        JDatePanelImpl datePanelStart = new JDatePanelImpl(modelStart, p);
        this.datePickerStart = new JDatePickerImpl(datePanelStart, new DateLabelFormatter());
        this.textFieldStartzeit = new JTextField(15);
        JDatePanelImpl datePanelEnd = new JDatePanelImpl(modelEnd, p);
        this.datePickerEnd = new JDatePickerImpl(datePanelEnd, new DateLabelFormatter());
        this.textFieldEndzeit = new JTextField(15);
        this.textFieldTyp = new JTextField(15);
        this.textFieldTerminbeschreibung = new JTextField(15);

        // Wenn ein Termin übergeben wurde, die Textfelder und die Checkbox entsprechend vorbelegen
        if (termin != null) {
            this.currentTermin = termin;
            textFieldTitel.setText(termin.getTitle());
            checkBoxMehrtagig.setSelected(termin.isMultiDay());
            int month = termin.getStart().getMonthValue() - 1;
            datePickerStart.getModel().setDate(termin.getStart().getYear(), month, termin.getStart().getDayOfMonth());
            datePickerStart.getModel().setSelected(true);
            textFieldStartzeit.setText(termin.getStartTime());
            datePickerEnd.getModel().setDate(termin.getEnd().getYear(), termin.getEnd().getMonthValue(), termin.getEnd().getDayOfMonth());
            datePickerEnd.getModel().setSelected(true);
            textFieldEndzeit.setText(termin.getEndTime());
            textFieldTyp.setText(termin.getType());
            textFieldTerminbeschreibung.setText(termin.getDescription());
        } else {
            this.currentTermin = null;
        }

        setupUI();
        toggleEditing(false);
    }
    /**
     * Zeigt die GUI zur Terminerstellung an.
     * Stellt das Fenster der GUI sichtbar dar.
     *
     * @return
     */
    public JFrame showUI() {
        frame.setVisible(true);  // Stellt nur die Sichtbarkeit des Fensters ein
        return frame;
    }
    /**
     * Initialisiert die Benutzeroberfläche.
     * Setzt die Größe des Fensters, fügt ActionListener für die Buttons hinzu und erstellt die Panels.
     */
    private void setupUI() {
        frame.setSize(800, 600);

        // Panel für den "Bearbeiten"-Knopf
        JPanel editPanel = new JPanel();
        editPanel.add(editButton);

        // Panel für die Textfelder und Checkbox
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 15); // Ändere die rechte Padding der Labels

        panel.add(new JLabel("Titel:"), gbc);
        gbc.gridx = 1;
        panel.add(textFieldTitel, gbc);
        gbc.gridx = 0;
        panel.add(new JLabel("Mehrtägig:"), gbc);
        gbc.gridx = 1;
        panel.add(checkBoxMehrtagig, gbc);

        gbc.gridx = 0;
        panel.add(new JLabel("Startdatum:"), gbc);
        gbc.gridx = 1;
        panel.add(datePickerStart, gbc);
        gbc.gridx = 0;
        panel.add(new JLabel("Startzeit:"), gbc);
        gbc.gridx = 1;
        panel.add(textFieldStartzeit, gbc);
        gbc.gridx = 0;
        panel.add(new JLabel("Enddatum:"), gbc);
        gbc.gridx = 1;
        panel.add(datePickerEnd, gbc);
        gbc.gridx = 0;
        panel.add(new JLabel("Endzeit:"), gbc);
        gbc.gridx = 1;
        panel.add(textFieldEndzeit, gbc);
        gbc.gridx = 0;
        panel.add(new JLabel("Typ:"), gbc);
        gbc.gridx = 1;
        panel.add(textFieldTyp, gbc);
        gbc.gridx = 0;
        panel.add(new JLabel("Terminbeschreibung:"), gbc);
        gbc.gridx = 1;
        panel.add(textFieldTerminbeschreibung, gbc);
        gbc.gridx = 0;

        JScrollPane scrollPane = new JScrollPane(panel);

        // ActionListener für den "Bearbeiten"-Knopf
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toggleEditing(!isEditing); // Toggle den Bearbeitungszustand
                isEditing = !isEditing; // Update den Bearbeitungszustand
            }
        });

        // ActionListener für den "Abbrechen"-Knopf
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Schließt das Fenster
            }
        });

        // ActionListener für den "Speichern"-Knopf
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentTermin == null) {
                    saveAppointment(); // Neuer Termin
                } else {
                    updateAppointment(); // Aktualisierung eines bestehenden Termins
                }
                frame.dispose(); // Schließt das Fenster
            }
        });

        // Panel für die Knöpfe "Abbrechen" und "Speichern"
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);

        mainPanel.add(editPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(mainPanel);
        frame.setVisible(true);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.2);
        int height = (int) (screenSize.height * 0.5);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null); // Fenster in der Bildschirmmitte öffnen
        frame.setVisible(true);
    }

    /**
     * Aktiviert oder deaktiviert die Bearbeitungsmodus für die Textfelder und Checkboxen.
     *
     * @param isEditing True, wenn der Bearbeitungsmodus aktiviert werden soll, andernfalls False.
     */
    public void toggleEditing(boolean isEditing) {
        textFieldTitel.setEnabled(isEditing);
        checkBoxMehrtagig.setEnabled(isEditing);
        datePickerStart.setEnabled(isEditing);
        textFieldStartzeit.setEnabled(isEditing);
        datePickerEnd.setEnabled(isEditing);
        textFieldEndzeit.setEnabled(isEditing);
        textFieldTyp.setEnabled(isEditing);
        textFieldTerminbeschreibung.setEnabled(isEditing);
    }
    private void saveAppointment() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime startDateTime;
        LocalDateTime endDateTime;

        // Umwandlung von java.util.Date in java.time.LocalDate
        LocalDate startDate = ((java.util.Date) datePickerStart.getModel().getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = ((java.util.Date) datePickerEnd.getModel().getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        try {
            startDateTime = LocalDateTime.of(
                    startDate,
                    LocalTime.parse(textFieldStartzeit.getText(), timeFormatter));
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Die Startzeit ist ungültig.", "Eingabefehler", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (endDate == null) {
            endDateTime = startDateTime;
        } else {
            try {
                endDateTime = LocalDateTime.of(
                        endDate,
                        LocalTime.parse(textFieldEndzeit.getText(), timeFormatter));
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Die Endzeit ist ungültig.", "Eingabefehler", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (endDateTime.isBefore(startDateTime)) {
                JOptionPane.showMessageDialog(null, "Das Enddatum kann nicht vor dem Startdatum liegen.", "Eingabefehler", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Erstellen eines neuen Termins
        this.currentTermin = new Termin(
                textFieldTitel.getText(),
                textFieldTyp.getText(),
                checkBoxMehrtagig.isSelected(),
                startDateTime,
                endDateTime
        );
        currentTermin.setDescription(textFieldTerminbeschreibung.getText());
        appointments.addTermin(currentTermin);
    }

    /**
     * Aktualisiert einen vorhandenen Termin basierend auf den Daten in den Textfeldern und der Checkbox.
     */
    private void updateAppointment() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime startDateTime;
        LocalDateTime endDateTime;

        Date start = (Date) datePickerStart.getModel().getValue();
        Date end = (Date) datePickerEnd.getModel().getValue();
        LocalDate startDate = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = end != null ? end.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;

        try {
            startDateTime = LocalDateTime.of(
                    startDate,
                    LocalTime.parse(textFieldStartzeit.getText(), timeFormatter));
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Die Startzeit ist ungültig.", "Eingabefehler", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (endDate == null) {
            endDateTime = startDateTime;
        } else {
            try {
                endDateTime = LocalDateTime.of(
                        endDate,
                        LocalTime.parse(textFieldEndzeit.getText(), timeFormatter));
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Die Endzeit ist ungültig.", "Eingabefehler", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (endDateTime.isBefore(startDateTime)) {
                JOptionPane.showMessageDialog(null, "Das Enddatum kann nicht vor dem Startdatum liegen.", "Eingabefehler", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Aktualisieren eines bestehenden Termins
        this.currentTermin.setTitle(textFieldTitel.getText());
        this.currentTermin.setType(textFieldTyp.getText());
        this.currentTermin.setMultiDay(checkBoxMehrtagig.isSelected());
        this.currentTermin.setStart(startDateTime);
        this.currentTermin.setEnd(endDateTime);
        this.currentTermin.setDescription(textFieldTerminbeschreibung.getText());
    }
}
