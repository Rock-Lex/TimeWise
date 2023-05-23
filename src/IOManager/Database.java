package IOManager;

import java.sql.*;


/**
 * Bei dieser Klasse handelt es sich um einen Database Manager.
 * *
 * Autor: Oleksandr Kamenskyi
 * Version: 1.0.0
 * Erstellt am: 14.05.2023
 * Letzte Änderung: 22.05.2023
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
            System.out.println(dbPath);
            Connection connection = DriverManager.getConnection(dbPath);
            if (connection != null) {
                DatabaseMetaData meta = connection.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
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
            System.out.println(dbPath);
            Connection connection = DriverManager.getConnection(dbPath);
            if (connection != null) {
                DatabaseMetaData meta = connection.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                connection.close();
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public void createTables() {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + this.projectPath + this.databaseName);
            System.out.println("Opened database successfully");

            statement = connection.createStatement();

            // Template
            String sql = "CREATE TABLE COMPANY " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " AGE            INT     NOT NULL, " +
                    " ADDRESS        CHAR(50), " +
                    " SALARY         REAL)";
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
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




