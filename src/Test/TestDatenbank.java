package Test;

import Calendar.Termin;
import IOManager.Database;
import IOManager.Exceptions.SQLPackageException;
import IOManager.Exceptions.WrongPathException;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.List;

public class TestDatenbank {

    /**
     * Bei dieser Klasse handelt es sich um einen Test fur Database.
     * *
     * Autor: Oleksandr Kamenskyi
     * Version: 1.0.0
     * Erstellt am: 23.05.2023
     * Letzte Änderung: 23.05.2023
     */

    TestDatenbank() {

    }

    public void test() {
        System.out.println("Test started");


        System.out.println("Test ended");
    }

    public static void main(String[] args) {
        Database database = null;
        try {
            database = new Database();
        } catch (WrongPathException | SQLPackageException e) {
            throw new RuntimeException(e);
        }

        LocalDateTime lt = LocalDateTime.now();
        database.addTermin("", "Mehrere Tage", lt, lt,"AT", "Oleksandr Kamenskyi");
//        database.addTermin("Mehrere Tage", lt, lt,"AT", "Oleksandr Kamenskyi");
//        database.addTermin("Mehrere Tage", lt, lt,"AT", "Oleksandr Kamenskyi");
//        database.addTermin("Mehrere Tage", lt, lt,"AT", "Oleksandr Kamenskyi");
//        database.deleteTermin("fuitw6oZUv4YpsQhAl6WABNMxdevTmF7tqRqi1vCYgfbra0LhP");
        List<Termin> termine = database.getTermine();

        if (termine != null) {
            System.out.println("Termine size");
            System.out.println(termine.size());

            for (Termin termin : termine)
            {
                System.out.println(termin.getTitle());
                System.out.println(termin.getType());
            }
        }
        else {
            System.out.println("List ist NULL");
        }
    }
}
