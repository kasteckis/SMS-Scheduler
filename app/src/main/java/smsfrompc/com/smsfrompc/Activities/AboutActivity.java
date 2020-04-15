package smsfrompc.com.smsfrompc.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import smsfrompc.com.smsfrompc.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // TODO: Maybe later use some kind of API to retrieve this information from server

        String bodyPlainText = "This app lets you send delayed SMS messages to anyone in your contact list\n\nDeveloper - Valentinas Kasteckis\nLicense - MIT";

        TextView bodyTextView = findViewById(R.id.aboutBodyTextView);

        bodyTextView.setText(bodyPlainText);
    }
}
