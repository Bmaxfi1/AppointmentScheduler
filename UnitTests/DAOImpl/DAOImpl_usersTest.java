package DAOImpl;

import DAO.DAO_users;
import DBConnectionClasses.DBConnection;

import DBConnectionClasses.DBQuery;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DAOImpl_usersTest {
    DAO_users daoUsers;
    String addTestUserString = "INSERT INTO users\n" +
            "(User_ID, User_Name, Password, salt) VALUES(0, \"coolGuy\", sha1(concat(\"s4lted\", \"Il0vepizza\")), \"s4lted\")";
    String removeTestUserString = "DELETE FROM users WHERE User_Name = \"coolGuy\"";


    @BeforeEach
    void setUp() throws SQLException {
        DBConnection.startConnection();
        Connection connection = DBConnection.getConnection();

        daoUsers = new DAOImpl_users();

        DBQuery.setPreparedStatement(connection, addTestUserString);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.execute();
    }

    @AfterEach
    void tearDown() throws SQLException {
        Connection connection = DBConnection.getConnection();

        DBQuery.setPreparedStatement(connection, removeTestUserString);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.execute();
        DBConnection.closeConnection();
    }

    @Test
    void credentialsValid() throws SQLException, NoSuchAlgorithmException {

        assertTrue(daoUsers.credentialsValid("coolGuy", "Il0vepizza"));

    }

}