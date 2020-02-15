package smsfrompc.com.smsfrompc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Debug;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        if(!MainActivity.permissionsGranted)
        {
            Intent settingsIntent = new Intent(getApplicationContext(), SettingsActivity.class);

            startActivity(settingsIntent);
            return;
        }


        ListView contactsListView = findViewById(R.id.contactsListView);

        ArrayList<Contact> contactList = readContacts();
        ArrayList<Contact> contactListNoDups = removeDuplicates(contactList);

        ContactsListAdapter adapter = new ContactsListAdapter(this, R.layout.contacts_list_view, contactListNoDups);
        contactsListView.setAdapter(adapter);

        contactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "paspausta", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ArrayList<Contact> readContacts()
    {
        ArrayList<Contact> contactList = new ArrayList<>();
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        Contact tempContact = new Contact(name, phoneNo);
                        contactList.add(tempContact);
                    }
                    pCur.close();
                }
            }
        }
        if(cur!=null){
            cur.close();
        }
        return contactList;
    }

    private ArrayList<Contact> removeDuplicates(ArrayList<Contact> list)
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
