package GUI;

import Calendar.Termin;
import Calendar.TerminListe;
import GUI.Exceptions.AppointmentMismatchMonthException;
import GUI.Exceptions.AppointmentOutOfMonthRangeException;
import GUI.Views.CalendarView;
import GUI.Views.CalendarViewManager;
import GUI.Views.MonthView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import IOManager.Database;
import IOManager.Exceptions.SQLPackageException;
import IOManager.Exceptions.WrongPathException;

import javax.swing.*;
import java.awt.*;
import java.time.YearMonth;
import java.util.Random;

/**
 * Diese Klasse repräsentiert das Hauptpanel der Anwendung.
 *
 * Autor: Philipp Voß
 * Version: 1.5
 * Erstellt am: 02.06.2023
 * Letzte Änderung: 19.07.2023
 *
 */
public class PanelMain extends JPanel {
    // Deklaration der Variablen
    private JTabbedPane tabbedPane;
    private PanelChange panelChange;
    private JFrame mainFrame;
    private CalendarViewManager viewManager;
    private CalendarView monthView;
    private JButton btn_createAppointment;
    private JPanel upperPanel;

    // --------------------------- Konstruktor und Hauptmethode -------------------------------------------

    /**
     * Erstellt ein neues PanelMain-Objekt mit der angegebenen Terminliste.
     *
     * @param terminListe Die Terminliste für den Kalender
     */
    public PanelMain(TerminListe terminListe) throws AppointmentOutOfMonthRangeException, AppointmentMismatchMonthException {
        YearMonth currentYearMonth = YearMonth.now();
        viewManager = new CalendarViewManager(terminListe);

        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        mainFrame = new JFrame("Main Panel");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setOpaque(false);
        setLayout(new BorderLayout());

        // Erstellen des Buttons
        btn_createAppointment = new JButton("Erstelle Termin");

        // Erstellen des neuen JPanels und Hinzufügen von PanelChange und dem Button
        upperPanel = new JPanel(new BorderLayout());
        upperPanel.add(btn_createAppointment, BorderLayout.WEST); // Button an der linken Seite

        btn_createAppointment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Appointment newAppointment = new Appointment();
                newAppointment.showUI();
            }
        });

        panelChange = new PanelChange(viewManager, this, terminListe);
        upperPanel.add(panelChange, BorderLayout.CENTER); // PanelChange in der Mitte

        // Hole die monthView von viewManager
        monthView = viewManager.getCurrentView();
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab(String.valueOf(currentYearMonth), monthView);
        System.out.println("Anzahl der Termine in terminListe in PanelMain (Konstruktor): " + terminListe.getTermine().size());

        add(upperPanel, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);

        mainFrame.getContentPane().add(this);
        // Set the frame size to 80% of the screen size
        int width = (int) (screenSize.width * 0.7);
        int height = (int) (screenSize.height * 0.7);
        mainFrame.setSize(width, height);
        mainFrame.setLocationRelativeTo(null); // Fenster in der Bildschirmmitte öffnen
        mainFrame.setVisible(true);

        mainFrame.revalidate();
        mainFrame.repaint();
    }

    /**
     * Die Hauptmethode der Anwendung.
     *
     * @param args Kommandozeilenargumente (werden ignoriert)
     *
     */
    public static void main(String[] args) throws SQLPackageException, WrongPathException, AppointmentOutOfMonthRangeException, AppointmentMismatchMonthException {
        TerminListe terminListe = new TerminListe();
        Database db = new Database();


        //erstelleZufaelligeTermine(terminListe);
        terminListe = db.getTermine();


        System.out.println("Anzahl der Termine in terminListe (Main Methode): " + terminListe.getTermine().size());

        PanelMain panelMain = new GUI.PanelMain(terminListe);
    }
    // --------------------------- Methoden zur Manipulation der GUI -------------------------------------------

    /**
     * Aktualisiert den Titel des Tabs mit dem aktuellen Monat und Jahr.
     */
    public void updateTabTitle() {
        String currentMonthAndYear = String.valueOf(monthView.getYearMonth());
        tabbedPane.setTitleAt(0, currentMonthAndYear);
        revalidate();
        repaint();
    }
    /**
     * Gibt die MonthView zurück.
     *
     * @return Die MonthView
     */
    public MonthView getMonthView() {
        return (MonthView) monthView;
    }
    // --------------------------- Methoden zur Bearbeitung der Daten -------------------------------------------

    /**
     * Erstellt zufällige Termine und fügt sie der Terminliste hinzu.
     *
     * @param terminListe Die Terminliste, zu der die Termine hinzugefügt werden sollen
     *
     */
    public static void erstelleZufaelligeTermine(TerminListe terminListe) throws SQLPackageException, WrongPathException {
        YearMonth currentYearMonth = YearMonth.now();
        int currentYear = currentYearMonth.getYear();
        Database db = new Database();
        Random random = new Random();

        for (int i = 1; i <= 1000; i++) {
            int year = currentYear + random.nextInt(2);  // Wählt zufällig das aktuelle oder nächste Jahr
            int month = random.nextInt(12) + 1;
            YearMonth randomYearMonth = YearMonth.of(year, month);

            int day = random.nextInt(28) + 1;

            int startHour = random.nextInt(13) + 6;
            int startMinute = (random.nextInt(4) * 15)%60;
            int endHour = (startHour + 2) % 24;
            int endMinute = startMinute;

            Termin termin = new Termin(
                    "Terminname " + i,
                    "Typ " + i,
                    false,
                    String.format("%d-%02d-%02d", randomYearMonth.getYear(), randomYearMonth.getMonthValue(), day),
                    String.format("%d-%02d-%02d", randomYearMonth.getYear(), randomYearMonth.getMonthValue(), day),
                    String.format("%02d:%02d", startHour, startMinute),
                    String.format("%02d:%02d", endHour, endMinute)
            );

            terminListe.addTermin(termin);

            db.addTermin(termin);
        }
    }
}