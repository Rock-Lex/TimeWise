package Utilities;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Diese Klasse stellt einen Formatter für Datumsfelder dar.
 * Es erbt von JFormattedTextField.AbstractFormatter und ermöglicht das Parsen und Formatieren von Datumswerten.
 *
 * Autor: Philipp Voß
 * Version: 1.1
 * Erstellt am: 28.07.2023
 * Letzte Änderung: 29.07.2023
 */
public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
    /**
     * Wandelt einen String in ein Datumswert-Objekt um.
     *
     * @param text Der String, der in ein Datumswert-Objekt umgewandelt werden soll.
     * @return Das umgewandelte Datumswert-Objekt.
     * @throws ParseException Wenn der Text nicht in ein Datumswert-Objekt umgewandelt werden kann.
     */
    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }
    /**
     * Wandelt ein Datumswert-Objekt in einen formatierten String um.
     *
     * @param value Das Datumswert-Objekt, das in einen String umgewandelt werden soll.
     * @return Der umgewandelte und formatierte String.
     * @throws ParseException Wenn das Datumswert-Objekt nicht in einen String umgewandelt werden kann.
     */
    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }
}
