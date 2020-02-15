package smsfrompc.com.smsfrompc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ContactsListAdapter extends ArrayAdapter<Contact> {

    private Context mContext;
    int mResource;

    public ContactsListAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public ContactsListAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public ContactsListAdapter(@NonNull Context context, int resource, @NonNull Contact[] objects) {
        super(context, resource, objects);
    }

    public ContactsListAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull Contact[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public ContactsListAdapter(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    public ContactsListAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<Contact> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getName();
        String number = getItem(position).getNumber();

        Contact contact = new Contact(name, number);

        LayoutInflater inflater = LayoutInflater.from(mContext);

        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = convertView.findViewById(R.id.nameTextView);
        TextView tvNumber = convertView.findViewById(R.id.numberTextView);

        tvName.setText(name);
        tvNumber.setText(number);

        return convertView;
    }
}
