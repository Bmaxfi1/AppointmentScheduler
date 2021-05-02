package DBConnectionClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

//utilityClass
public class DBQuery {
    private static Statement statement; //statement reference
    private static PreparedStatement preparedStatement;

    //create statement object
    public static void setStatement(Connection connection) throws SQLException {
        statement = connection.createStatement();
    }

    //return statement object
    public static Statement getStatement() {
        return statement;
    }

    public static void setPreparedStatement(Connection conn, String sqlStatement) throws SQLException {
        preparedStatement = conn.prepareStatement(sqlStatement);
    }

    public static PreparedStatement getPreparedStatement(){
        return preparedStatement;
    }
}
/*
//SQL Statement examples

Connection connection = DBConnection.getConnection();

DBQuery.setStatement(connection);  //Create statement object
Statement statement = DBQuery.getStatement();  //get statement reference

//Raw SQL statement
String insertStatement = "INSERT INTO countries(country, Create_Date, Created_By, Last_Updated_By) VALUES('US', '2021-05-01 00:00:00', 'admin', 'admin')";

//Variable Insert
String insertStatement;
String countryName = "Canada";
String createDate = "2021-05-01 00:00:00";
String createdBy = "admin";
String lastUpdateBy = "admin";
insertStatement = "INSERT INTO countries(country, Create_Date, Created_By, Last_Updated_By)" +
        "VALUES(" +
        "'" + countryName +"'," +
        "'" + createDate +"'," +
        "'" + createdBy +"'," +
        "'" + lastUpdateBy + "'" +
        ")";

//Update statement
String updateStatement = "UPDATE countries SET Country = 'Japan' WHERE Country = 'Canada'";

//Delete statement
String deleteStatement = "DELETE FROM countries WHERE Country_ID = '251'";

//Execute SQL statement
statement.execute(insertStatement);
statement.execute(updateStatement);
statement.execute(deleteStatement);

//confirm rows affected
        if(statement.getUpdateCount() > 0)
                System.out.println(statement.getUpdateCount() + "row(s) affected.");  //single row needs no braces
                else
                System.out.println("No change.");

/////////////////////////////////////////////////////////////////////////
//ResultSet object - contains a collection of objects that map records from a database table or multiple tables
//provides methods for scrolling through it. next(), previous(), first(), last(), etc
//manages the cursor.

//getResultSet() should be called by a statement reference if the execute() method returns true.

//ResultSet get() methods.  these convert between Java data types and SQL data types. (ex. getString() = String = CHAR, VARCHAR)


             Connection connection = DBConnection.getConnection();
             DBQuery.setStatement(connection);
             Statement statement = DBQuery.getStatement();

             String selectStatement = "SELECT * FROM countries";
             statement.execute(selectStatement);
             ResultSet rs = statement.getResultSet(); //has values from prev execute line
                while(rs.next()) { //.next() returns false if there is no next value
                int countryId = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                LocalDate createDate = rs.getDate("Create_Date").toLocalDate();
                LocalTime time = rs.getTime("Create_Date").toLocalTime();
                String createdBy = rs.getString("Created_By");
                LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdatedBy = rs.getString("Last_Updated_By");

                System.out.println(countryId + " " + countryName + " " + " " + createDate + " " + time + " " + createdBy + " " + lastUpdate + " " + lastUpdatedBy);
                }
//////////////////////////////////////////////////////////////////////
//prepared statement

PreparedStatement is a subclass of the Statement class.
PreparedStatement can accept raw SQL statements in Java without the need for concatenation, etc.
Creating a PreparedStatement object - prepareStatement()
accepts a String argument representing a SQL statement to be executed
like the createStatement(), the prepareStatement() method must be called by a connection reference


//        //select statement
//        String countryName = "Canada";
//        String selectStatement = "SELECT * FROM countries WHERE Country = ?";
//        DBQuery.setPreparedStatement(connection, selectStatement);
//        PreparedStatement ps = DBQuery.getPreparedStatement();
//        //key value mapping
//        ps.setString(1, countryName);
//        //execute prepared statement
//        ps.execute();
//        if (ps.getUpdateCount() > 0) {
//            System.out.println(ps.getUpdateCount() + " row(s) affected.");
//        } else {
//            System.out.println("no rows affected.");
//            ResultSet rs = ps.getResultSet(); //has values from prev execute line
//            while (rs.next()) { //.next() returns false if there is no next value
//                int countryId = rs.getInt("Country_ID");
//                String countryNameResult = rs.getString("Country");
//                LocalDate createDate = rs.getDate("Create_Date").toLocalDate();
//                LocalTime time = rs.getTime("Create_Date").toLocalTime();
//                String createdBy = rs.getString("Created_By");
//                LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
//                String lastUpdatedBy = rs.getString("Last_Updated_By");
//
//                System.out.println(countryId + " " + countryNameResult + " " + " " + createDate + " " + time + " " + createdBy + " " + lastUpdate + " " + lastUpdatedBy);
//            }
//        }

//        //delete statement
//        String countryName = "'Australia'";
//        String deleteStatement = "DELETE FROM countries WHERE Country = ?";
//        DBQuery.setPreparedStatement(connection, deleteStatement);
//        PreparedStatement ps = DBQuery.getPreparedStatement();
//        //key value mapping
//        ps.setString(1, countryName);
//        //execute prepared statement
//        ps.execute();
//        if (ps.getUpdateCount() > 0) {
//            System.out.println(ps.getUpdateCount() + " row(s) affected.");
//        } else {
//            System.out.println("no rows affected.");
//        }


//        //update statement
//        String countryName = "UK";
//        String createdBy = "admin";
//        String updateStatement = "UPDATE countries SET Country = ?, Created_By = ? WHERE Country = ?";
//        DBQuery.setPreparedStatement(connection, updateStatement);
//        PreparedStatement ps = DBQuery.getPreparedStatement();
//        //key value mapping
//        ps.setString(1, countryName);
//        ps.setString(2, createdBy);
//        ps.setString(3, countryName);
//        //execute prepared statement
//        ps.execute();
//        if (ps.getUpdateCount() > 0) {
//            System.out.println(ps.getUpdateCount() + " row(s) affected.");
//        } else {
//            System.out.println("no rows affected.");
//        }


//        //insert statement
//        String countryName = "'Australia'"; //prepared statements can get rid of the double quotes
//        String createDate = LocalDateTime.now().toString();
//        String createdBy = "admin";
//        String lastUpdateBy = "admin";
//        String insertStatement = "INSERT INTO countries(Country, Create_Date, Created_By, Last_Updated_By) VALUES(?, ?, ?, ?)";  //notice the question marks(INDEXES START WITH 1, NOT ZERO!!!)
//        //
//        DBQuery.setPreparedStatement(connection, insertStatement); //create prepared statement
//        PreparedStatement ps = DBQuery.getPreparedStatement();
//        //key value mapping
//        ps.setString(1, countryName);
//        ps.setString(2, createDate);
//        ps.setString(3, createdBy);
//        ps.setString(4, lastUpdateBy);
//        //execute prepared statement
//        ps.execute(); //execute PreparedStatement
//
//        if(ps.getUpdateCount() > 0) {
//            System.out.println(ps.getUpdateCount() + " row(s) affected.");
//        }
//        else {
//            System.out.println("no rows affected.");
//        }


*/
