
import Calendar.Termin;
import IOManager.Database;

public class Main {
    public static void main(String[] args) {
        testDates();
        //testDB();
    }

    public static void testDates() {
        Termin eintaegigerTermin = new Termin("Ein Tag","MT",true,"2022-12-21", "2022-12-21", "12:00","13:00");
        System.out.println(eintaegigerTermin.title);
        System.out.println(eintaegigerTermin.start);
        System.out.println(eintaegigerTermin.end);
        System.out.println();
        Termin mehrtaegigerTermin = new Termin("Mehrere Tage", "AT", true,"2022-12-21", "2022-12-22", "12:00","13:00");
        System.out.println(mehrtaegigerTermin.title);
        System.out.println(mehrtaegigerTermin.start);
        System.out.println(mehrtaegigerTermin.end);
    }
    public static void testDB() {
        Database db = new Database();

    }
}
