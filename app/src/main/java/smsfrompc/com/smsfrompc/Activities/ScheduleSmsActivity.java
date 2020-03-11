package smsfrompc.com.smsfrompc.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import smsfrompc.com.smsfrompc.Entities.Classes.HistoryMessage;
import smsfrompc.com.smsfrompc.R;

public class ScheduleSmsActivity extends AppCompatActivity {

    TextView recipientName = null;
    TextView recipientNumber = null;
    SmsManager smsManager;
    Button scheduleSmsBtn;

    EditText editTextTime;

    String plainRecipientNumber = null;
    String plainRecipientText = null;
    int delayTimeInMs = -1;
    int delayTimeMultiplier = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_sms);

        recipientName = findViewById(R.id.nameTextView);
        recipientNumber = findViewById(R.id.numberTextView);
        scheduleSmsBtn = findViewById(R.id.scheduleSmsBtn);

        editTextTime = findViewById(R.id.scheduledTime);

        switch(MainActivity.ScheduleFormatSetting.getSettingValue()) {
            case "seconds":
                editTextTime.setHint(getResources().getString(R.string.datetime_default_seconds));
                delayTimeMultiplier = 1000;
                break;
            case "minutes":
                editTextTime.setHint(getResources().getString(R.string.datetime_default_minutes));
                delayTimeMultiplier = 1000 * 60;
                break;
            default:
                throw new RuntimeException("Undefined ScheduleFormatSetting flag");
        }

        recipientName.setText(getIntent().getStringExtra("EXTRA_NAME"));
        recipientNumber.setText(getIntent().getStringExtra("EXTRA_NUMBER"));

        smsManager = SmsManager.getDefault();

        scheduleSmsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(ScheduleSmsActivity.this, getResources().getString(R.string.message_successfully_scheduled), Toast.LENGTH_SHORT).show();
                String tempNumber = (String) recipientNumber.getText();
                EditText tempEditText = findViewById(R.id.messageText);
                String tempText = tempEditText.getText().toString();
                int delayTimeMs = Integer.parseInt(editTextTime.getText().toString());
                delayTimeMs = delayTimeMs * delayTimeMultiplier;

                //tempNumber = "+3706000";
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();

                HistoryMessage historyMessage = new HistoryMessage(
                        recipientName.getText().toString(),
                        recipientNumber.getText().toString(),
                        Integer.toString(delayTimeMs),
                        MainActivity.ScheduleFormatSetting.getSettingValue(),
                        dateFormat.format(date)
                );

                sendMessage(tempNumber, tempText, delayTimeMs, historyMessage);
            }
        });
    }

    private void sendMessage(String number, String text, int delayTimeMs, final HistoryMessage historyMessage)
    {
        plainRecipientNumber = number;
        plainRecipientText = text;
        delayTimeInMs = delayTimeMs;
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        smsManager.sendTextMessage(plainRecipientNumber, null, plainRecipientText, null, null);
                        //MainActivity.myAppDatabase.historyMessageDao().addHistoryMessage(historyMessage);
                    }
                },
                delayTimeMs
        );
    }
}
