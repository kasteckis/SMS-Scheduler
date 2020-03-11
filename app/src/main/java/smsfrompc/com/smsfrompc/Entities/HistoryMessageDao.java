package smsfrompc.com.smsfrompc.Entities;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HistoryMessageDao {
    @Query("SELECT * FROM history_messages")
    List<HistoryMessage> getAll();

    @Insert
    void addHistoryMessage(HistoryMessage historyMessage);

    @Delete
    void deleteHistoryMessage(HistoryMessage historyMessage);
}
