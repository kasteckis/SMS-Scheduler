package smsfrompc.com.smsfrompc.Entities.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import smsfrompc.com.smsfrompc.Entities.Classes.Setting;

@Dao
public interface SettingDao {
    @Query("SELECT * FROM settings")
    List<Setting> getAll();

    @Insert
    void addSetting(Setting setting);

    @Delete
    void deleteSetting(Setting setting);

    @Update
    void updateSetting(Setting setting);
}
