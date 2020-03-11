package smsfrompc.com.smsfrompc.Entities;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Setting.class}, version = 1)
public abstract class MyAppDatabase extends RoomDatabase {
    public abstract SettingDao settingDao();
}
