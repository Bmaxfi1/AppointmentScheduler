package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CountryList {
    private static ObservableList<Country> countryList = FXCollections.observableArrayList();

    public static void addCountry(Country countryToAdd) {
        countryList.add(countryToAdd);
    }

    public static void addCountryList(ObservableList<Country> listToAdd) {
        for (Country country: listToAdd) {
            addCountry(country);
        }
    }

    public static ObservableList<Country> getCountryList() {return countryList;}

    public static ObservableList<String> getCountryNames() {
        ObservableList<String> countryNames = FXCollections.observableArrayList();
        for (Country country : countryList)
        {
            countryNames.add(country.getCountryName());
        }
        return countryNames;
    }
}
