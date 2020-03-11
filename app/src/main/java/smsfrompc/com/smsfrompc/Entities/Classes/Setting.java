package smsfrompc.com.smsfrompc.Entities.Classes;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "settings")
public class Setting {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "setting_name")
    private String settingName;

    @ColumnInfo(name = "setting_value")
    private String settingValue;

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
