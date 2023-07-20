package IOManager;

import Calendar.Termin;
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
 * Autor: Oleksandr Kamenskyi
 * Version: 1.1.2
 * Erstellt am: 14.05.2023
 * Letzte Änderung: 19.06.2023
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
     * Konstruktor für die Erstellung eines Database Connections.
     *
     * @param projectPath Path bis zum Datenbank
     * @param databaseName Name der Datenbank mit Datentyp
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

    public void deleteTermin(String id) {

        String sql = String.format("DELETE FROM termine WHERE id='%s';", id);

        try {
            executeUpdateSQL(sql);
        } catch (SQLCommandException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

    }

    public List<Termin> getTermine() {
        String sql = "SELECT * FROM termine;";
        try {
            List<Termin> termins = executeQueryTermine(sql);
            return termins;
        } catch (SQLCommandException | NullConnectionException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    /**
     *  DB Utils
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

    private List<Termin> executeQueryTermine(String sql) throws NullConnectionException, SQLCommandException {
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
                    String terminTyp = resultSet.getString("Type");
                    Integer multiday = resultSet.getInt("Multiday");
                    String participants = resultSet.getString("Participants");

                    Termin termin = new Termin(titel, terminTyp, false, start, end, start, end);
                    termins.add(termin);
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

    public static String convertToSQLiteDateTime(LocalDateTime dateTime) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return dateTime.format(formatter);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

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
}




