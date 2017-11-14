package com.example.android.group14_inclass09;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignUpActivity extends AppCompatActivity {

    EditText firstName, lastName, email, password, repeatPassword;
    Button signUpBtn, cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setTitle("Sign Up");

        firstName = (EditText) findViewById(R.id.firstNameText);
        lastName = (EditText) findViewById(R.id.lastNameText);
        email = (EditText) findViewById(R.id.emailText);
        password = (EditText) findViewById(R.id.passwordText);
        repeatPassword = (EditText) findViewById(R.id.repeatPasswordText);

        signUpBtn = (Button) findViewById(R.id.signUpBtn);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String firstNameVal, lastNameVal, emailVal, passwordVal, repeatPasswordVal;

                    firstNameVal = firstName.getText().toString();
                    lastNameVal = lastName.getText().toString();
                    emailVal = email.getText().toString();
                    passwordVal = password.getText().toString();
                    repeatPasswordVal = repeatPassword.getText().toString();

                    if (firstNameVal.isEmpty() || lastNameVal.isEmpty() || emailVal.isEmpty() || passwordVal.isEmpty() || repeatPasswordVal.isEmpty()) {
                        Toast.makeText(SignUpActivity.this, "Please complete all fields", Toast.LENGTH_SHORT).show();
                    } else {
                        if(passwordVal.length() < 6) {
                            Toast.makeText(SignUpActivity.this, "Password must be 6 characters long", Toast.LENGTH_SHORT).show();
                        } else {
                            if (passwordVal.equals(repeatPasswordVal)) {
                                signup(firstNameVal, lastNameVal, emailVal, passwordVal);
                            } else {
                                Toast.makeText(SignUpActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        cancelBtn = (Button) findViewById(R.id.cancelBtn);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void signup(String firstname, String lastname, String email, final String password) throws Exception {

    }
}
