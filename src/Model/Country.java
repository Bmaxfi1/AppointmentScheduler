package Model;

import javafx.collections.ObservableList;

/**
 * The Country class defines a country object.  Customers use Countries in their addresses.
 */
public class Country {
    private int countryId;
    private String countryName;
    private ObservableList<String> firstLevelDivisions;

    public Country(int countryId, String countryName, ObservableList<String> firstLevelDivisions) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.firstLevelDivisions = firstLevelDivisions;
    }

    /**
     *
     * @return the countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     *
     * @return the list of strings containing the first level divisions for this country
     */
    public ObservableList<String> getFirstLevelDivisions() {
        return firstLevelDivisions;
    }

    /**
     * this method causes "toString()" to return the country name rather than the object address
     * @return the country name as a string
     */
    public String toString() {
        return this.countryName;
    }
}
