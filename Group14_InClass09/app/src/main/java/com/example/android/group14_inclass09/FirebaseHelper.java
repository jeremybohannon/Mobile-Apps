package com.example.android.group14_inclass09;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by elizabeththompson on 11/13/17.
 */

public class FirebaseHelper {

    private final String TAG = "Debug";

    private FirebaseAuth auth;
    DatabaseReference dbRef;
    private Activity activity;

    public FirebaseHelper(Activity activity) {
        this.auth = FirebaseAuth.getInstance();
        this.dbRef = FirebaseDatabase.getInstance().getReference();
        this.activity = activity;
    }

    public void loginUser(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "signInWithEmail:success");
                        FirebaseUser user = auth.getCurrentUser();
                    } else {
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                    }
                }
            });
    }

    public void registerUser(String firstname, String lastname, final String email, final String password) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "createUserWithEmail:success");
                        FirebaseUser user = auth.getCurrentUser();
                        loginUser(email, password);
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    }
                }
            });
    }

    public void createContact(String name, String email, String phone, String department, int imageId) {

    }


    public void getContacts() {
        FirebaseUser currentUser = auth.getCurrentUser();
        DatabaseReference contacts = dbRef.child(currentUser.getUid()).child("contactList");

        contacts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //String value = dataSnapshot.getValue();
                Log.d(TAG, "Value is: " + dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}
