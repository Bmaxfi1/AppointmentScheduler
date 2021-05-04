package DAOImpl;

import DAO.DAO_countries;
import DAO.DAO_first_level_divisions;
import DBConnectionClasses.DBConnection;
import DBConnectionClasses.DBQuery;
import Model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The Implementation of the DAO Interface
 */
public class DAOImpl_countries implements DAO_countries {
    private static final String selectAllString = "SELECT * FROM countries";

    @Override
    public String getCountryName(int countryIdToFind) throws SQLException {
        Connection connection = DBConnection.getConnection();
        DBQuery.setPreparedStatement(connection, selectAllString);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.execute();
        ResultSet rs = ps.getResultSet();
        while (rs.next()) {
            int countryId = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");
            if (countryId == countryIdToFind) {
                return countryName;
            }
        }
        return null;
    }

    @Override
    public ObservableList<Country> getAllCountries() throws SQLException{
        ObservableList<Country> allCountries = FXCollections.observableArrayList();

        DAO_first_level_divisions fldDao = new DAOImpl_first_level_divisions();

        Connection connection = DBConnection.getConnection();
        DBQuery.setPreparedStatement(connection, selectAllString);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.execute();
        ResultSet rs = ps.getResultSet();
        while (rs.next()) {
            int countryId = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");

            Country country = new Country(countryId, countryName, fldDao.getFirstLevelDivisionList(countryId));

            allCountries.add(country);
        }

        return allCountries;
    }

}
