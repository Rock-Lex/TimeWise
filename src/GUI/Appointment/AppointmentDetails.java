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

    public AppointmentDetails(TerminListe terminListe) {
        this.terminListe = terminListe;

        this.textFieldTerminTeilnehmer = new JTextField(15);
        this.participantFields.add(this.textFieldTerminTeilnehmer);  // Add the initial textFieldTerminTeilnehmer to the participantFields list
        addParticipantBtn = new JButton("Add Participant");
        addParticipantBtn.addActionListener(e -> addParticipantField());
        setupUI();
    }


    public List<String> getParticipants() {
        List<String> participants = new ArrayList<>();
        for (JTextField field : participantFields) {
            participants.add(field.getText());
        }
        return participants;
    }

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

    private void setRepeatFieldsEnabled(boolean b) {
    }

    public void addTermin(Termin termin) {
        terminListe.addTermin(termin);
    }

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

    public String getTermineAsString() {
        StringBuilder sb = new StringBuilder();
        for (Termin t : terminListe.getTermine()) {
            sb.append(t.getTitle() + ",");
        }
        return sb.toString();
    }

    public void setTermineFromString(String terminTitles) {
        String[] parts = terminTitles.split(",");
        for (String title : parts) {
            Termin t = new Termin(title, "", false, null, null);
            terminListe.addTermin(t);
        }
    }
    public JPanel getDetailsPanel() {
        return details;
    }
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
