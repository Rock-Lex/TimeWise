package GUI;


import Calendar.Termin;
import Calendar.TerminListe;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeParseException;
//import org.jdatepicker.impl.JDatePanelImpl;
//import org.jdatepicker.impl.JDatePickerImpl;
//import org.jdatepicker.impl.UtilDateModel;


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

        // Erstellen der Textfelder und der Checkbox
        this.textFieldTitel = new JTextField(15);
        this.checkBoxMehrtagig = new JCheckBox();
        this.textFieldStartdatum = new JTextField(15);
        this.textFieldStartzeit = new JTextField(15);
        this.textFieldEnddatum = new JTextField(15);
        this.textFieldEnddatum.setEnabled(false);
        this.textFieldEndzeit = new JTextField(15);
        this.textFieldTyp = new JTextField(15);
        this.textFieldTerminbeschreibung = new JTextField(15);

        // Wenn ein Termin übergeben wurde, die Textfelder und die Checkbox entsprechend vorbelegen
        if (termin != null) {
            this.currentTermin = termin;
            textFieldTitel.setText(termin.getTitle());
            checkBoxMehrtagig.setSelected(termin.isMultiDay());
            textFieldStartdatum.setText(termin.getStartDate());
            textFieldStartzeit.setText(termin.getStartTime());
            textFieldEnddatum.setText(termin.getEndDate());
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

        checkBoxMehrtagig.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    textFieldEnddatum.setEnabled(true);
                } else {
                    textFieldEnddatum.setEnabled(false);
                }
            }
        });


        gbc.gridx = 0;
        panel.add(new JLabel("Startdatum:"), gbc);
        gbc.gridx = 1;
        panel.add(textFieldStartdatum, gbc);
        gbc.gridx = 0;
        panel.add(new JLabel("Startzeit:"), gbc);
        gbc.gridx = 1;
        panel.add(textFieldStartzeit, gbc);
        gbc.gridx = 0;
        panel.add(new JLabel("Enddatum:"), gbc);
        gbc.gridx = 1;
        panel.add(textFieldEnddatum, gbc);
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
        // Aktiviert/Deaktiviert die Textfelder und die Checkbox basierend auf dem übergebenen Bearbeitungszustand
        textFieldTitel.setEnabled(isEditing);
        checkBoxMehrtagig.setEnabled(isEditing);
        textFieldStartdatum.setEnabled(isEditing);
        textFieldStartzeit.setEnabled(isEditing);
        textFieldEndzeit.setEnabled(isEditing);
        textFieldTyp.setEnabled(isEditing);
        textFieldTerminbeschreibung.setEnabled(isEditing);
    }
    /**
     * Fügt einen Termin von den Textfeldern und der Checkbox in die Datenbank hinzu.
     * Extrahiert die eingegebenen Daten, erstellt einen Termin und speichert ihn in der Datenbank.
     *
     * @param panel Das JPanel, das die Textfelder und Checkbox enthält.
     */
    private void addAppointment(JPanel panel) {
        // Code zur Hinzufügung eines Termins von den Textfeldern und Checkbox in die Datenbank
        String[] rowData = new String[columnNames.length];
        Component[] components = panel.getComponents();

        for (int i = 0; i < components.length; i += 2) {
            JLabel label = (JLabel) components[i];
            Component component = components[i + 1];

            if (component instanceof JTextField) {
                JTextField textField = (JTextField) component;
                rowData[i / 2] = textField.getText();
                textField.setText(""); // Zurücksetzen des Textfelds für den nächsten Termin
            } else if (component instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) component;
                rowData[i / 2] = String.valueOf(checkBox.isSelected());
            }
        }

        // Wenn die Checkbox "Mehrtägig" nicht angekreuzt ist, wird das Enddatum mit dem Startdatum gleichgesetzt
        if (!Boolean.parseBoolean(rowData[1])) {
            rowData[4] = rowData[2];
        }
    }
    private void saveAppointment() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime startDateTime;
        LocalDateTime endDateTime;

        try {
            startDateTime = LocalDateTime.of(
                    LocalDate.parse(textFieldStartdatum.getText(), dateFormatter),
                    LocalTime.parse(textFieldStartzeit.getText(), timeFormatter));
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Das Startdatum oder die Startzeit ist ungültig.", "Eingabefehler", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (textFieldEnddatum.getText().isEmpty()) {
            endDateTime = startDateTime;
        } else {
            try {
                endDateTime = LocalDateTime.of(
                        LocalDate.parse(textFieldEnddatum.getText(), dateFormatter),
                        LocalTime.parse(textFieldEndzeit.getText(), timeFormatter));
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Das Enddatum oder die Endzeit ist ungültig.", "Eingabefehler", JOptionPane.ERROR_MESSAGE);
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
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime startDateTime;
        LocalDateTime endDateTime;

        try {
            startDateTime = LocalDateTime.of(
                    LocalDate.parse(textFieldStartdatum.getText(), dateFormatter),
                    LocalTime.parse(textFieldStartzeit.getText(), timeFormatter));
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Das Startdatum oder die Startzeit ist ungültig.", "Eingabefehler", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (textFieldEnddatum.getText().isEmpty()) {
            endDateTime = startDateTime;
        } else {
            try {
                endDateTime = LocalDateTime.of(
                        LocalDate.parse(textFieldEnddatum.getText(), dateFormatter),
                        LocalTime.parse(textFieldEndzeit.getText(), timeFormatter));
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Das Enddatum oder die Endzeit ist ungültig.", "Eingabefehler", JOptionPane.ERROR_MESSAGE);
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
