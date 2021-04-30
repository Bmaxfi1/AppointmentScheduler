package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContactList {
    private static ObservableList<Contact> contactList = FXCollections.observableArrayList();

    public static void addContact(Contact contactToAdd) {
        contactList.add(contactToAdd);
    }

    public static void addContactList(ObservableList<Contact> listToAdd) {
        for (Contact contact: listToAdd) {
            addContact(contact);
        }
    }

    public static ObservableList<Contact> getContactList() {
        return contactList;
    }

    public static Contact getContact(int contactId) {
        for (Contact contact: ContactList.contactList){
            if (contact.getContactId() == contactId) {
                return contact;
            }
        }
        return null;
    }

    public static ObservableList<String> getContactNames() {
        ObservableList<String> namesOfContacts = FXCollections.observableArrayList();
        for (Contact contact: ContactList.contactList) {
            namesOfContacts.add(contact.getContactName());
        }
        return namesOfContacts;
    }

}
