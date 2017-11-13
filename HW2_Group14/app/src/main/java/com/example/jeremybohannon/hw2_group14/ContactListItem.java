package com.example.jeremybohannon.hw2_group14;

/**
 * Created by elizabeththompson on 10/1/17.
 */

public class ContactListItem {

    private String picture;
    private String firstName;
    private String lastName;
    private String phone;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ContactListItem(String picture, String firstName, String lastName, String phone, int id) {
        this.picture = picture;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.id = id;

    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
