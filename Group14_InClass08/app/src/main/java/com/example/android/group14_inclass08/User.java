package com.example.android.group14_inclass08;

/**
 * Created by elizabeththompson on 11/6/17.
 */

public class User {
    String status, token, user_email, user_fname, user_lame, user_role;
    int user_id;

    @Override
    public String toString() {
        return "User{" +
                "status='" + status + '\'' +
                ", token='" + token + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_fname='" + user_fname + '\'' +
                ", user_lame='" + user_lame + '\'' +
                ", user_role='" + user_role + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
