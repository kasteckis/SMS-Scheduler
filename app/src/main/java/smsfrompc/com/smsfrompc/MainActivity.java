package smsfrompc.com.smsfrompc;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static boolean permissionsGranted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                permissionsGranted = true;
                Toast.makeText(MainActivity.this, "Hey there!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                permissionsGranted = false;
                Toast.makeText(MainActivity.this, "You won't be able to use this program without granting permissions!", Toast.LENGTH_SHORT).show();
            }
        };

        TedPermission.with(MainActivity.this)
                .setPermissionListener(permissionListener)
                .setPermissions(Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS,
                        Manifest.permission.SEND_SMS, Manifest.permission.INTERNET)
                .check();

        Button settingsActivity = findViewById(R.id.settingsBtn);
        settingsActivity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent settingsIntent = new Intent(getApplicationContext(), SettingsActivity.class);

                startActivity(settingsIntent);
            }
        });

        Button contactsActivity = findViewById(R.id.delaySmsBtn);
        contactsActivity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent contactsActivity = new Intent(getApplicationContext(), ContactsActivity.class);

                startActivity(contactsActivity);
            }
        });

        Button aboutActivity = findViewById(R.id.aboutBtn);
        aboutActivity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent aboutActivityIntent = new Intent(getApplicationContext(), AboutActivity.class);

                startActivity(aboutActivityIntent);
            }
        });

        Button historyActivity = findViewById(R.id.historyBtn);
        historyActivity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent historyActivityIntent = new Intent(getApplicationContext(), HistoryActivity.class);

                startActivity(historyActivityIntent);
            }
        });
    }
}
