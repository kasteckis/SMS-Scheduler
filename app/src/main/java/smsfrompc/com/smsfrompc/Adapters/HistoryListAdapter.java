package smsfrompc.com.smsfrompc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import smsfrompc.com.smsfrompc.Activities.HistoryActivity;
import smsfrompc.com.smsfrompc.Entities.Classes.Contact;
import smsfrompc.com.smsfrompc.Entities.Classes.HistoryMessage;
import smsfrompc.com.smsfrompc.R;

public class HistoryListAdapter extends ArrayAdapter<HistoryMessage> {

    private Context mContext;
    int mResource;

    public HistoryListAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public HistoryListAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public HistoryListAdapter(@NonNull Context context, int resource, @NonNull HistoryMessage[] objects) {
        super(context, resource, objects);
    }

    public HistoryListAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull HistoryMessage[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public HistoryListAdapter(@NonNull Context context, int resource, @NonNull List<HistoryMessage> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    public HistoryListAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<HistoryMessage> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String recipientName = getItem(position).getRecipientName();
        String recipientNumber = getItem(position).getRecipientNumber();
        String delayTime = getItem(position).getDelayTime();
        String scheduleFormat = getItem(position).getScheduleFormat();
        String date = getItem(position).getDate();
        String text = getItem(position).getText();


        HistoryMessage historyMessage = new HistoryMessage(recipientName, recipientNumber, delayTime, scheduleFormat, date, text);

        LayoutInflater inflater = LayoutInflater.from(mContext);

        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = convertView.findViewById(R.id.nameTextView);
        TextView tvNumber = convertView.findViewById(R.id.numberTextView);
        TextView tvDelay = convertView.findViewById(R.id.delayTimeTextView);
        TextView tvDate = convertView.findViewById(R.id.dateTextView);

        tvName.setText(recipientName);
        tvNumber.setText(recipientNumber);
        tvDelay.setText(delayTime);
        tvDate.setText(date);

        return convertView;
    }
}
