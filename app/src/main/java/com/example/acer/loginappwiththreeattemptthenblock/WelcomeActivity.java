package com.example.acer.loginappwiththreeattemptthenblock;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    private TextView welcomeTextView;
    private Button logoutButton;
    private SharedPreferences sharedPreferences;
    private static final String PREF_KEY_CREDENTIAL="cred";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        welcomeTextView=(TextView) findViewById(R.id.welcomeTextView);
        logoutButton=(Button) findViewById(R.id.logoutButton);
        sharedPreferences=getSharedPreferences(PREF_KEY_CREDENTIAL, Context.MODE_PRIVATE);

        welcomeTextView.setText("Welcome");

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences.edit().clear().apply();
                Intent loginIntent=new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });
    }
}
