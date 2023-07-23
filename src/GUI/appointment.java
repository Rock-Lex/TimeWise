package GUI;


import Calendar.Termin;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;




 /* Eine GUI zur erstellung von Terminen.
         *
         * Autor: Simon Degmair
         * Version: 1.3
         * Erstellt am: 18.07.2023
         * Letzte Änderung: 22.07.2023
         */

public class appointment {

    private static final List<Termin> appointments = new ArrayList<>();
    private static final JFrame frame = new JFrame("Termin App");
    private static final JPanel mainPanel = new JPanel(new BorderLayout());
    private static final JButton editButton = new JButton("Bearbeiten");
    private static final JButton cancelButton = new JButton("Abbrechen");
    private static final JButton saveButton = new JButton("Speichern");
    private static final String[] columnNames = {"Titel", "Mehrtägig", "Startdatum", "Startzeit", "Enddatum", "Endzeit", "Typ", "Terminbeschreibung"};
    private static boolean running = true;

    public static void main(String[] args) {
        // Richtet die Graphische Oberfläche ein
        setupUI();

    }

    public static void showUI() {
        setupUI();
    }

    private static void setupUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        for (String columnName : columnNames) {
            JLabel label = new JLabel(columnName + ":");
            if (columnName.equals("Mehrtägig")) {
                JCheckBox checkBox = new JCheckBox();
                checkBox.setEnabled(false); // Deaktiviert die Checkbox zu Beginn
                panel.add(label, gbc);
                gbc.gridx = 1;
                panel.add(checkBox, gbc);
                gbc.gridx = 0; // Zurücksetzen der x-Position für das nächste Label
            } else {
                JTextField textField = new JTextField(15);
                textField.setEnabled(false); // Deaktiviert die Textfelder zu Beginn
                panel.add(label, gbc);
                gbc.gridx = 1;
                panel.add(textField, gbc);
                gbc.gridx = 0; // Zurücksetzen der x-Position für das nächste Label
            }
        }

        JScrollPane scrollPane = new JScrollPane(panel);

        // ActionListener für den "Bearbeiten"-Knopf
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toggleEditing(panel); // Aktiviert/Deaktiviert die Textfelder und die Checkbox
            }
        });

        // ActionListener für den "Abbrechen"-Knopf
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                running = false;
            }
        });

        // ActionListener für den "Speichern"-Knopf
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addAppointment(panel); // Speichert die Daten und schließt das Fenster
                running = false;
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
        int width = (int) (screenSize.width * 0.7);
        int height = (int) (screenSize.height * 0.7);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null); // Fenster in der Bildschirmmitte öffnen
        frame.setVisible(true);
    }

    private static void toggleEditing(JPanel panel) {
        // Aktiviert/Deaktiviert die Textfelder und die Checkbox je nach aktuellem Zustand
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField || component instanceof JCheckBox) {
                component.setEnabled(!component.isEnabled());
            }
        }
    }

    private static void addAppointment(JPanel panel) {
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

        // Hier werden die Daten in der Datenbank gespeichert
        /*try {
            Database database = new Database();
            database.addTermin(null, rowData[0], LocalDateTime.parse(rowData[2]), LocalDateTime.parse(rowData[4]), rowData[6], Integer.parseInt(rowData[1]), rowData[7]);
        } catch (WrongPathException | SQLPackageException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }


    static class appointment {
        String title;
        String multiDay;
        String startDate;
        String startTime;
        String endDate;
        String endTime;
        String type;
        String description;

        public Termin(String title, String multiDay, String startDate, String startTime, String endDate, String endTime, String type, String description) {
            this.title = title;
            this.multiDay = multiDay;
            this.startDate = startDate;
            this.startTime = startTime;
            this.endDate = endDate;
            this.endTime = endTime;
            this.type = type;
            this.description = description;


        }*/
    }
}



















































