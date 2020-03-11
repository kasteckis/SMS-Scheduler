package smsfrompc.com.smsfrompc.Entities;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SettingDao {
    @Query("SELECT * FROM settings")
    List<Setting> getAll();

    @Insert
    void addSetting(Setting setting);

    @Delete
    void deleteSetting(Setting setting);
}
