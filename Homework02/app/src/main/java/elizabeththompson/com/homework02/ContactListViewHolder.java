package elizabeththompson.com.homework02;

import android.widget.TextView;

/**
 * Created by elizabeththompson on 10/1/17.
 */

public class ContactListViewHolder {

    TextView firstName;
    TextView lastName;
    TextView phone;

    public ContactListViewHolder(TextView firstName, TextView lastName, TextView phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public TextView getFirstName() {
        return firstName;
    }

    public void setFirstName(TextView firstName) {
        this.firstName = firstName;
    }

    public TextView getLastName() {
        return lastName;
    }

    public void setLastName(TextView lastName) {
        this.lastName = lastName;
    }

    public TextView getPhone() {
        return phone;
    }

    public void setPhone(TextView phone) {
        this.phone = phone;
    }
}
