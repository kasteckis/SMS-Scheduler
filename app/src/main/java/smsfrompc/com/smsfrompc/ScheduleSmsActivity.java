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

    String plainRecipientNumber = null;
    String plainRecipientText = null;
    int delayTimeInMs = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_sms);

        recipientName = findViewById(R.id.nameTextView);
        recipientNumber = findViewById(R.id.numberTextView);
        scheduleSmsBtn = findViewById(R.id.scheduleSmsBtn);

        recipientName.setText(getIntent().getStringExtra("EXTRA_NAME"));
        recipientNumber.setText(getIntent().getStringExtra("EXTRA_NUMBER"));

        smsManager = SmsManager.getDefault();

        scheduleSmsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(ScheduleSmsActivity.this, "Your message is successfully scheduled!", Toast.LENGTH_SHORT).show();
                String tempNumber = (String) recipientNumber.getText();
                EditText tempEditText = findViewById(R.id.messageText);
                String tempText = tempEditText.getText().toString();
                EditText tempEditTextTime = findViewById(R.id.scheduledTime);
                int delayTimeMs = Integer.parseInt(tempEditTextTime.getText().toString());
                delayTimeMs = delayTimeMs * 1000;

                //tempNumber = "+3706000";

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
