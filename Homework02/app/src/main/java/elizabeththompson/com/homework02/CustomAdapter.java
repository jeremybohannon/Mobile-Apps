package elizabeththompson.com.homework02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;

/**
 * Created by elizabeththompson on 10/1/17.
 */

public class CustomAdapter extends ArrayAdapter {
    private ContactListItem[] objects;

    public CustomAdapter(Context context, int resource, ContactListItem[] objects) {
        super(context, resource, objects);
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ContactListViewHolder viewHolder = null;
        ContactListItem listViewItem = objects[position];


        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_list_item, null);

            TextView firstName = (TextView) convertView.findViewById(R.id.firstName);
            TextView lastName = (TextView) convertView.findViewById(R.id.lastName);
            TextView phone = (TextView) convertView.findViewById(R.id.phone);
            viewHolder = new ContactListViewHolder(firstName, lastName, phone);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ContactListViewHolder) convertView.getTag();
        }

        viewHolder.getFirstName().setText(listViewItem.getFirstName());
        viewHolder.getLastName().setText(listViewItem.getLastName());
        viewHolder.getPhone().setText(listViewItem.getPhone());

        return convertView;
    }
}
