package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * The CountryList class is used to hold and interact with the static member countryList
 */
public class CountryList {
    private static ObservableList<Country> countryList = FXCollections.observableArrayList();

    /**
     *
     * @param countryToAdd the country object to add to the static countryList
     */
    public static void addCountry(Country countryToAdd) {
        countryList.add(countryToAdd);
    }

    /**
     *
     * @param listToAdd the list of the countries to add to the static countryList
     */
    public static void addCountryList(ObservableList<Country> listToAdd) {
        for (Country country: listToAdd) {
            addCountry(country);
        }
    }

    /**
     *
     * @return the static countryList
     */
    public static ObservableList<Country> getCountryList() {return countryList;}

    /**
     *
     * @return a list of strings containing all names in the static countryList
     */
    public static ObservableList<String> getCountryNames() {
        ObservableList<String> countryNames = FXCollections.observableArrayList();
        for (Country country : countryList)
        {
            countryNames.add(country.getCountryName());
        }
        return countryNames;
    }

}
