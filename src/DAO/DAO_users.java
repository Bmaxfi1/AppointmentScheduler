package DAO;

import Model.User;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public interface DAO_users {
    boolean credentialsValid(String userID, String password) throws SQLException, NoSuchAlgorithmException;


}
