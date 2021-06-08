package DAOImpl;

import DAO.DAO_users;
import DBConnectionClasses.DBConnection;
import DBConnectionClasses.DBQuery;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOImpl_users implements DAO_users {
    private static final String selectUserString = "SELECT User_Name, Password, salt FROM users";


    @Override
    public boolean credentialsValid(String userName, String p) throws SQLException, NoSuchAlgorithmException {
        Connection connection = DBConnection.getConnection();
        DBQuery.setPreparedStatement(connection, selectUserString);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        ps.execute();
        ResultSet rs = ps.getResultSet();



        while (rs.next()) {
            System.out.println(rs.getString("User_Name"));
            System.out.println(rs.getString("Password"));
            System.out.println(rs.getString("salt"));

            String salt = rs.getString("salt");
            String saltedPw = salt + p;

            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(saltedPw.getBytes(StandardCharsets.UTF_8));
            String sha1pw = String.format("%040x", new BigInteger(1, crypt.digest()));

            System.out.println(sha1pw);

            if (rs.getString("User_Name").equals(userName)) {
                if (rs.getString("Password").equals(sha1pw)) {
                    return true;
                }
            }
        }
        return false;

    }
}
