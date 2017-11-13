package com.example.jeremybohannon.hw2_group14;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by elizabeththompson on 10/1/17.
 */

public class ContactListViewHolder {

    ImageView picture;
    TextView firstName;
    TextView lastName;
    TextView phone;

    public ContactListViewHolder(ImageView picture, TextView firstName, TextView lastName, TextView phone) {
        this.picture = picture;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public ImageView getPicture() {
        return picture;
    }

    public void setPicture(ImageView picture) {
        this.picture = picture;
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
