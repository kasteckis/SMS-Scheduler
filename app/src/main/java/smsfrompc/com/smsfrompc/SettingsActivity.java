package smsfrompc.com.smsfrompc;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    TextView informationAboutPermissions;
    Button requestPermissionsButton;
    PermissionListener permissionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        informationAboutPermissions = findViewById(R.id.informationAboutPermissions);
        requestPermissionsButton = findViewById(R.id.reqPermissionsBtn);

        permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                informationAboutPermissions.setVisibility(View.GONE);
                requestPermissionsButton.setVisibility(View.GONE);
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(SettingsActivity.this, "You won't be able to use this program without granting permissions!", Toast.LENGTH_SHORT).show();
                informationAboutPermissions.setText("This app won't work without granting permissions. Please request permission grant again.");
            }
        };

        requestPermissions();

        requestPermissionsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                requestPermissions();
            }
        });
    }

    void requestPermissions()
    {
        TedPermission.with(SettingsActivity.this)
                .setPermissionListener(permissionListener)
                .setPermissions(Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS,
                        Manifest.permission.SEND_SMS, Manifest.permission.INTERNET)
                .check();
    }
}
