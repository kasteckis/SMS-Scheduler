package smsfrompc.com.smsfrompc.Entities.Classes;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "history_messages")
public class HistoryMessage {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "recipient_name")
    private String recipientName;

    @ColumnInfo(name = "recipient_number")
    private String recipientNumber;

    @ColumnInfo(name = "delay_time")
    private String delayTime;

    @ColumnInfo(name = "schedule_format")
    private String scheduleFormat;

    @ColumnInfo(name = "date")
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientNumber() {
        return recipientNumber;
    }

    public void setRecipientNumber(String recipientNumber) {
        this.recipientNumber = recipientNumber;
    }

    public String getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(String delayTime) {
        this.delayTime = delayTime;
    }

    public String getScheduleFormat() {
        return scheduleFormat;
    }

    public void setScheduleFormat(String scheduleFormat) {
        this.scheduleFormat = scheduleFormat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
