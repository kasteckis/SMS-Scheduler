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

import smsfrompc.com.smsfrompc.Activities.MainActivity;
import smsfrompc.com.smsfrompc.Entities.Classes.HistoryMessage;
import smsfrompc.com.smsfrompc.Entities.Classes.Setting;
import smsfrompc.com.smsfrompc.R;

public class ScheduleSmsActivity extends AppCompatActivity {

    TextView recipientName = null;
    TextView recipientNumber = null;
    SmsManager smsManager;
    Button scheduleSmsBtn;

    EditText editTextTime;
    EditText editText;

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
        editText = findViewById(R.id.messageText);

        switch(Setting.ScheduleFormatSetting.getSettingValue()) {
            case "seconds":
                editTextTime.setHint(getResources().getString(R.string.datetime_default_seconds));
                setScheduledTimeFromHistory();
                delayTimeMultiplier = 1000;
                break;
            case "minutes":
                editTextTime.setHint(getResources().getString(R.string.datetime_default_minutes));
                setScheduledTimeFromHistory();
                delayTimeMultiplier = 1000 * 60;
                break;
            default:
                throw new RuntimeException("Undefined ScheduleFormatSetting flag");
        }

        recipientName.setText(getIntent().getStringExtra("EXTRA_NAME"));
        recipientNumber.setText(getIntent().getStringExtra("EXTRA_NUMBER"));
        editText.setText(getIntent().getStringExtra("EXTRA_TEXT"));

        smsManager = SmsManager.getDefault();

        scheduleSmsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(ScheduleSmsActivity.this, getResources().getString(R.string.message_successfully_scheduled), Toast.LENGTH_SHORT).show();
                String tempNumber = (String) recipientNumber.getText();
                String tempText = editText.getText().toString();
                int delayTimeMs = Integer.parseInt(editTextTime.getText().toString());
                delayTimeMs = delayTimeMs * delayTimeMultiplier;

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();

                HistoryMessage historyMessage = new HistoryMessage(
                        recipientName.getText().toString(),
                        recipientNumber.getText().toString(),
                        Integer.toString(delayTimeMs),
                        Setting.ScheduleFormatSetting.getSettingValue(),
                        dateFormat.format(date),
                        tempText
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
                        MainActivity.myAppDatabase.historyMessageDao().addHistoryMessage(historyMessage);
                    }
                },
                delayTimeMs
        );
    }

    private void setScheduledTimeFromHistory()
    {
        String delayTime = getIntent().getStringExtra("EXTRA_DELAY");
        String scheduleFormat = getIntent().getStringExtra("EXTRA_SCHEDULE_FORMAT");

        if(delayTime == null || scheduleFormat == null) {
            return;
        }

        int delayTimeInt = Integer.parseInt(delayTime) / 1000;

        switch(Setting.ScheduleFormatSetting.getSettingValue()) {
            case "seconds":
                editTextTime.setText(Integer.toString(delayTimeInt));
                break;
            case "minutes":
                delayTimeInt = delayTimeInt / 60;
                if(delayTimeInt > 0)
                {
                    editTextTime.setText(Integer.toString(delayTimeInt));
                }
                break;
            default:
                throw new RuntimeException("Undefined ScheduleFormatSetting flag");
        }
    }
}
