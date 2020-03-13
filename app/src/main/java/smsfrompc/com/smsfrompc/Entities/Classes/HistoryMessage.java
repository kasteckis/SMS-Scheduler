package smsfrompc.com.smsfrompc.Entities.Classes;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "history_messages")
public class HistoryMessage {

    @PrimaryKey(autoGenerate = true)
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

    @ColumnInfo(name = "text")
    private String text;

    public HistoryMessage(String recipientName, String recipientNumber, String delayTime, String scheduleFormat, String date, String text) {
        this.recipientName = recipientName;
        this.recipientNumber = recipientNumber;
        this.delayTime = delayTime;
        this.scheduleFormat = scheduleFormat;
        this.date = date;
        this.text = text;
    }

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
