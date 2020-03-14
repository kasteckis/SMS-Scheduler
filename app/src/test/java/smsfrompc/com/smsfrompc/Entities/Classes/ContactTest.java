package smsfrompc.com.smsfrompc.Entities.Classes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ContactTest {

    ArrayList<Contact> contactsArrayList = new ArrayList<Contact>();

    @Before
    public void setUp() throws Exception {
        Contact[] contacts = {
                new Contact("Tom", "100"),
                new Contact("Tom", "100"),
                new Contact("Tom", "100"),
                new Contact("Ben", "200"),
                new Contact("Tom", "100"),
                new Contact("Tom", "100"),
                new Contact("Ben", "200"),
                new Contact("Tom", "100"),
                new Contact("Jerry", "300"),
                new Contact("Jerry", "300")
        };

        contactsArrayList.addAll(Arrays.asList(contacts));
    }

    @Test
    public void removeDuplicates() {
        contactsArrayList = Contact.removeDuplicates(contactsArrayList);
        assertEquals(3, contactsArrayList.size());
    }

    @After
    public void tearDown() throws Exception {
        contactsArrayList = null;
    }
}