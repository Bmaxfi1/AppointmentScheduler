package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ContactList {
    private static ObservableList<Contact> contactList = FXCollections.observableArrayList();

    /**
     *
     * @param contactToAdd the contact to add to the static contactList
     */
    public static void addContact(Contact contactToAdd) {
        contactList.add(contactToAdd);
    }

    /**
     *
     * @param listToAdd the list of contacts to add to the static contactList
     */
    public static void addContactList(ObservableList<Contact> listToAdd) {
        for (Contact contact: listToAdd) {
            addContact(contact);
        }
    }

    /**
     *
     * @return the static member contactList
     */
    public static ObservableList<Contact> getContactList() {
        return contactList;
    }

    /**
     *
     * @param contactId the id of the contact to find
     * @return the found contact
     */
    public static Contact getContact(int contactId) {
        for (Contact contact: ContactList.contactList){
            if (contact.getContactId() == contactId) {
                return contact;
            }
        }
        return null;
    }

    /**
     *
     * @return a list of contact names contained in the static member contactList
     */
    public static ObservableList<String> getContactNames() {
        ObservableList<String> namesOfContacts = FXCollections.observableArrayList();
        for (Contact contact: ContactList.contactList) {
            namesOfContacts.add(contact.getContactName());
        }
        return namesOfContacts;
    }

}
