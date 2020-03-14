package smsfrompc.com.smsfrompc.Entities.Classes;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Contact {
    private String name;
    private String number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public static ArrayList<Contact> removeDuplicates(ArrayList<Contact> list)
    {
        ArrayList<Contact> contactListNoDups = new ArrayList<>();
        for (Contact contact : list)
        {
            boolean shouldIAddToNewList = true;
            for(Contact contactInNewList : contactListNoDups)
            {
                if(contact.getName().equals(contactInNewList.getName()) && contact.getNumber().equals(contactInNewList.getNumber()))
                {
                    shouldIAddToNewList = false;
                    break;
                }
            }
            if(shouldIAddToNewList)
                contactListNoDups.add(contact);
        }
        return contactListNoDups;
    }
}
