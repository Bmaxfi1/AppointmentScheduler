package DAO;

import javafx.collections.ObservableList;

import java.sql.SQLException;

/**
 * The DAO Interface for first level divisions
 */
public interface DAO_first_level_divisions {
    public String getFirstLevelDivisionName(int divisionIdToFind) throws SQLException;
    public int getCountryId(int divisionIdToFind) throws SQLException;
    public ObservableList<String> getFirstLevelDivisionList(int selectedCountryId) throws SQLException;
    public int getFirstLevelDivisionId(String nameToFind) throws SQLException;
}
