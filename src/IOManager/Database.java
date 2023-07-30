package IOManager;

import Calendar.Termin;
import Calendar.TerminListe;
import IDgen.IDGenerator;
import IOManager.Exceptions.NullConnectionException;
import IOManager.Exceptions.SQLCommandException;
import IOManager.Exceptions.SQLPackageException;
import IOManager.Exceptions.WrongPathException;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Bei dieser Klasse handelt es sich um einen Database Manager.
 * *
 * @author Oleksandr Kamenskyi
 * @version 1.1.2
 * @since 14.05.2023
 * Letzte Änderung: 19.06.2023
 */

// Notes
// add UNIX/Win var

public class Database {
    // public Connection conn = null;
    private String projectPath;
    private String databaseName;

    /**
     * Standardkonstruktor für die Erstellung einer Datenbankverbindung.
     * Die Datenbank wird im Projektverzeichnis unter dem Namen "TimeWise.db" erwartet.
     *
     * @throws WrongPathException wenn der Pfad zur Datenbank nicht korrekt ist
     * @throws SQLPackageException wenn das SQL-Paket nicht gefunden wird
     */
    public Database() throws WrongPathException, SQLPackageException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new SQLPackageException("No SQL Package found", e);
        }

        this.projectPath = System.getProperty("user.dir");
        this.databaseName = "/TimeWise.db";

        try {
            String dbPath = "jdbc:sqlite:" +  this.projectPath + this.databaseName;
            Connection connection = DriverManager.getConnection(dbPath);
            if (connection != null) {
                DatabaseMetaData meta = connection.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                createTables(connection);
                connection.close();
            }
            else {
                throw new NullConnectionException("Connection is null");
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new WrongPathException("Wrong DB path");
        }
        System.out.println("Opened database successfully");
    }

    /**
     * Konstruktor für die Erstellung einer Datenbankverbindung mit einem angegebenen Pfad und Datenbanknamen.
     *
     * @param projectPath Pfad zur Datenbank
     * @param databaseName Name der Datenbank (inklusive Dateiendung)
     *
     * @throws WrongPathException wenn der Pfad zur Datenbank nicht korrekt ist
     * @throws SQLPackageException wenn das SQL-Paket nicht gefunden wird
     */
    public Database(String projectPath, String databaseName) throws WrongPathException, SQLPackageException {
        this.projectPath = projectPath;
        this.databaseName = databaseName;
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new SQLPackageException("No SQL Package found", e);
        }
        try {
            String dbPath = "jdbc:sqlite:" +  this.projectPath + this.databaseName;
            Connection connection = DriverManager.getConnection(dbPath);
            if (connection != null) {
                DatabaseMetaData meta = connection.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                createTables(connection);
                connection.close();
            }
            else {
                throw new NullConnectionException("Connection is null");
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new WrongPathException("Wrong DB path");
        }
        System.out.println("Opened database successfully");
    }

    /**
     *  DB Interface
     */
    /**
     * Fügt einen Termin zur Datenbank hinzu. Generiert eine ID für den Termin, falls keine angegeben ist.
     *
     * @param id ID des Termins, leerer String wenn automatisch generiert
     * @param title Titel des Termins
     * @param start Startzeitpunkt des Termins
     * @param end Endzeitpunkt des Termins
     * @param type Typ des Termins
     * @param multiday Anzahl der Tage, über die sich der Termin erstreckt
     * @param participants Teilnehmer des Termins
     */
    public void addTermin(String id, String title, LocalDateTime start, LocalDateTime end, String type, Integer multiday, String participants) {
        String startStr = convertToSQLiteDateTime(start);
        String endStr = convertToSQLiteDateTime(end);

        if (id == "") {
            id = IDGenerator.generateID(50);
        }

        String sql = String.format("INSERT INTO Termine (ID, Titel, Start, End, Type, MultiDay, Participants) VALUES ('%s','%s', '%s', '%s', '%s', '%d', '%s')", id, title, startStr, endStr,  type, multiday, participants);
        System.out.println(sql);
        try {
            executeUpdateSQL(sql);
        } catch (SQLCommandException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    /**
     * Fügt einen neuen Termin zur Datenbank hinzu. Wenn die ID des Termins leer ist,
     * wird eine neue ID automatisch generiert.
     *
     * @param termin Das Terminobjekt, das hinzugefügt werden soll.
     */
    public void addTermin(Termin termin) {
        String startStr = convertToSQLiteDateTime(termin.getStart());
        String endStr = convertToSQLiteDateTime(termin.getEnd());
        String id = termin.getId();
        String title = termin.getTitle();
        String type = termin.getType();
        String participants = "";
        Integer multiday = termin.isMultiDay()? 1 : 0;

        if (id == "") {
            id = IDGenerator.generateID(50);
        }

        String sql = String.format("INSERT INTO Termine (ID, Titel, Start, End, Type, MultiDay, Participants) VALUES ('%s','%s', '%s', '%s', '%s', '%d', '%s')", id, title, startStr, endStr,  type, multiday, participants);
        System.out.println(sql);
        try {
            executeUpdateSQL(sql);
        } catch (SQLCommandException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    /**
     * Löscht einen Termin aus der Datenbank anhand seiner ID.
     *
     * @param id Die ID des Termins, der gelöscht werden soll.
     */
    public void deleteTermin(String id) {

        String sql = String.format("DELETE FROM termine WHERE id='%s';", id);

        try {
            executeUpdateSQL(sql);
        } catch (SQLCommandException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

    }
    /**
     * Gibt eine Liste aller Termine in der Datenbank zurück.
     *
     * @return Eine TerminListe mit allen Terminen. Gibt null zurück, wenn ein Fehler auftritt.
     */
    public TerminListe getTermine() {
        String sql = "SELECT * FROM termine;";
        try {
            TerminListe termins = executeQueryTermine(sql);
            return termins;
        } catch (SQLCommandException | NullConnectionException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }


    /**
     *  DB Utils
     */

    /**
     * Erstellt die Tabelle "termine" in der Datenbank, falls diese noch nicht existiert.
     *
     * @param connection Eine Verbindung zu der Datenbank.
     * @throws SQLCommandException Wenn ein SQL-Fehler auftritt.
     */
    private void createTables(Connection connection) throws SQLCommandException {
        Statement statement =  null;

        try {
            // Create Statement
            statement = connection.createStatement();

            // Create Tables
            statement.executeUpdate(getTableTermine());

            // Close Statement
            statement.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            throw new SQLCommandException("Error while creating a table", e);
        }
        System.out.println("Table Termine successfully");
    }
    /**
     * Gibt den SQL-Befehl als String zurück, um die Tabelle "termine" zu erstellen.
     *
     * @return SQL-Befehl als String.
     */
    private String getTableTermine()
    {
        String sql = "CREATE TABLE IF NOT EXISTS termine" +
                "(ID TEXT PRIMARY   KEY     NOT NULL," +
                " Titel             TEXT    NOT NULL, " +
                " Start             TEXT," +
                " End               TEXT," +
                " Type              TEXT," +
                " MultiDay          INTEGER," +                    // 0 or 1
                " Participants      TEXT)";
        return sql;
    }
    /**
     * Führt ein SQL-Update aus. Ein Update umfasst die Befehle INSERT, UPDATE und DELETE.
     *
     * @param sql Der SQL-Befehl, der ausgeführt werden soll.
     * @throws SQLCommandException Wenn ein SQL-Fehler auftritt.
     */
    private void executeUpdateSQL(String sql) throws SQLCommandException {

        try {
            String dbPath = "jdbc:sqlite:" +  this.projectPath + this.databaseName;
            Connection connection = DriverManager.getConnection(dbPath);
            if (connection != null) {
                // Create Statement
                Statement statement = connection.createStatement();

                // Create Tables
                statement.executeUpdate(sql);

                statement.close();
                // connection.commit();  // Delete when DB in auto-commit mode
                connection.close();
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new SQLCommandException("Error while executing SQL Update", e);
        }
    }

//    private ResultSet executeQuerySQL(String sql) {
//        ResultSet resultSet = null;
//        try {
//            String dbPath = "jdbc:sqlite:" +  this.projectPath + this.databaseName;
//            Connection connection = DriverManager.getConnection(dbPath);
//            if (connection != null) {
//                // Create Statement
//                Statement statement = connection.createStatement();
//                // Create Tables
//                resultSet = statement.executeQuery(sql);
//                statement.close();
//                // connection.commit();  // Dselete when DB in auto-commit mode
//                connection.close();
//                return resultSet;
//            }
//        } catch (Exception e) {
//            System.err.println(e.getClass().getName() + ": " + e.getMessage());
//            System.exit(0);
//        }
//        return null;
//    }
    /**
     * Führt eine SQL-Abfrage aus und gibt die resultierenden Termine als eine TerminListe zurück.
     *
     * @param sql Der SQL-Befehl, der ausgeführt werden soll.
     * @throws NullConnectionException Wenn die Verbindung zur Datenbank null ist.
     * @throws SQLCommandException Wenn ein SQL-Fehler auftritt.
     * @return Eine Liste von Terminen, die die Ergebnisse der SQL-Abfrage darstellt.
     */
    private TerminListe executeQueryTermine(String sql) throws NullConnectionException, SQLCommandException {
        ResultSet resultSet = null;
        TerminListe termins = new TerminListe();
        try {
            String dbPath = "jdbc:sqlite:" +  this.projectPath + this.databaseName;
            Connection connection = DriverManager.getConnection(dbPath);
            if (connection != null) {
                // Create Statement
                Statement statement = connection.createStatement();
                // Create Tables
                resultSet = statement.executeQuery(sql);
                while (resultSet.next())
                {
                    String titel = resultSet.getString("Titel");
                    LocalDateTime start = convertToJavaDateTime(resultSet.getString("Start"));
                    LocalDateTime end = convertToJavaDateTime(resultSet.getString("End"));
                    String terminTyp = resultSet.getString("Type");
                    Integer multiday = resultSet.getInt("Multiday");
                    String participants = resultSet.getString("Participants");

                    Termin termin = new Termin(titel, terminTyp, false, start, end);
                    termins.addTermin(termin);
                }
                statement.close();
                // connection.commit();  // Dselete when DB in auto-commit mode
                connection.close();
                return termins;
            }
            else {
                throw new NullConnectionException("Connection is null");
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new SQLCommandException("Error while executing SQL Querry", e);
        }
    }

    /**
     *  Format converters
     */

    /**
     * Konvertiert ein LocalDateTime-Objekt in einen SQLite DateTime String.
     *
     * @param dateTime Das LocalDateTime-Objekt, das konvertiert werden soll.
     * @return Ein String, der das formatierte Datum und die Uhrzeit für SQLite darstellt.
     */
    public static String convertToSQLiteDateTime(LocalDateTime dateTime) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return dateTime.format(formatter);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }
    /**
     * Konvertiert einen SQLite DateTime String in ein LocalDateTime-Objekt.
     *
     * @param sqliteDateTime Der SQLite DateTime String, der konvertiert werden soll.
     * @return Ein LocalDateTime-Objekt, das das Datum und die Uhrzeit aus dem SQLite String darstellt.
     */
    public static LocalDateTime convertToJavaDateTime(String sqliteDateTime) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return LocalDateTime.parse(sqliteDateTime, formatter);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    /**
     *  Getters und Setters
     */

    /**
     * Gibt den Pfad des aktuellen Projekts zurück.
     *
     * @return Der Pfad des aktuellen Projekts.
     */
    public String getProjectPath() {
        return this.projectPath;
    }
    /**
     * Gibt den Namen der Datenbank zurück.
     *
     * @return Der Name der Datenbank.
     */
    public String getDatabaseName() {
        return this.databaseName;
    }
    /**
     * Setzt den Pfad des aktuellen Projekts.
     *
     * @param projectPath Der zu setzende Pfad.
     */
    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }
    /**
     * Setzt den Namen der Datenbank.
     *
     * @param databaseName Der zu setzende Name der Datenbank.
     */
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
}




