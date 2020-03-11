package smsfrompc.com.smsfrompc.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import smsfrompc.com.smsfrompc.Adapters.ContactsListAdapter;
import smsfrompc.com.smsfrompc.Adapters.HistoryListAdapter;
import smsfrompc.com.smsfrompc.Entities.Classes.Contact;
import smsfrompc.com.smsfrompc.Entities.Classes.HistoryMessage;
import smsfrompc.com.smsfrompc.R;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ListView contactsListView = findViewById(R.id.historyListView);

        List<HistoryMessage> historyMessageArrayList = MainActivity.myAppDatabase.historyMessageDao().getAll();
        HistoryMessage a = new HistoryMessage("tadas", "112", "2min", "seconds", "2012-02-02");
        historyMessageArrayList.add(a);
        historyMessageArrayList.add(a);
        historyMessageArrayList.add(a);

        HistoryListAdapter adapter = new HistoryListAdapter(this, R.layout.history_list_view, historyMessageArrayList);
        contactsListView.setAdapter(adapter);

        contactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent scheduleSmsIntent = new Intent(getApplicationContext(), ScheduleSmsActivity.class);

//                Contact selectedContact = contactListNoDups.get(i);
//                scheduleSmsIntent.putExtra("EXTRA_NAME", selectedContact.getName());
//                scheduleSmsIntent.putExtra("EXTRA_NUMBER", selectedContact.getNumber());

                startActivity(scheduleSmsIntent);
            }
        });
    }
}
