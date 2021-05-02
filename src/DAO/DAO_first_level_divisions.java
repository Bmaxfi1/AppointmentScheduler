package DAO;

import DBConnectionClasses.DBConnection;
import DBConnectionClasses.DBQuery;
import Model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO_first_level_divisions {
    private static final String selectAllString = "SELECT * FROM first_level_divisions";

    public static String getFirstLevelDivisionName(int divisionIdToFind) throws SQLException {
        String firstLevelDivision;

        Connection connection = DBConnection.getConnection();
        DBQuery.setPreparedStatement(connection, selectAllString);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.execute();
        ResultSet rs = ps.getResultSet();
        while (rs.next()) {
            int divisionId = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");
            int countryId = rs.getInt("COUNTRY_ID");

            if (divisionId == divisionIdToFind) {
                return divisionName;
            }
        }
        return null;
    }

    public static int getCountryId(int divisionIdToFind) throws SQLException {
        Connection connection = DBConnection.getConnection();
        DBQuery.setPreparedStatement(connection, selectAllString);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.execute();
        ResultSet rs = ps.getResultSet();
        while (rs.next()) {
            int countryId = rs.getInt("COUNTRY_ID");
            int divisionId = rs.getInt("Division_ID");
            if (divisionId == divisionIdToFind) {
                return countryId;
            }
        }
        return -1;
    }

    public static ObservableList<String> getFirstLevelDivisionList(int selectedCountryId) throws SQLException {
        ObservableList<String> firstLevelDivisionsList = FXCollections.observableArrayList();

        Connection connection = DBConnection.getConnection();
        DBQuery.setPreparedStatement(connection, selectAllString);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.execute();
        ResultSet rs = ps.getResultSet();
        while (rs.next()) {
            int divisionId = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");
            int countryId = rs.getInt("COUNTRY_ID");
            if (countryId == selectedCountryId) {
                firstLevelDivisionsList.add(divisionName);
            }
        }
        return firstLevelDivisionsList;
    }

}
