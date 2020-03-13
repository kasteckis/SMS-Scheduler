package smsfrompc.com.smsfrompc.Entities.Classes;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

import smsfrompc.com.smsfrompc.Activities.MainActivity;

@Entity(tableName = "settings")
public class Setting {

    public static Setting ScheduleFormatSetting = null;

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "setting_name")
    private String settingName;

    @ColumnInfo(name = "setting_value")
    private String settingValue;

    public static void setDefaultSettings() {
        List<Setting> allSettings = MainActivity.myAppDatabase.settingDao().getAll();
        if(allSettings.size() == 0) {
            Setting setting = new Setting();
            setting.setId(1);
            setting.setSettingName("delayTime");
            setting.setSettingValue("seconds");

            MainActivity.myAppDatabase.settingDao().addSetting(setting);
        }
        allSettings = MainActivity.myAppDatabase.settingDao().getAll();
        for(Setting setting : allSettings) {
            if(setting.getSettingName().equals("delayTime")) {
                ScheduleFormatSetting = setting;
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }
}
