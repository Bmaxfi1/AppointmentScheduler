package DAO;

import Model.Country;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface DAO_countries {
    public String getCountryName(int countryIdToFind) throws SQLException;
    public ObservableList<Country> getAllCountries() throws SQLException;

}
