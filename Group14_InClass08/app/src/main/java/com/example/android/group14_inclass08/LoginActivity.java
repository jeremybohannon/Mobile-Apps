package com.example.android.group14_inclass08;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class LoginActivity extends AppCompatActivity {

    private final OkHttpClient client = new OkHttpClient();

    EditText email, password;
    Button loginBtn, signupBtn;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

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
                try {
                    login(email.getText().toString(), password.getText().toString());
                } catch (Exception e) {
                    Toast.makeText(LoginActivity.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        signupBtn = (Button) findViewById(R.id.signupBtn);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });


    }

    public void login(String email, String password) throws Exception {

        RequestBody formBody = new FormBody.Builder()
                .add("email", email)
                .add("password", password)
                .build();

        Request request = new Request.Builder()
                .url("http://ec2-54-164-74-55.compute-1.amazonaws.com/api/login")
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                Toast.makeText(LoginActivity.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                try {
                    ResponseBody responseBody = response.body();

                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }

                    Gson gson = new Gson();
                    final User user = gson.fromJson(responseBody.string(), User.class);

                    //Store token in SharedPreferences
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString("userToken", user.getToken());
                    editor.putInt("userID", user.getUser_id());
                    editor.apply();

                    Intent intent = new Intent(LoginActivity.this, ThreadActivity.class);
                    intent.putExtra("User", user);
                    startActivity(intent);

                    System.out.println(user.toString());
                } catch (Exception e) {
                    Toast.makeText(LoginActivity.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("result code: " + resultCode);
        System.out.println("request code: " + requestCode);
        if (requestCode == 1) {
            if(resultCode == LoginActivity.RESULT_OK){
                Intent intent = new Intent(LoginActivity.this, ThreadActivity.class);
                intent.putExtra("User", data.getSerializableExtra("User"));
                startActivity(intent);
            }
        }
    }

}
