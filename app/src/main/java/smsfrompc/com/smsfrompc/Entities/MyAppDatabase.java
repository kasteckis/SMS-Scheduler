package smsfrompc.com.smsfrompc.Entities;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import smsfrompc.com.smsfrompc.Entities.Classes.Setting;
import smsfrompc.com.smsfrompc.Entities.Daos.HistoryMessageDao;
import smsfrompc.com.smsfrompc.Entities.Daos.SettingDao;

@Database(entities = {Setting.class}, version = 1)
public abstract class MyAppDatabase extends RoomDatabase {
    public abstract SettingDao settingDao();
    public abstract HistoryMessageDao historyMessageDao();
}
