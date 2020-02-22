package smsfrompc.com.smsfrompc.Entities;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface MyDao {

    @Insert
    public void addSetting(Setting setting);
}
