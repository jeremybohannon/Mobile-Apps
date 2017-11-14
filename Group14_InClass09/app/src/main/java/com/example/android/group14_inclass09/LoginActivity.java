package com.example.android.group14_inclass09;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.group14_inclass09.R;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button loginBtn, signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

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
                        login(loginEmail, loginPassword);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        signupBtn = (Button) findViewById(R.id.signupBtn);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("[LoginActivity | onCreate | signUp click] " + " Sign up btn clicked");
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

    public void login(String email, String password) throws Exception {
        System.out.println("[LoginActivity | login] " + " Login with email: " + email);




    }
}
