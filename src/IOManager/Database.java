package IOManager;

import Calendar.Termin;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Bei dieser Klasse handelt es sich um einen Database Manager.
 * *
 * Autor: Oleksandr Kamenskyi
 * Version: 1.0.0
 * Erstellt am: 14.05.2023
 * Letzte Änderung: 24.05.2023
 */

// Notes
// add UNIX/Win var

public class Database {
    // public Connection conn = null;
    private String projectPath;
    private String databaseName;

    /**
     * Konstruktor für die Erstellung eines Database Connections.
     */
    public Database() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    /**
     * Konstruktor für die Erstellung eines Database Connections.
     *
     * @param projectPath Path bis zum Datenbank
     * @param databaseName Name der Datenbank mit Datentyp
     */
    public Database(String projectPath, String databaseName) {
        this.projectPath = projectPath;
        this.databaseName = databaseName;
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    private void createTables(Connection connection) {
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
            System.exit(0);
        }
        System.out.println("Table Termine successfully");
    }

    private String getTableTermine()
    {
        String sql = "CREATE TABLE IF NOT EXISTS termine" +
                "(ID INTEGER PRIMARY KEY     NOT NULL," +
                " Titel          TEXT    NOT NULL, " +
                " Start          TEXT," +
                " End            TEXT," +
                " TerminTyp      TEXT, " +
                " Participants   TEXT)";
        return sql;
    }

    private void executeUpdateSQL(String sql) {

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
            System.exit(0);
        }
    }

    private ResultSet executeQuerySQL(String sql) {
        ResultSet resultSet = null;
        try {
            String dbPath = "jdbc:sqlite:" +  this.projectPath + this.databaseName;
            Connection connection = DriverManager.getConnection(dbPath);
            if (connection != null) {
                // Create Statement
                Statement statement = connection.createStatement();
                // Create Tables
                resultSet = statement.executeQuery(sql);
//                while (resultSet.next())
//                {
//                    System.out.println(resultSet.getString("Titel"));
//                }
                statement.close();
                // connection.commit();  // Dselete when DB in auto-commit mode
                connection.close();
                return resultSet;
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }

    private List<Termin> executeQueryTermine(String sql) {
        ResultSet resultSet = null;
        List<Termin> termins = new ArrayList<>();
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
                    String start = resultSet.getString("Start");
                    String end = resultSet.getString("End");
                    String terminTyp = resultSet.getString("TerminTyp");
                    String participants = resultSet.getString("Participants");

                    Termin termin = new Termin(titel, terminTyp, false, start, end, start, end);
                    termins.add(termin);
                }
                statement.close();
                // connection.commit();  // Dselete when DB in auto-commit mode
                connection.close();
                return termins;
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }

    /**
     *  DB Interface
     */

    public void addTermin(String title, LocalDateTime startDate, LocalDateTime endDate, String terminTyp, String participants) {
        String start = convertToSQLiteDateTime(startDate);
        String end = convertToSQLiteDateTime(endDate);
        String sql = String.format("INSERT INTO Termine (Titel, Start, End, TerminTyp, Participants) VALUES ('%s', '%s', '%s', '%s', '%s')", title, start, end,  terminTyp, participants);
        executeUpdateSQL(sql);
    }

    public void deleteTermin(Integer id) {

        String sql = String.format("DELETE FROM termine WHERE id=%d;", id);
        System.out.println(sql);
        executeUpdateSQL(sql);
    }

    public List<Termin> getTermine() {
        String sql = "SELECT * FROM termine;";
        List<Termin> termins = executeQueryTermine(sql);

        return termins;
    }

    public static String convertToSQLiteDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

    public static LocalDateTime convertToJavaDateTime(String sqliteDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(sqliteDateTime, formatter);
    }

    /**
     *  Getters und Setters
     */

    public String getProjectPath() {
        return this.projectPath;
    }

    public String getDatabaseName() {
        return this.databaseName;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public List<Termin> getTerminArray() {
        Termin termin = new Termin("Ein Tag","MT",true,"2022-12-21", "2022-12-21", "12:00","13:00");
        List<Termin> ter = Arrays.asList(termin, termin, termin);

        return ter;
    }

    public void deleteTermin(Termin termin) {
        System.out.println("Termin is deleted");
        System.out.println(termin.getId());
        System.out.println(termin.getTitle());
    }

    public int compareTo(Database other) {
        return this.databaseName.compareTo(other.databaseName);
    }
}




