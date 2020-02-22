package smsfrompc.com.smsfrompc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ScheduleSmsActivity extends AppCompatActivity {

    TextView recipientName = null;
    TextView recipientNumber = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_sms);

        recipientName = findViewById(R.id.nameTextView);
        recipientNumber = findViewById(R.id.numberTextView);

        recipientName.setText(getIntent().getStringExtra("EXTRA_NAME"));
        recipientNumber.setText(getIntent().getStringExtra("EXTRA_NUMBER"));
    }
}
