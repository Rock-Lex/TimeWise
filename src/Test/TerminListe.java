package Test;

import Calendar.Termin;
import IOManager.Database;
import IOManager.Exceptions.SQLPackageException;
import IOManager.Exceptions.WrongPathException;

import java.util.ArrayList;
import java.util.List;

public class TerminListe {

    /**
     * Bei dieser Klasse handelt es sich um einen Test fur Calendar.
     * *
     * Autor: Oleksandr Kamenskyi
     * Version: 1.0.0
     * Erstellt am: 23.05.2023
     * Letzte Änderung: 23.05.2023
     */

    public TerminListe() {

    }


    public void test() {
        System.out.println("Test started");
        List<Termin> termine = new ArrayList<>();
        Database database = null;

        try {
            database = new Database("bla-keks", "Haha");
        } catch (SQLPackageException | WrongPathException e) {
            throw new RuntimeException(e);
        }

        Calendar.TerminListe kalender = new Calendar.TerminListe(database, termine);
        System.out.println(kalender.getTermine().size());

        Termin termin1 = new Termin("Ein Tag","MT",true,"2022-12-21", "2022-12-21", "12:00","13:00");
        Termin termin2 = new Termin("Zwei Tag","AT",true,"2023-12-21", "2023-12-21", "12:00","13:00");

        termine.add(termin1);
        termine.add(termin2);

        System.out.println(kalender.getTermine().size());

        System.out.println("\n");

        System.out.println(kalender.toString());





        System.out.println("Test ended");
    }
}
