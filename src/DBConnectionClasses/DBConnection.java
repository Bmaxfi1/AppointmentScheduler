package DBConnectionClasses;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

//the four key JDBC API Interfaces are; Driver, Connection, Statement, and ResultSet.
//Driver - establishes a database connection
//Connection - communicates with the database
//Statement - provides methods for executing SQL statements
//ResultSet holds data returned from database tables

//common methods
//createStatement()
//execute() - runs a SQL Statement (string arg).  returns true if a SELECT statement was executed, false if INSERT, UPDATE, DELETE
//getUpdateCount() - returns the number of rows affected by an INSERT, UPDATE, or DELETE command.  Only used on execute that returns false.

public class DBConnection {
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//wgudb.ucertify.com:3306/";
    private static final String dbName = "WJ07Vc0";

    private static final String jdbcURL = protocol + vendorName + ipAddress + dbName;

    private static final String MYSQLJBCDriver = "com.mysql.jdbc.Driver";

    private static final String username = "U07Vc0";
    private static Connection conn = null;

    public static Connection startConnection() {
        try {
            Class.forName(MYSQLJBCDriver);
            conn = DriverManager.getConnection(jdbcURL, username, "53689140160");

            System.out.println("Connection successful");
        } catch (SQLException e) {
            e.printStackTrace();
            //System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            //System.out.println(e.getMessage());
        }
        return conn;
    }

    public static Connection getConnection(){
        return conn;
    }

    public static void closeConnection(){
        try {
            conn.close();
        }
        catch (Exception e){
            //do nothing
        }
    }



}
