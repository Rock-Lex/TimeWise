import Calendar.Termin;
import IOManager.Database;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        //testTerminClass();
        testDatenbankClass();
    }

    public static void testTerminClass() {
        System.out.println("TEST CASES");

        System.out.println("TEST 1");
        Termin eintaegigerTermin = new Termin("Ein Tag","MT",true,"2022-12-21", "2022-12-21", "12:00","13:00");
        LocalDateTime lt = LocalDateTime.now();

        eintaegigerTermin.setStart(lt);
        eintaegigerTermin.setMultiDay(true);
        eintaegigerTermin.setEnd(lt);
        eintaegigerTermin.setTitle("Test Title");
        eintaegigerTermin.setType("MT");

        System.out.println();

        System.out.println(eintaegigerTermin.getId());
        System.out.println(eintaegigerTermin.getStart());
        System.out.println(eintaegigerTermin.getEnd());
        System.out.println(eintaegigerTermin.getTitle());
        System.out.println(eintaegigerTermin.getType());
        System.out.println(eintaegigerTermin.isMultiDay());

        System.out.println("TEST 2");
        Termin mehrtaegigerTermin = new Termin("Mehrere Tage", "AT", true,"2022-12-21", "2022-12-22", "12:00","13:00");

        System.out.println();

        LocalDateTime lt2 = LocalDateTime.now();

        mehrtaegigerTermin.setStart(lt2);
        mehrtaegigerTermin.setMultiDay(true);
        mehrtaegigerTermin.setEnd(lt2);
        mehrtaegigerTermin.setTitle("Test Title 22");
        mehrtaegigerTermin.setType("AT");

        System.out.println();

        System.out.println(mehrtaegigerTermin.getId());
        System.out.println(mehrtaegigerTermin.getStart());
        System.out.println(mehrtaegigerTermin.getEnd());
        System.out.println(mehrtaegigerTermin.getTitle());
        System.out.println(mehrtaegigerTermin.getType());
        System.out.println(mehrtaegigerTermin.isMultiDay());
    }
    public static void testDatenbankClass() {
        Database db = new Database();
        List<Termin> arr = db.getTerminArray();

        for (Termin termin : arr) {
            System.out.println(termin.getStart());
            db.deleteTermin(termin);
        }
    }
}