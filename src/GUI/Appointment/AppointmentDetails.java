package GUI.Appointment;

import Calendar.Termin;
import Calendar.TerminListe;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.*;
/**
 * Eine Klasse, die ein Detailfenster für Termine darstellt. Soll zukünftig als Erweiterung für AppointmentForm.java fungieren.
 * Es ermöglicht das Hinzufügen von Teilnehmern zu einem Termin,
 * die Festlegung einer Wiederholungsfrequenz und das Setzen eines Enddatums für die Wiederholung.
 *
 * @author Philipp Voß
 * @version 0.0.1
 * @since 30.05.2023
 * Letzte Änderung: 30.07.2023
 */
public class AppointmentDetails {
    private JPanel details;
    private TerminListe terminListe;
    private JFrame frame;
    private JTextField textField;
    private boolean repeatEnabled;
    private String repeatFrequency;
    private LocalDate repeatEndDate;
    private JCheckBox repeatCheckBox;
    private JComboBox repeatFrequencyCombo;
    private JDatePicker repeatEndDatePicker;
    private List<JTextField> participantFields = new ArrayList<>();
    private JTextField textFieldTerminTeilnehmer;
    private JButton addParticipantBtn;
    private int currentRow = 0;
    /**
     * Konstruktor für die Klasse AppointmentDetails.
     * Initialisiert eine neue Instanz der Klasse mit einer vorgegebenen Liste von Terminen.
     *
     * @param terminListe Eine Liste von Terminen, die in der GUI angezeigt werden soll.
     */
    public AppointmentDetails(TerminListe terminListe) {
        this.terminListe = terminListe;

        this.textFieldTerminTeilnehmer = new JTextField(15);
        this.participantFields.add(this.textFieldTerminTeilnehmer);  // Add the initial textFieldTerminTeilnehmer to the participantFields list
        addParticipantBtn = new JButton("Add Participant");
        addParticipantBtn.addActionListener(e -> addParticipantField());
        setupUI();
    }

    /**
     * Liefert eine Liste der Teilnehmernamen aus den Eingabefeldern.
     *
     * @return Eine Liste der Teilnehmernamen.
     */
    public List<String> getParticipants() {
        List<String> participants = new ArrayList<>();
        for (JTextField field : participantFields) {
            participants.add(field.getText());
        }
        return participants;
    }
    /**
     * Initialisiert und erstellt die Benutzeroberfläche für die Detailansicht.
     */
    private void setupUI() {
        details = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        // Create UI components
        repeatCheckBox = new JCheckBox("Repeat");
        String[] freqs = {"Daily", "Weekly", "Monthly"};
        repeatFrequencyCombo = new JComboBox(freqs);

        gbc.gridy = currentRow++;

        // Create date model
        UtilDateModel model = new UtilDateModel();

        // Properties for date picker
        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");
        // Create date panel
        //JDatePanelImpl datePanel = new JDatePickerImpl(model, properties);

        // Create picker impl
        //JDatePickerImpl repeatEndDatePickerImpl = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 15);

        gbc.gridx = 0;
        details.add(new JLabel("Wiederholung:"), gbc);
        gbc.gridx = 1;
        details.add(repeatCheckBox, gbc);
        gbc.gridx = 0;
        details.add(new JLabel("Häufigkeit:"), gbc);
        gbc.gridx = 1;
        details.add(repeatFrequencyCombo, gbc);
        gbc.gridx = 0;
        details.add(new JLabel("Ende:"), gbc);
        gbc.gridx = 1;
        //details.add(repeatEndDatePickerImpl, gbc);
        gbc.gridx = 0;
        details.add(new JLabel("Teilnehmer:"), gbc);
        gbc.gridx = 1;
        details.add(participantFields.get(0), gbc); // Get the first text field from the participantFields list
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.gridx = 0;
        details.add(addParticipantBtn, gbc);
    }



    /**
     * Fügt ein neues Textfeld zur Eingabe von Teilnehmernamen hinzu.
     */
    private void addParticipantField() {
        JTextField newTextField = new JTextField(15);
        participantFields.add(newTextField);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = currentRow++;

        details.add(newTextField, gbc);

        details.revalidate();
        details.repaint();
    }
    /**
     * Aktiviert oder deaktiviert die Eingabefelder für die Wiederholung.
     *
     * @param b Ein boolescher Wert, der angibt, ob die Eingabefelder aktiviert (true) oder deaktiviert (false) sein sollen.
     */
    private void setRepeatFieldsEnabled(boolean b) {
    }
    /**
     * Fügt einen neuen Termin zur Liste der Termine hinzu.
     *
     * @param termin Der Termin, der hinzugefügt werden soll.
     */
    public void addTermin(Termin termin) {
        terminListe.addTermin(termin);
    }
    /**
     * Legt fest, ob der Termin wiederholt wird, sowie die Frequenz und das Enddatum der Wiederholung.
     *
     * @param enabled Ein boolescher Wert, der angibt, ob der Termin wiederholt wird.
     * @param frequency Die Frequenz der Wiederholung ("Daily", "Weekly", "Monthly").
     * @param endDate Das Enddatum der Wiederholung.
     */
    public void setRepeat(boolean enabled, String frequency, LocalDate endDate) {
        this.repeatEnabled = enabled;
        this.repeatFrequency = frequency;
        this.repeatEndDate = endDate;
    }

    public boolean isRepeatEnabled() {
        return repeatEnabled;
    }

    public String getRepeatFrequency() {
        return repeatFrequency;
    }

    public LocalDate getRepeatEndDate() {
        return repeatEndDate;
    }
    /**
     * Liefert eine Zeichenkette, die alle Termine als kommaseparierte Liste enthält.
     *
     * @return Eine Zeichenkette, die alle Termintitel enthält, getrennt durch Kommata.
     */
    public String getTermineAsString() {
        StringBuilder sb = new StringBuilder();
        for (Termin t : terminListe.getTermine()) {
            sb.append(t.getTitle() + ",");
        }
        return sb.toString();
    }
    /**
     * Setzt die Termine der Terminliste basierend auf einer kommaseparierten Zeichenkette von Termintiteln.
     *
     * @param terminTitles Eine kommaseparierte Zeichenkette von Termintiteln.
     */
    public void setTermineFromString(String terminTitles) {
        String[] parts = terminTitles.split(",");
        for (String title : parts) {
            Termin t = new Termin(title, "", false, null, null);
            terminListe.addTermin(t);
        }
    }
    /**
     * Liefert das Detail-Panel, das zur Darstellung in einem Frame oder Panel verwendet werden kann.
     *
     * @return Das Detail-Panel.
     */
    public JPanel getDetailsPanel() {
        return details;
    }
    /**
     * Der Einstiegspunkt des Programms.
     *
     * @param args Ein Array von Zeichenketten, das die Befehlszeilenargumente darstellt.
     */
    public static void main(String[] args) {
        TerminListe terminListe = new TerminListe();
        AppointmentDetails details = new AppointmentDetails(terminListe);

        JFrame frame = new JFrame("Appointment Details");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.2);
        int height = (int) (screenSize.height * 0.5);
        frame.setSize(width, height);
        frame.add(details.getDetailsPanel());

        frame.pack();
        frame.setVisible(true);
    }
}
