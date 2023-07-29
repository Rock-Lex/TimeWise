package GUI;


import Calendar.Termin;
import Calendar.TerminListe;

import javax.swing.*;
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

import GUI.Utilities.DateLabelFormatter;
import org.jdatepicker.DateModel;
import org.jdatepicker.JDatePicker;
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
public class AppointmentForm {
    private TerminListe appointments;
    private JFrame frame;
    private JPanel mainPanel;
    private JButton editButton;
    private JButton cancelButton;
    private JButton saveButton;
    private JCheckBox checkBoxMehrtagig;
    private JTextField textFieldTitel;
    private JTextField textFieldStartzeit;
    private JTextField textFieldEndzeit;
    private JTextField textFieldTyp;
    private JTextField textFieldTerminbeschreibung;
    private JButton deleteButton;
    private String[] columnNames = {"Titel", "Mehrtägig", "Startdatum", "Startzeit", "Enddatum", "Endzeit", "Typ", "Terminbeschreibung"};
    private boolean running = true;
    private boolean isEditing = false;
    private Termin currentTermin;
    private JCheckBox repeatAppointmentCheckBox;
    private JComboBox repeatFrequencyComboBox;
    private JDatePicker repeatEndDatePicker;

    private JDatePickerImpl datePickerStart;
    private JDatePickerImpl datePickerEnd;

    /**
     * Konstruktor für die Erstellung einer neuen Termin-GUI ohne initialen Termin.
     * Erzeugt ein neues Appointment-Objekt ohne einen initialen Termin.
     * Ruft den anderen Konstruktor auf und setzt die Bearbeitung auf den deaktivierten Zustand.
     */
    public AppointmentForm(TerminListe terminListe) {
        this(null, terminListe);  // Ruft den anderen Konstruktor mit null und terminListe als Argument auf
    }

    /**
     * Konstruktor für die Erstellung einer Termin-GUI mit einem initialen Termin.
     * Erzeugt ein neues Appointment-Objekt mit einem initialen Termin, falls vorhanden.
     * Der Konstruktor ruft den anderen Konstruktor auf und setzt die Bearbeitung auf den deaktivierten Zustand.
     *
     * @param termin Der Termin, der angezeigt und bearbeitet werden soll.
     */
    public AppointmentForm(Termin termin, TerminListe terminListe) {
        this.appointments = terminListe;
        this.frame = new JFrame("Termin App");
        this.mainPanel = new JPanel(new BorderLayout());
        this.editButton = new JButton("Bearbeiten");
        this.cancelButton = new JButton("Abbrechen");
        this.saveButton = new JButton("Speichern");
        deleteButton = new JButton("Termin löschen");

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

        // Create combo box with repeat options
        String[] repeatOptions = {"Daily", "Weekly", "Monthly"};
        repeatFrequencyComboBox = new JComboBox(repeatOptions);

        // Create date picker for repeat end date
        repeatEndDatePicker = new JDatePicker() {
            @Override
            public DateModel<?> getModel() {
                return null;
            }

            @Override
            public void addActionListener(ActionListener actionListener) {

            }

            @Override
            public void removeActionListener(ActionListener actionListener) {

            }

            @Override
            public void setShowYearButtons(boolean b) {

            }

            @Override
            public boolean isShowYearButtons() {
                return false;
            }

            @Override
            public void setDoubleClickAction(boolean b) {

            }

            @Override
            public boolean isDoubleClickAction() {
                return false;
            }

            @Override
            public void setTextEditable(boolean b) {

            }

            @Override
            public boolean isTextEditable() {
                return false;
            }

            @Override
            public void setButtonFocusable(boolean b) {

            }

            @Override
            public boolean getButtonFocusable() {
                return false;
            }
        };

        // Create repeat checkbox
        repeatAppointmentCheckBox = new JCheckBox("Wiederholung");

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

        deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(termin != null) {

                    // Call method to delete appointment
                    terminListe.removeTermin(termin);

                    // Close window
                    frame.dispose();
                }
            }
        });

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

        // Create date model
        UtilDateModel model = new UtilDateModel();

        // Properties for date picker
        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");

        // Create date panel
        JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);

        // Create picker impl
        JDatePickerImpl repeatEndDatePickerImpl = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        // Add to panel
        panel.add(repeatEndDatePickerImpl);

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
        panel.add(new JLabel("Wiederholung:"), gbc);
        gbc.gridx = 1;
        panel.add(repeatAppointmentCheckBox, gbc);
        gbc.gridx = 0;
        panel.add(new JLabel("Häufigkeit:"), gbc);
        gbc.gridx = 1;
        panel.add(repeatFrequencyComboBox, gbc);
        gbc.gridx = 0;
        panel.add(new JLabel("Ende:"), gbc);
        gbc.gridx = 1;
        panel.add(repeatEndDatePickerImpl, gbc);

        // Initially disable repeat fields
        toggleRepeatFields(false, repeatEndDatePickerImpl);

        // Add action listener to enable/disable repeat fields
        repeatAppointmentCheckBox.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                toggleRepeatFields(repeatAppointmentCheckBox.isSelected(), repeatEndDatePickerImpl);
            }
        });


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
        deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Delete logic here
            }

        });
        // Panel für die Knöpfe "Abbrechen" und "Speichern"
        JPanel leftBox = new JPanel();
        JPanel rightBox = new JPanel();

        leftBox.add(deleteButton);


        // Left align components

        // Add buttons
        rightBox.add(cancelButton);
        rightBox.add(saveButton);

        leftBox.setLayout(new FlowLayout(FlowLayout.LEFT));
        rightBox.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        buttonPanel.add(leftBox);
        buttonPanel.add(rightBox);

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

    private void toggleRepeatFields(boolean enabled, JDatePickerImpl endDatePicker) {

        repeatFrequencyComboBox.setEnabled(enabled);

        // Use passed in end date picker
        endDatePicker.setEnabled(enabled);

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
        repeatFrequencyComboBox.setEnabled(isEditing);
        repeatAppointmentCheckBox.setEnabled(isEditing);
    }
    private void saveAppointment() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime startDateTime;
        LocalDateTime endDateTime;

        // Umwandlung von java.util.Date in java.time.LocalDate
        Date start = (Date) datePickerStart.getModel().getValue();
        LocalDate startDate = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        Date end = (Date) datePickerEnd.getModel().getValue();
        LocalDate endDate;

        if (end != null) {
            endDate = end.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } else {
            endDate = startDate; // Set end date to start date if end is null
        }

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
