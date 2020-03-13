package smsfrompc.com.smsfrompc.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import smsfrompc.com.smsfrompc.Adapters.HistoryListAdapter;
import smsfrompc.com.smsfrompc.Entities.Classes.HistoryMessage;
import smsfrompc.com.smsfrompc.Managers.PermissionManager;
import smsfrompc.com.smsfrompc.R;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!PermissionManager.permissionsGranted)
        {
            setContentView(R.layout.not_granted_permissions_layout);
            return;
        }

        setContentView(R.layout.activity_history);

        ListView contactsListView = findViewById(R.id.historyListView);

        final List<HistoryMessage> historyMessageArrayList = MainActivity.myAppDatabase.historyMessageDao().getAll();

        HistoryListAdapter adapter = new HistoryListAdapter(this, R.layout.history_list_view, historyMessageArrayList);
        contactsListView.setAdapter(adapter);

        contactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent scheduleSmsIntent = new Intent(getApplicationContext(), ScheduleSmsActivity.class);

                HistoryMessage selectedHistoryMessage = historyMessageArrayList.get(i);
                scheduleSmsIntent.putExtra("EXTRA_NAME", selectedHistoryMessage.getRecipientName());
                scheduleSmsIntent.putExtra("EXTRA_NUMBER", selectedHistoryMessage.getRecipientNumber());
                scheduleSmsIntent.putExtra("EXTRA_TEXT", selectedHistoryMessage.getText());
                scheduleSmsIntent.putExtra("EXTRA_DELAY", selectedHistoryMessage.getDelayTime());
                scheduleSmsIntent.putExtra("EXTRA_SCHEDULE_FORMAT", selectedHistoryMessage.getScheduleFormat());

                startActivity(scheduleSmsIntent);
            }
        });
    }
}
