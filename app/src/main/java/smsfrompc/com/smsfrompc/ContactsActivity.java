package smsfrompc.com.smsfrompc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        ListView contactsListView = findViewById(R.id.contactsListView);
        Contact a = new Contact("random", "random");
        Contact b = new Contact("random2", "random");
        Contact c = new Contact("random3", "randomNUMBER3");

        ArrayList<Contact> contactList = new ArrayList<>();
        contactList.add(a);
        contactList.add(b);
        contactList.add(c);

        ContactsListAdapter adapter = new ContactsListAdapter(this, R.layout.contacts_list_view, contactList);
        contactsListView.setAdapter(adapter);
    }
}
