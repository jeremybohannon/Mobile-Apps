package com.example.android.group14_inclass07;

/**
 * Created by jeremybohannon on 10/30/17.
 */

class Contact {
    private String name;
    private String email;
    private String phone;
    private String department;
    private int imageid;

    public Contact(String name, String email, String department, int imageid) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.imageid = imageid;
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

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }
}
