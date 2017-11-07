package com.example.android.group14_inclass08;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class SignUpActivity extends AppCompatActivity {

    private final OkHttpClient client = new OkHttpClient();

    EditText firstname, lastname, email, password, repeatPassword;
    Button signUpBtn, cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firstname = (EditText) findViewById(R.id.firstNameText);
        lastname = (EditText) findViewById(R.id.lastNameText);
        email = (EditText) findViewById(R.id.emailText);
        password = (EditText) findViewById(R.id.passwordText);
        repeatPassword = (EditText) findViewById(R.id.repeatPasswordText);

        signUpBtn = (Button) findViewById(R.id.signUpBtn);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String firstnameVal, lastnameVal, emailVal, passwordVal, repeatPasswordVal;

                    firstnameVal = firstname.getText().toString();
                    lastnameVal = lastname.getText().toString();
                    emailVal = email.getText().toString();
                    passwordVal = password.getText().toString();
                    repeatPasswordVal = repeatPassword.getText().toString();

                    if (firstnameVal.isEmpty() || lastnameVal.isEmpty() || emailVal.isEmpty() || passwordVal.isEmpty() || repeatPasswordVal.isEmpty()) {
                        Toast.makeText(SignUpActivity.this, "Please complete all fields", Toast.LENGTH_SHORT).show();
                    } else {
                        if (passwordVal.equals(repeatPasswordVal)) {
                            signup(firstnameVal, lastnameVal, emailVal, passwordVal);
                        } else {
                            Toast.makeText(SignUpActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void signup(String firstname, String lastname, String email, String password) throws Exception {
        RequestBody formBody = new FormBody.Builder()
                .add("fname", firstname)
                .add("lname", lastname)
                .add("email", email)
                .add("password", password)
                .build();

        Request request = new Request.Builder()
                .url("http://ec2-54-164-74-55.compute-1.amazonaws.com/api/signup")
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                try {
                    ResponseBody responseBody = response.body();

                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                    Gson gson = new Gson();
                    User user = gson.fromJson(responseBody.string(), User.class);

                    System.out.println(user.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
