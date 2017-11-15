package com.example.android.group14_inclass09;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private final String TAG = "Debug";

    EditText email, password;
    Button loginBtn, signupBtn;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progress = new ProgressDialog(this);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        loginBtn = (Button) findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            System.out.println("[LoginActivity | onCreate | loginBtn click] " + " Login btn clicked");
            try {
                String loginEmail = email.getText().toString();
                String loginPassword = password.getText().toString();

                if(loginEmail.isEmpty() || loginPassword.isEmpty()) {
                    System.out.println("[LoginActivity | onCreate | loginBtn click] " + " Empty credentials");
                } else {
                    loginUser(loginEmail, loginPassword);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            }
        });

        signupBtn = findViewById(R.id.signupBtn);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            System.out.println("[LoginActivity | onCreate | signUp click] " + " Sign up btn clicked");
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
            }
        });

    }

    public void loginUser(String email, String password) {
        System.out.println("[LoginActivity | loginUser] " + "In loginUser with email: " + email);


        progress.setTitle("Loading");
        progress.setMessage("Logging you in!");
        progress.setCancelable(false);
        progress.show();


        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progress.dismiss();
                    if (task.isSuccessful()) {
                        System.out.println("[LoginActivity | loginUser] " + "Sign up successful");
                        Log.d(TAG, "signInWithEmail:success");

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        System.out.println("[LoginActivity | loginUser] " + "Sign up unsuccessful");
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                    }
                }
            });
    }
}
