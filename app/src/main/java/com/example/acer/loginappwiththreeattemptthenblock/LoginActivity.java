package com.example.acer.loginappwiththreeattemptthenblock;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText,passwordEditText;
    private CheckBox rememberMeCheckBox;
    private Button loginButton,resetButton;


    private static final String PREF_KEY_CREDENTIAL="cred";
    private static final String PREF_KEY_USERNAME="username";
    private static final String PREF_KEY_PASSWORD="password";
    private SharedPreferences sharedPreferences;

    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences=getSharedPreferences(PREF_KEY_CREDENTIAL, Context.MODE_PRIVATE);

        if(isPreferenceExist()==true){
            Intent welcomeIntent=new Intent(LoginActivity.this,WelcomeActivity.class);
            startActivity(welcomeIntent);
            finish();
        }
        else {
            Toast.makeText(this, "Shared Preference not there", Toast.LENGTH_SHORT).show();
        }


        usernameEditText=(EditText) findViewById(R.id.usernameEditText);
        passwordEditText=(EditText) findViewById(R.id.passwordEditText);
        rememberMeCheckBox=(CheckBox) findViewById(R.id.rememberMeCheckBox);
        loginButton=(Button) findViewById(R.id.loginButton);
        resetButton=(Button) findViewById(R.id.resetButton);




        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=usernameEditText.getText().toString();
                String password=passwordEditText.getText().toString();
                if(username.equals("rishi")&&password.equals("password")){
                    if(rememberMeCheckBox.isChecked()){
                        sharedPreferences.edit().putString(PREF_KEY_USERNAME,username).apply();
                        sharedPreferences.edit().putString(PREF_KEY_PASSWORD,password).apply();
                        Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                        Intent welcomeIntent=new Intent(LoginActivity.this,WelcomeActivity.class);
                        startActivity(welcomeIntent);
                        finish();
                    }


                }
                else {

                    counter++;

                    Toast.makeText(LoginActivity.this, "Login Attempt "+counter, Toast.LENGTH_SHORT).show();

                    if(counter==3){
                        loginButton.setEnabled(false);
                        Toast.makeText(LoginActivity.this, "You have exceeded the limit of login", Toast.LENGTH_LONG).show();
                    }
                }


            }
        });
    }
    private boolean isPreferenceExist(){
        return sharedPreferences.contains(PREF_KEY_USERNAME)
                && sharedPreferences.contains(PREF_KEY_PASSWORD);
    }

}
