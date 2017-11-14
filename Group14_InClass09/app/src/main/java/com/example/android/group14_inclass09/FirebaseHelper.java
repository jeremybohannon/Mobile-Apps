package com.example.android.group14_inclass09;


import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by elizabeththompson on 11/13/17.
 */

public class FirebaseHelper {

    private final String TAG = "Debug";

    private FirebaseAuth auth;

    public void loginUser(String email, String password) {
        auth = FirebaseAuth.getInstance();

//        auth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "createUserWithEmail:success");
//                            FirebaseUser user = auth.getCurrentUser();
//                            //updateUI(user);
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                            //Toast.makeText(EmailPasswordActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
//                            //updateUI(null);
//                        }
//                    }
//                });
    }

    public void registerUser(String firstname, String lastname, String email, String password) {

    }

    public void createContact(String name, String email, String phone, String department, int imageId) {

    }

    public void getContacts() {

    }
}
