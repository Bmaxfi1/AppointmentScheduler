package Model;

import javafx.collections.ObservableList;

public class Country {
    private int countryId;
    private String countryName;
    private ObservableList<String> firstLevelDivisions;

    public Country(int countryId, String countryName, ObservableList<String> firstLevelDivisions) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.firstLevelDivisions = firstLevelDivisions;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public ObservableList<String> getFirstLevelDivisions() {
        return firstLevelDivisions;
    }

    public void setFirstLevelDivisions(ObservableList<String> firstLevelDivisions) {
        this.firstLevelDivisions = firstLevelDivisions;
    }

    public String toString() {
        return this.countryName;
    }
}
