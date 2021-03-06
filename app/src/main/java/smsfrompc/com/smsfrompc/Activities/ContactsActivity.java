package smsfrompc.com.smsfrompc.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import smsfrompc.com.smsfrompc.Adapters.ContactsListAdapter;
import smsfrompc.com.smsfrompc.Entities.Classes.Contact;
import smsfrompc.com.smsfrompc.Managers.PermissionManager;
import smsfrompc.com.smsfrompc.R;

public class ContactsActivity extends AppCompatActivity {

    static ArrayList<Contact> contactListNoDups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        if(!PermissionManager.permissionsGranted)
        {
            setContentView(R.layout.not_granted_permissions_layout);
            return;
        }

        ListView contactsListView = findViewById(R.id.contactsListView);

        if(contactListNoDups == null) {
            ArrayList<Contact> contactList = readContacts();
            contactListNoDups = Contact.removeDuplicates(contactList);
        }

        ContactsListAdapter adapter = new ContactsListAdapter(this, R.layout.contacts_list_view, contactListNoDups);
        contactsListView.setAdapter(adapter);

        contactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent scheduleSmsIntent = new Intent(getApplicationContext(), ScheduleSmsActivity.class);

                Contact selectedContact = contactListNoDups.get(i);
                scheduleSmsIntent.putExtra("EXTRA_NAME", selectedContact.getName());
                scheduleSmsIntent.putExtra("EXTRA_NUMBER", selectedContact.getNumber());

                startActivity(scheduleSmsIntent);
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
}
