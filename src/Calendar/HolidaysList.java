package Calendar;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * @author tobiasrehm
 * Bei dieser Klasse handelt es sich um die Holidays Class zur Berechnung und Festlegung der Feiertage in Berlin.
 *
 * Autor: Tobias Rehm
 * Version: 1.0.0
 * Erstellt am: 23.05.2023
 * Letzte Änderung: 23.07.2023
 */
public class HolidaysList {
    // das ist die Verwaltungsklasse für die Holiday-List
    public List<Holidays> holidaysList = new ArrayList<>();

    public HolidaysList(int year)  {
        //hier der Constructor, in der die Klasse erstellt wird
        addAllHolidays(year);
    }

    private void addAllHolidays(int year) {
        holidaysList.add(new Holidays("Neujahr", LocalDate.of(year, 1, 1)));
        holidaysList.add(new Holidays("Heilige drei Könige", LocalDate.of(year, 1, 6)));
        holidaysList.add(new Holidays("internationaler Frauentag", LocalDate.of(year, 3, 8)));
        holidaysList.add(new Holidays("Rosenmontag", Holidays.calculateEasterSunday(year).minusDays(48)));
        holidaysList.add(new Holidays("Faschingsdienstag", Holidays.calculateEasterSunday(year).minusDays(47)));
        holidaysList.add(new Holidays("Aschermittwoch", Holidays.calculateEasterSunday(year).minusDays(46)));
        holidaysList.add(new Holidays("Palmsonntag", Holidays.calculateEasterSunday(year).minusDays(7)));
        holidaysList.add(new Holidays("Karfreitag", Holidays.calculateEasterSunday(year).minusDays(2)));
        holidaysList.add(new Holidays("Karsamstag", Holidays.calculateEasterSunday(year).minusDays(1)));
        holidaysList.add(new Holidays("Ostersonntag", Holidays.calculateEasterSunday(year)));
        holidaysList.add(new Holidays("Ostermontag", Holidays.calculateEasterSunday(year).plusDays(1)));
        holidaysList.add(new Holidays("Tag der Arbeit", LocalDate.of(year, 5, 1)));
        holidaysList.add(new Holidays("Christi Himmelfahrt", Holidays.calculateEasterSunday(year).plusDays(39)));
        holidaysList.add(new Holidays("Pfingstsonntag", Holidays.calculateEasterSunday(year).plusDays(49)));
        holidaysList.add(new Holidays("Pfingstmontag", Holidays.calculateEasterSunday(year).plusDays(50)));
        holidaysList.add(new Holidays("Fronleichnam", Holidays.calculateEasterSunday(year).plusDays(60)));
        holidaysList.add(new Holidays("Augsburger Friedensfest", LocalDate.of(year, 8, 8)));
        holidaysList.add(new Holidays("Mariä Himmelfahrt", LocalDate.of(year, 8, 15)));
        holidaysList.add(new Holidays("Weltkindertag", LocalDate.of(year, 9, 20)));
        holidaysList.add(new Holidays("Tag der Deutschen Einheit", LocalDate.of(year, 10, 3)));
        holidaysList.add(new Holidays("Reformationstag", LocalDate.of(year, 10, 31)));
        holidaysList.add(new Holidays("Allerheiligen", LocalDate.of(year, 11, 1)));
        holidaysList.add(new Holidays("Buß- und Bettag", Holidays.BussUndBettag(year)));
        holidaysList.add(new Holidays("Heiligabend", LocalDate.of(year, 12, 24)));
        holidaysList.add(new Holidays("1. Weihnachtstag", LocalDate.of(year, 12, 25)));
        holidaysList.add(new Holidays("2. Weihnachtstag", LocalDate.of(year, 12, 26)));
        holidaysList.add(new Holidays("Sylvester", LocalDate.of(year, 12, 31)));
        holidaysList.add(new Holidays("Totensonntag", Holidays.BussUndBettag(year).plusDays(4)));
        holidaysList.add(new Holidays("1. Advent", Holidays.BussUndBettag(year).plusDays(18)));
        holidaysList.add(new Holidays("2. Advent", Holidays.BussUndBettag(year).plusDays(25)));
        holidaysList.add(new Holidays("3. Advent", Holidays.BussUndBettag(year).plusDays(32)));
        holidaysList.add(new Holidays("4. Advent", Holidays.BussUndBettag(year).plusDays(39)));
    }
    public List<Holidays> getHolidays() {
        return this.holidaysList;
    }
}
