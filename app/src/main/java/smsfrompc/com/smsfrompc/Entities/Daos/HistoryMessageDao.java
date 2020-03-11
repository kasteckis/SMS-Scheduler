package smsfrompc.com.smsfrompc.Entities.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import smsfrompc.com.smsfrompc.Entities.Classes.HistoryMessage;

@Dao
public interface HistoryMessageDao {
    @Query("SELECT * FROM history_messages")
    List<HistoryMessage> getAll();

    @Insert
    void addHistoryMessage(HistoryMessage historyMessage);

    @Delete
    void deleteHistoryMessage(HistoryMessage historyMessage);
}
