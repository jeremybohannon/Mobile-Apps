package com.example.jeremybohannon.hw2_group14;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ContactDetailsActivity extends AppCompatActivity {
    Intent intent = new Intent();

    ImageView picture;
    TextView firstName, lastName, phoneNumber, company, email, url, address, birthday, nickname, facebook, twitter, skype, youtube;
    TextView urlValue, facebookUrl, twitterUrl, skypeUrl, youtubeUrl;
    Contact selectedContact = null;
    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        setTitle("Display Contact");

        picture = (ImageView) findViewById(R.id.picture);
        firstName = (TextView) findViewById(R.id.firstName);
        lastName = (TextView) findViewById(R.id.lastName);
        phoneNumber = (TextView) findViewById(R.id.phone);
        company = (TextView) findViewById(R.id.company);
        email = (TextView) findViewById(R.id.email);
        url = (TextView) findViewById(R.id.url);
        address = (TextView) findViewById(R.id.address);
        birthday = (TextView) findViewById(R.id.birthday);
        nickname = (TextView) findViewById(R.id.nickname);
        facebook = (TextView) findViewById(R.id.facebook);
        twitter = (TextView) findViewById(R.id.twitter);
        skype = (TextView) findViewById(R.id.skype);
        youtube = (TextView) findViewById(R.id.youtube);

        urlValue = (TextView) findViewById(R.id.urlValue);
        facebookUrl = (TextView) findViewById(R.id.facebookUrl);
        twitterUrl = (TextView) findViewById(R.id.twitterUrl);
        skypeUrl = (TextView) findViewById(R.id.skypeUrl);
        youtubeUrl = (TextView) findViewById(R.id.youtubeUrl);

        int identifier = getIntent().getExtras().getInt("EXTRA_IDENTIFIER");

        for(int i = 0; i < MainActivity.contacts.size(); i++){
            if(MainActivity.contacts.get(i).getId() == identifier){
                selectedContact = MainActivity.contacts.get(i);
                index = i;
            }
        }

        if (selectedContact != null) {
            Bitmap image = BitmapFactory.decodeFile(selectedContact.getPicture());
            picture.setImageBitmap(image);
            firstName.setText(firstName.getText() + ": " + selectedContact.getFirstName());
            lastName.setText(lastName.getText() + ": " + selectedContact.getLastName());
            phoneNumber.setText(phoneNumber.getText() + ": " + selectedContact.getPhoneNumber());
            company.setText(company.getText() + ": " + selectedContact.getCompany());
            email.setText(email.getText() + ": " + selectedContact.getEmail());
            urlValue.setText(selectedContact.getUrl());
            address.setText(address.getText() + ": " + selectedContact.getAddress());
            birthday.setText(birthday.getText() + ": " + selectedContact.getBirthday());
            nickname.setText(nickname.getText() + ": " + selectedContact.getNickname());
            facebookUrl.setText(selectedContact.getFacebookUrl());
            twitterUrl.setText(selectedContact.getTwitterUrl());
            skypeUrl.setText(selectedContact.getSkype());
            youtubeUrl.setText(selectedContact.getYoutubeChannel());
        } else {
            return;
        }

        urlValue.setOnClickListener(onUrlClickListener);
        facebookUrl.setOnClickListener(onUrlClickListener);
        twitterUrl.setOnClickListener(onUrlClickListener);
        skypeUrl.setOnClickListener(onUrlClickListener);
        youtubeUrl.setOnClickListener(onUrlClickListener);
    }

    View.OnClickListener onUrlClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            TextView textview = (TextView) view;
            String url = textview.getText().toString();

            if (!url.startsWith("http://") && !url.startsWith("https://"))
                url = "http://" + url;

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        }
    };
}
