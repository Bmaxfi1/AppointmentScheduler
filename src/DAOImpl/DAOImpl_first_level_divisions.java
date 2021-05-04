package DAOImpl;

import DAO.DAO_first_level_divisions;
import DBConnectionClasses.DBConnection;
import DBConnectionClasses.DBQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The Implementation of the DAO Interface
 */
public class DAOImpl_first_level_divisions implements DAO_first_level_divisions {
    private static final String selectAllString = "SELECT * FROM first_level_divisions";

    @Override
    public String getFirstLevelDivisionName(int divisionIdToFind) throws SQLException {
        Connection connection = DBConnection.getConnection();
        DBQuery.setPreparedStatement(connection, selectAllString);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.execute();
        ResultSet rs = ps.getResultSet();
        while (rs.next()) {
            int divisionId = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");

            if (divisionId == divisionIdToFind) {
                return divisionName;
            }
        }
        return null;
    }

    @Override
    public int getCountryId(int divisionIdToFind) throws SQLException {
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

    @Override
    public ObservableList<String> getFirstLevelDivisionList(int selectedCountryId) throws SQLException {
        ObservableList<String> firstLevelDivisionsList = FXCollections.observableArrayList();

        Connection connection = DBConnection.getConnection();
        DBQuery.setPreparedStatement(connection, selectAllString);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.execute();
        ResultSet rs = ps.getResultSet();
        while (rs.next()) {
            String divisionName = rs.getString("Division");
            int countryId = rs.getInt("COUNTRY_ID");
            if (countryId == selectedCountryId) {
                firstLevelDivisionsList.add(divisionName);
            }
        }
        return firstLevelDivisionsList;
    }

    @Override
    public int getFirstLevelDivisionId(String nameToFind) throws SQLException {
        Connection connection = DBConnection.getConnection();
        DBQuery.setPreparedStatement(connection, selectAllString);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.execute();
        ResultSet rs = ps.getResultSet();
        while (rs.next()) {
            int divisionId = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");
            if (divisionName.equals(nameToFind)) {
                return divisionId;
            }
        }
        return -1;
    }

}
