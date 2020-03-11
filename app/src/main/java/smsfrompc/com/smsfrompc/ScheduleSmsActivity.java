package smsfrompc.com.smsfrompc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

                tempNumber = "+3706000";

                sendMessage(tempNumber, tempText, delayTimeMs);
            }
        });
    }

    private void sendMessage(String number, String text, int delayTimeMs)
    {
        plainRecipientNumber = number;
        plainRecipientText = text;
        delayTimeInMs = delayTimeMs;
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        smsManager.sendTextMessage(plainRecipientNumber, null, plainRecipientText, null, null);
                    }
                },
                delayTimeMs
        );
    }
}
