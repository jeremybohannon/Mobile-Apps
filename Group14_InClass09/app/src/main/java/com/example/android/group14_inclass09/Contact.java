package com.example.android.group14_inclass09;

import java.io.Serializable;

/**
 * Created by jeremybohannon on 10/30/17.
 */
//Jeremy Bohannon Elizabeth Thompson
//InClass09
//contact.java

class Contact implements Serializable{
    private String name;
    private String email;
    private String phone;
    private String department;
    private String imageid;
    private String hash;

    public Contact() {
        this.name = "";
        this.email = "";
        this.phone = "";
        this.department = "";
        this.imageid = "";
    }

    public Contact(String name, String email, String phone, String department, String imageid) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.phone = phone;
        this.imageid = imageid;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getImageid() {
        return imageid;
    }

    public void setImageid(String imageid) {
        this.imageid = imageid;
    }
}
