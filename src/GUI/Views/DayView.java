
package GUI.Views;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse DayView stellt eine JFrame-Ansicht für einen einzelnen Tag im Kalender dar.
 * Sie zeigt Termine für den Tag an und ermöglicht das Hinzufügen und Entfernen von Terminen.
 *
 * @author Leif Maluck
 * @version 1.0
 * @since 2023-07-01
 * Letzte Bearbeitung: 2023-07-30
 */
public class DayView extends JFrame {
    private Kalender kalender;
    private DefaultTableModel tableModel;
    private JTable table;

    public DayView() {
        kalender = new Kalender();

        setTitle("Tagesansicht Kalender");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Tabelle für die Darstellung der Termine
        tableModel = new DefaultTableModel(new Object[]{"ID", "Start", "Ende", "Erinnerungen"}, 0);
        table = new JTable(tableModel);
        table.setRowHeight(30);

        // Scrollbar zur Tabelle hinzufügen
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        // Buttons zum Hinzufügen und Entfernen von Terminen
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addTerminButton = new JButton("Termin hinzufügen");
        JButton removeTerminButton = new JButton("Termin entfernen");

        addTerminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TerminDialog dialog = new TerminDialog(DayView.this);
                dialog.setVisible(true);
            }
        });

        removeTerminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int terminId = (int) tableModel.getValueAt(selectedRow, 0);
                    kalender.removeTermin(terminId);
                    tableModel.removeRow(selectedRow);
                }
            }
        });

        buttonPanel.add(addTerminButton);
        buttonPanel.add(removeTerminButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
    /**
     * Fügt einen Termin zur Ansicht hinzu und aktualisiert die Tabelle.
     *
     * @param termin Der Termin, der hinzugefügt werden soll.
     */
    public void addTermin(Termin termin) {
        kalender.addTermin(termin);
        tableModel.addRow(new Object[]{termin.getId(), termin.getStart(), termin.getEnde(), termin.getErinnerungen()});
    }
    /**
     * Die main-Methode startet die Anwendung, indem sie die GUI im Event Dispatch Thread erstellt.
     *
     * @param args Die Befehlszeilenargumente (werden hier nicht verwendet).
     */
    public static void main(String[] args) {
        // GUI im Event Dispatch Thread erstellen
        javax.swing.SwingUtilities.invokeLater(() -> {
            new DayView();
        });
    }
}
/**
 * Die Klasse TerminDialog stellt einen Dialog zum Hinzufügen von Terminen dar.
 * Der Dialog wird von der DayView verwendet, um neue Termine einzugeben.
 *
 * @author Leif Maluck
 * @version 1.0
 * Erstellt am 09.07.2023
 * Zuletzt bearbeitet am 30.07.2023
 */
class TerminDialog extends JDialog {
    private JTextField idField, startField, endeField, erinnerungenField;

    public TerminDialog(JFrame parent) {
        super(parent, "Termin hinzufügen", true);
        setSize(300, 200);
        setLayout(new BorderLayout());
        setLocationRelativeTo(parent);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        idField = new JTextField();
        startField = new JTextField();
        endeField = new JTextField();
        erinnerungenField = new JTextField();

        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Startdatum (yyyy-MM-dd):"));
        inputPanel.add(startField);
        inputPanel.add(new JLabel("Enddatum (yyyy-MM-dd):"));
        inputPanel.add(endeField);
        inputPanel.add(new JLabel("Erinnerungen (durch Komma getrennt):"));
        inputPanel.add(erinnerungenField);

        add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Abbrechen");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                LocalDate start = LocalDate.parse(startField.getText());
                LocalDate ende = LocalDate.parse(endeField.getText());
                String[] erinnerungenArr = erinnerungenField.getText().split(",");
                List<Erinnerung> erinnerungen = new ArrayList<>();
                for (String erinnerungName : erinnerungenArr) {
                    erinnerungen.add(new Erinnerung(erinnerungName.trim()));
                }
                Termin termin = new Termin(id, start, ende, erinnerungen);
                ((DayView) parent).addTermin(termin);
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
    }
}

class Kalender {
    private ArrayList<Termin> termine;

    public Kalender() {
        termine = new ArrayList<>();
    }

    public void addTermin(Termin termin) {
        termine.add(termin);
    }

    public void removeTermin(int id) {
        termine.removeIf(termin -> termin.getId() == id);
    }
}

class Termin {
    private int id;
    private LocalDate start;
    private LocalDate ende;
    private List<Erinnerung> erinnerungen;

    public Termin(int id, LocalDate start, LocalDate ende, List<Erinnerung> erinnerungen) {
        this.id = id;
        this.start = start;
        this.ende = ende;
        this.erinnerungen = erinnerungen;
    }

    public int getId() {
        return id;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnde() {
        return ende;
    }

    public List<Erinnerung> getErinnerungen() {
        return erinnerungen;
    }
}

class Erinnerung {
    private String name;

    public Erinnerung(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
