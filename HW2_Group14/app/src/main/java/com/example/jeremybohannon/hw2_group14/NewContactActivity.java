package com.example.jeremybohannon.hw2_group14;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class NewContactActivity extends AppCompatActivity {
    Intent intent = new Intent();
    Button saveBtn;
    ImageButton imageButton;

    String picture;

    EditText firstName, lastName, phoneNumber, company, email, url, address, birthday, nickname, facebook, twitter, skype, youtube;
    Contact selectedContact = null;
    int index = -1;

    private static final int CAMERA_PIC_REQUEST = 1337;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        if(MainActivity.isEdit){
            setTitle("Edit Contact");
        } else {
            setTitle("Create New Contact");
        }

        saveBtn = (Button) findViewById(R.id.saveBtn);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        phoneNumber = (EditText) findViewById(R.id.phone);
        company = (EditText) findViewById(R.id.company);
        email = (EditText) findViewById(R.id.email);
        url = (EditText) findViewById(R.id.url);
        address = (EditText) findViewById(R.id.address);
        birthday = (EditText) findViewById(R.id.birthday);
        nickname = (EditText) findViewById(R.id.nickname);
        facebook = (EditText) findViewById(R.id.facebook);
        twitter = (EditText) findViewById(R.id.twitter);
        skype = (EditText) findViewById(R.id.skype);
        youtube = (EditText) findViewById(R.id.youtube);

        if(MainActivity.isEdit){
            int identifier = getIntent().getExtras().getInt("EXTRA_IDENTIFIER");

            for(int i = 0; i < MainActivity.contacts.size(); i++){
                if(MainActivity.contacts.get(i).getId() == identifier){
                    selectedContact = MainActivity.contacts.get(i);
                    index = i;
                }
            }

            if (selectedContact != null) {
                Bitmap image = BitmapFactory.decodeFile(selectedContact.getPicture());
                imageButton.setImageBitmap(image);
                firstName.setText(selectedContact.getFirstName());
                lastName.setText(selectedContact.getLastName());
                phoneNumber.setText(selectedContact.getPhoneNumber());
                company.setText(selectedContact.getCompany());
                email.setText(selectedContact.getEmail());
                url.setText(selectedContact.getUrl());
                address.setText(selectedContact.getAddress());
                birthday.setText(selectedContact.getBirthday());
                nickname.setText(selectedContact.getNickname());
                facebook.setText(selectedContact.getFacebookUrl());
                twitter.setText(selectedContact.getTwitterUrl());
                skype.setText(selectedContact.getSkype());
                youtube.setText(selectedContact.getYoutubeChannel());
            } else {
                return;
            }
        }

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent cameraIntent = new  Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, NewContactActivity.CAMERA_PIC_REQUEST);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstNameString = firstName.getText().toString();
                String lastNameString = lastName.getText().toString();
                String phoneString = phoneNumber.getText().toString();

                if(!firstNameString.isEmpty() && !lastNameString.isEmpty() && !phoneString.isEmpty()){
                    if(MainActivity.isEdit){
                        selectedContact.setPicture(picture);
                        selectedContact.setFirstName(firstNameString);
                        selectedContact.setLastName(lastNameString);
                        selectedContact.setPhoneNumber(phoneString);
                        selectedContact.setCompany(company.getText().toString());
                        selectedContact.setEmail(email.getText().toString());
                        selectedContact.setUrl(url.getText().toString());
                        selectedContact.setAddress(address.getText().toString());
                        selectedContact.setBirthday(birthday.getText().toString());
                        selectedContact.setNickname(nickname.getText().toString());
                        selectedContact.setFacebookUrl(facebook.getText().toString());
                        selectedContact.setTwitterUrl(twitter.getText().toString());
                        selectedContact.setSkype(skype.getText().toString());
                        selectedContact.setYoutubeChannel(youtube.getText().toString());

                        MainActivity.contacts.set(index, selectedContact);

                        setResult(RESULT_OK, intent);
                        finish();
                    } else {
                        MainActivity.contacts.add(new Contact(MainActivity.idCounter,
                                picture,
                                firstNameString,
                                lastNameString,
                                phoneString,
                                company.getText().toString(),
                                email.getText().toString(),
                                url.getText().toString(),
                                address.getText().toString(),
                                birthday.getText().toString(),
                                nickname.getText().toString(),
                                facebook.getText().toString(),
                                twitter.getText().toString(),
                                skype.getText().toString(),
                                youtube.getText().toString()));

                        MainActivity.idCounter++;

                        setResult(RESULT_OK, intent);
                        finish();
                    }
                } else {
                    Toast.makeText(NewContactActivity.this, "Enter at least first/last name, and phone number!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NewContactActivity.CAMERA_PIC_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap picture = (Bitmap) data.getExtras().get("data");
            this.setPicture(picture);
        }
    }

    private void setPicture(Bitmap picture) {
        try {
            ((ImageButton) findViewById(R.id.imageButton)).setImageBitmap(picture);

            File tmp = File.createTempFile(UUID.randomUUID().toString(), null, this.getCacheDir());
            this.picture = tmp.getAbsolutePath();

            ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
            picture.compress(Bitmap.CompressFormat.PNG, 0, byteOutput);
            byte[] bitmapData = byteOutput.toByteArray();

            FileOutputStream fileOutput = new FileOutputStream(tmp);
            fileOutput.write(bitmapData);
            fileOutput.flush();
            fileOutput.close();

        } catch (IOException ignore) {
            // catch exception
        }
    }

}
