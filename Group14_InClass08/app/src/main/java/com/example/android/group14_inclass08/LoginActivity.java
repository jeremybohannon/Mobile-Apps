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

        //Ensure userToken and userId is reset on startup
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("userToken", "");
        editor.putInt("userID", 0);
        editor.apply();

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
                        displayLoginFailure("Empty Credentials");
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

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("[LoginActivity | onResume] " + " In onResume");

        //When this activity is resumed, check if the token has been set, if so we know to log the user in.
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String userToken = prefs.getString("userToken", "");

        if(!userToken.isEmpty()){
            try {
                System.out.println("[LoginActivity | onResume] " + " Token not empty, Token: " + userToken);

                String userEmail = prefs.getString("userEmail", "");
                String password = prefs.getString("password", "");

                login(userEmail, password);
            } catch (Exception e) {
                System.out.println("[LoginActivity | onResume] " + " Token empty");

                displayLoginFailure();
                e.printStackTrace();
            }
        }


    }

    public void login(String email, String password) throws Exception {
        System.out.println("[LoginActivity | login] " + " Login with email: " + email);

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
                System.out.println("[LoginActivity | login | onFailure] ");

                displayLoginFailure();
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                try {
                    ResponseBody responseBody = response.body();

                    if (!response.isSuccessful()) {
                        displayLoginFailure("Unauthorized");
                        throw new IOException(""+response);
                    }

                    Gson gson = new Gson();
                    final User user = gson.fromJson(responseBody.string(), User.class);

                    //Store token in SharedPreferences
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString("userToken", user.getToken());
                    editor.putInt("userID", user.getUser_id());
                    editor.apply();

                    System.out.println("[LoginActivity | login | onResponse] " + " Starting ThreadActivity intent");

                    Intent intent = new Intent(LoginActivity.this, ThreadActivity.class);
                    intent.putExtra("User", user);
                    startActivity(intent);

                } catch (Exception e) {
                    System.out.println("[LoginActivity | login | onResponse] " + " Login failed");
                    e.printStackTrace();
                }
            }
        });

    }

    void displayLoginFailure() {
        runOnUiThread(
            new Runnable() {
                public void run() {
                    Toast.makeText(LoginActivity.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            });
    }

    void displayLoginFailure(final String message) {
        runOnUiThread(
            new Runnable() {
                public void run() {
                    Toast.makeText(LoginActivity.this, "Login Unsuccessful, " + message, Toast.LENGTH_SHORT).show();
                }
            });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("[LoginActivity | login | onDestroy] " + "Clean up");

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("userToken", "");
        editor.putInt("userID", 0);
        editor.apply();

    }
}
