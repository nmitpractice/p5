package com.example.p5;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button sendBtn, btnSendEmail, btnPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendBtn = (Button) findViewById(R.id.btnSendSMS);
        btnSendEmail = (Button) findViewById(R.id.btnSendEmail);
        btnPhone = (Button) findViewById(R.id.btnDialPhone);

        // Lambda expression for onClickListeners
        sendBtn.setOnClickListener(view -> sendSMSMessage());
        btnSendEmail.setOnClickListener(view -> sendEmail());
        btnPhone.setOnClickListener(view -> PhoneDial());

    }

    protected void sendEmail() {

        try {
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setDataAndType(Uri.parse("mailto:"), "text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"ram@gmail.com"});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Test");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Message Body Test");
            startActivity(emailIntent);
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "No Email App Found", Toast.LENGTH_LONG).show();
        }
    }

    protected void sendSMSMessage() {
        Uri SMS_URI = Uri.parse("smsto:+919123456789"); //Replace the phone number
        Intent smsIntent = new Intent(Intent.ACTION_VIEW, SMS_URI);
        smsIntent.putExtra("sms_body", "This is test message"); //Replace the message witha a vairable
        startActivity(smsIntent);
    }

    protected void PhoneDial() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        startActivity(intent);
    }

}