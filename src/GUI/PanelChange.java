package GUI;

import Calendar.TerminListe;
import GUI.Exceptions.AppointmentMismatchMonthException;
import GUI.Exceptions.AppointmentOutOfMonthRangeException;
import GUI.Utilities.DateLabelFormatter;
import GUI.Views.CalendarView;
import GUI.Views.CalendarViewManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Properties;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdatepicker.impl.UtilCalendarModel;
import org.jdatepicker.util.JDatePickerUtil;
import org.jdatepicker.JDateComponentFactory;

/**
 * Diese Klasse repräsentiert das Panel für die Änderung von Ansichten und
 * weiter- bzw. zurückblättern im Kalender.
 *
 * Autor: Philipp Voß
 * Version: 1.2
 * Erstellt am: 03.06.2023
 * Letzte Änderung: 19.07.2023
 */
public class PanelChange extends JPanel {
    // Deklaration der UI-Elemente und Variablen
    private JPanel panelChange;
    private JPanel bottomPanel;
    private JButton btn_week;
    private JButton btn_month;
    private JButton btn_day;
    private JPanel monthChangePanel;
    private JPanel jumpPanel;
    private JButton btn_jumpTo;
    private JButton btn_today;
    private JButton nextButton;
    private JButton prevButton;
    private CalendarViewManager viewManager;
    private CalendarView calendarView;
    private PanelMain mainPanel;
    private TerminListe terminListe;
    private UtilDateModel model;
    private JDatePickerImpl datePicker;
    private JDialog datePickerDialog;


    /**
     * Erstellt ein neues PanelChange-Objekt mit dem angegebenen CalendarViewManager,
     * PanelMain und TerminListe.
     *
     * @param viewManager Der CalendarViewManager für die Ansichtsverwaltung.
     * @param mainPanel Das Hauptpanel, zu dem dieses PanelChange gehört.
     * @param terminListe Die Terminliste für den Kalender.
     */
    public PanelChange(CalendarViewManager viewManager,PanelMain mainPanel, TerminListe terminListe){
        this.viewManager = viewManager;
        this.mainPanel = mainPanel;
        this.terminListe = terminListe;
        System.out.println("Anzahl der Termine in terminListe in PanelChange: " + this.terminListe.getTermine().size());

        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        prevButton = new JButton("Zurück");
        nextButton = new JButton("Weiter");
        btn_month = new JButton("Monat");
        btn_week = new JButton("Woche");
        btn_day = new JButton("Tag");
        btn_today = new JButton("Aktueller Tag");
        btn_jumpTo = new JButton("Springe zu");

        // JDatePicker Initialisierung
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        Dimension buttonSize = btn_month.getPreferredSize();
        datePicker.setPreferredSize(buttonSize);

        jumpPanel = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        jumpPanel.setLayout(gridBagLayout);

        //Setze Constraints für btn_today
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        jumpPanel.add(btn_today, constraints);

        //Setze Constraints für datePicker
        constraints.gridy = 1;

        datePicker.setPreferredSize(btn_month.getPreferredSize());
        jumpPanel.add(datePicker, constraints);

        //Setze Constraints für btn_jumpTo
        constraints.gridy = 2;
        jumpPanel.add(btn_jumpTo, constraints);

        monthChangePanel = new JPanel();
        GridBagLayout gridBagLayoutMonth = new GridBagLayout();
        GridBagConstraints constraintsMonth = new GridBagConstraints();
        monthChangePanel.setLayout(gridBagLayoutMonth);

        // Setze Constraints für prevButton
        constraintsMonth.fill = GridBagConstraints.HORIZONTAL;
        constraintsMonth.gridx = 0;
        constraintsMonth.gridy = 0;
        monthChangePanel.add(prevButton, constraintsMonth);

        // Setze Constraints für jumpPanel
        constraintsMonth.gridx = 1;
        monthChangePanel.add(jumpPanel, constraintsMonth);

        // Setze Constraints für nextButton
        constraintsMonth.gridx = 2;
        monthChangePanel.add(nextButton, constraintsMonth);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        bottomPanel.add(btn_day);
        bottomPanel.add(btn_week);
        bottomPanel.add(btn_month);

        add(monthChangePanel);
        add(bottomPanel);
        setSize(600, 100);
        setVisible(true);

        /**
         * ActionListener, der aufgerufen wird, wenn der "Weiter" Button geklickt wird.
         * Führt den Wechsel zur nächsten Zeitspanne in der aktuellen Ansicht durch.
         *
         * @param e Das ActionEvent-Objekt, das den Button-Klick ausgelöst hat.
         * @throws RuntimeException Falls ein Fehler beim Wechsel zur nächsten Zeitspanne auftritt.
         */
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    viewManager.nextPeriod(terminListe);
                } catch (AppointmentOutOfMonthRangeException ex) {
                    throw new RuntimeException(ex);
                } catch (AppointmentMismatchMonthException ex) {
                    throw new RuntimeException(ex);
                }
                mainPanel.updateTabTitle();
            }
        });

/**
 * ActionListener, der aufgerufen wird, wenn der "Zurück" Button geklickt wird.
 * Führt den Wechsel zur vorherigen Zeitspanne in der aktuellen Ansicht durch.
 *
 * @param e Das ActionEvent-Objekt, das den Button-Klick ausgelöst hat.
 * @throws RuntimeException Falls ein Fehler beim Wechsel zur vorherigen Zeitspanne auftritt.
 */
        prevButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    viewManager.previousPeriod(terminListe);
                } catch (AppointmentOutOfMonthRangeException ex) {
                    throw new RuntimeException(ex);
                } catch (AppointmentMismatchMonthException ex) {
                    throw new RuntimeException(ex);
                }
                mainPanel.updateTabTitle();
            }
        });

/**
 * ActionListener, der aufgerufen wird, wenn der "Aktueller Tag" Button geklickt wird.
 * Springt zur Ansicht des aktuellen Tages in der aktuellen Ansicht.
 *
 * @param e Das ActionEvent-Objekt, das den Button-Klick ausgelöst hat.
 * @throws RuntimeException Falls ein Fehler beim Springen zum aktuellen Tag auftritt.
 */
        btn_today.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    viewManager.jumpToCurrentDay();
                } catch (AppointmentOutOfMonthRangeException ex) {
                    throw new RuntimeException(ex);
                } catch (AppointmentMismatchMonthException ex) {
                    throw new RuntimeException(ex);
                }
                mainPanel.updateTabTitle();
            }
        });

/**
 * ActionListener, der aufgerufen wird, wenn der "Springe zu" Button geklickt wird.
 * Holt das ausgewählte Datum aus dem JDatePicker.
 * Wenn ein gültiges Datum ausgewählt wurde, wird zur Ansicht dieses Datums gewechselt.
 *
 * @param e Das ActionEvent-Objekt, das den Button-Klick ausgelöst hat.
 * @throws RuntimeException Falls ein Fehler beim Springen zum ausgewählten Datum auftritt.
 */
        btn_jumpTo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Date selectedDate = (Date) datePicker.getModel().getValue();
                if (selectedDate != null) {
                    LocalDate date = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    try {
                        viewManager.jumpToDate(date);
                        mainPanel.updateTabTitle();
                    } catch (AppointmentOutOfMonthRangeException ex) {
                        throw new RuntimeException(ex);
                    } catch (AppointmentMismatchMonthException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

    }
}