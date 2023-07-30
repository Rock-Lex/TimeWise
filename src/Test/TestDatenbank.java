package Test;

import Calendar.Termin;
import Calendar.TerminListe;
import IOManager.Database;
import IOManager.Exceptions.SQLPackageException;
import IOManager.Exceptions.WrongPathException;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.List;

public class TestDatenbank {

    /**
     * Bei dieser Klasse handelt es sich um eine Testklasse für die Datenbank.
     *
     * @author Oleksandr Kamenskyi
     * version 1.2.3
     * @since 23.05.2023
     * Letzte Änderung: 19.06.2023
     */

    TestDatenbank() {

    }

    public void test() {
        // Test ID: TID1001
        // Name:
        // Input:
        // erwartete Ausgabe:
        // Ergebnis:

        printTestStart("Database Class");

        LocalDateTime lt = LocalDateTime.now();

        printTestCase(1, "Default Database Constructor");
        Database database = testCreateDatabase();
        printTestCase(2, "Database Constructor with path and name");
        Database database2 = testCreateDatabaseWithPath("/Users/okamenskyi/files/study/IT/javaTasks/TimeWise/", "TimeWise.db");
        printTestCase(3,"Database Constructor with WRONG path and name");
        Database database3 = testCreateDatabaseWithPath("/files/study/IT/javaTasks/TimeWise/", "TimeWise.db");

        database = testAddTermine(database);
        printTestLine();
        database = testGetTermine(database);

        database = testDeleteTermine(database);

        printTestEnd("Database Class");
    }

    private Database testDeleteTermine(Database database) {
        // Test ID: TID1002
        // Name:
        // Input:
        // erwartete Ausgabe:
        // Ergebnis:

        printTestStart("Delete termins function");

        printTestCase(1, "Delete existing Termin by ID");
        database.deleteTermin("fuitw6oZUv4YpsQhAl6WABNMxdevTmF7tqRqi1vCYgfbra0LhP");

        printTestCase(2, "Delete not existing Termin by ID");
        database.deleteTermin("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        printTestEnd("Delete termins function");

        return database;
    }

    private Database testGetTermine(Database database) {
        // Test ID: TID1003
        // Name:
        // Input:
        // erwartete Ausgabe:
        // Ergebnis:

        printTestStart("Get termins function");

        printTestCase(1, "Get Termins");

        TerminListe termine = database.getTermine();

        if (termine != null) {
            System.out.println("List termine size: " + termine.getTermine().size());
            int counter = 0;
            for (Termin termin : termine.getTermine())
            {
                printTestLine();
                System.out.println("Termin " + counter + ": ");
                System.out.println(termin.getTitle());
                System.out.println(termin.getType());
                printTestLine();

                counter++;
            }
        }
        else {
            System.out.println("List Termine ist NULL");
        }

        printTestEnd("Get termins function");

        return database;
    }

    private Database testAddTermine(Database database) {
        // Test ID: TID1004
        // Name:
        // Input:
        // erwartete Ausgabe:
        // Ergebnis:

        printTestStart("Add termins function");
        LocalDateTime lt = LocalDateTime.now();

        printTestCase(1, "Add Termin without ID");
        database.addTermin("", "Mehrere Tage", lt, lt,"AT", 1,"Oleksandr Kamenskyi");

        printTestCase(2, "Add Termin with ID");
        database.addTermin("fuitw6oZUv4YpsQhAl6WABNMxdevTmF7tqRqi1vCYgfbra0LhP", "Mehrere Tage", lt, lt,"AT", 1,"Oleksandr Kamenskyi");

        printTestCase(3, "Add Termin with existing ID");
        database.addTermin("fuitw6oZUv4YpsQhAl6WABNMxdevTmF7tqRqi1vCYgfbra0LhP", "Mehrere Tage", lt, lt,"AT", 1, "Oleksandr Kamenskyi");

        printTestEnd("Add termins function");

        return database;
    }

    private Database testCreateDatabase() {
        // Test ID: TID1005
        // Name:
        // Input:
        // erwartete Ausgabe:
        // Ergebnis:

        printTestStart("Database creation/connection");

        Database database = null;
        try {
            database = new Database();
        } catch (WrongPathException | SQLPackageException e) {
            throw new RuntimeException(e);
        }
        printTestEnd("Database creation/connection");
        return database;
    }

    private Database testCreateDatabaseWithPath(String projectPath, String databaseName) {
        // Test ID: TID1006
        // Name:
        // Input:
        // erwartete Ausgabe:
        // Ergebnis:

        printTestStart("Database creation/connection");

        Database database = null;
        try {
            database = new Database(projectPath, databaseName);
        } catch (WrongPathException | SQLPackageException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        printTestEnd("Database creation/connection");
        return database;
    }

    public void printTestStart(String msg) {
        // Test ID: TID1007
        // Name:
        // Input:
        // erwartete Ausgabe:
        // Ergebnis:

        printTestLine();
        printTestLine();
        System.out.println("Test started: " + msg);
        printTestLine();
        printTestLine();
        System.out.println("\n");
    }

    public void printTestEnd(String msg) {
        // Test ID: TID1008
        // Name:
        // Input:
        // erwartete Ausgabe:
        // Ergebnis:

        printTestLine();
        printTestLine();
        System.out.println("Test ended: " + msg);
        printTestLine();
        printTestLine();
        System.out.println("\n");
    }

    public void printTestCase(Integer num, String description) {
        // Test ID: TID1009
        // Name:
        // Input:
        // erwartete Ausgabe:
        // Ergebnis:

        printTestLine();
        System.out.println("Test case " + num + ": " + description);
        printTestLine();
        System.out.println("\n");
    }

    public void printTestLine() {
        System.out.print("-------------------\n");
    }
    // Test ID: TID1010
    // Name:
    // Input:
    // erwartete Ausgabe:
    // Ergebnis:

}

