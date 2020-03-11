package smsfrompc.com.smsfrompc;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    TextView informationAboutPermissions;
    Button requestPermissionsButton;
    PermissionListener permissionListener;

    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        informationAboutPermissions = findViewById(R.id.informationAboutPermissions);
        requestPermissionsButton = findViewById(R.id.reqPermissionsBtn);
        saveButton = findViewById(R.id.saveSettingsBtn);

        permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                MainActivity.permissionsGranted = true;
                informationAboutPermissions.setVisibility(View.GONE);
                requestPermissionsButton.setVisibility(View.GONE);
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                MainActivity.permissionsGranted = false;
                Toast.makeText(SettingsActivity.this, getResources().getString(R.string.wont_be_using_program_without_granting_permissions), Toast.LENGTH_SHORT).show();
                informationAboutPermissions.setText(getResources().getString(R.string.this_app_wont_work_without_granting_permissions));
            }
        };

        requestPermissions();

        requestPermissionsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                requestPermissions();
            }
        });


        handleScheduleFormatSpinner();
        int a = 0;
    }

    void requestPermissions()
    {
        TedPermission.with(SettingsActivity.this)
                .setPermissionListener(permissionListener)
                .setPermissions(Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS,
                        Manifest.permission.SEND_SMS, Manifest.permission.INTERNET)
                .check();
    }

    void handleScheduleFormatSpinner() {
        String[] arraySpinnerLocal = new String[2];
        if(MainActivity.ScheduleFormatSetting.getSettingValue().equals("seconds")) {
            arraySpinnerLocal[0] = "seconds";
            arraySpinnerLocal[1] = "minutes";
        } else {
            arraySpinnerLocal[0] = "minutes";
            arraySpinnerLocal[1] = "seconds";
        }

        final String[] arraySpinner = new String[] {
                arraySpinnerLocal[0], arraySpinnerLocal[1]
        };

        final String[] selectedScheduleFormat = {"seconds"};

        Spinner scheduleFormatSpinner = findViewById(R.id.scheduleFormatSpinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        scheduleFormatSpinner.setAdapter(spinnerAdapter);

        scheduleFormatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                selectedScheduleFormat[0] = arraySpinner[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                throw new RuntimeException("User selected nothing");
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.ScheduleFormatSetting.setSettingValue(selectedScheduleFormat[0]);
                MainActivity.myAppDatabase.settingDao().updateSetting(MainActivity.ScheduleFormatSetting);
                Toast.makeText(SettingsActivity.this, "You will be using " + selectedScheduleFormat[0] + " from now on when delaying messages!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
