package com.example.nitish.loginapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;


public class WelcomeActivity extends AppCompatActivity {

    private SharedPreferences activeUserPreference;

    private SharedPreferences sharedPreferencesFirstName;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TextView mTextViewGreeting = findViewById(R.id.text_view_greeting);

        sharedPreferencesFirstName = getSharedPreferences(getIntent().getStringExtra(Constants.EMAIL), Context.MODE_PRIVATE);
        String name = sharedPreferencesFirstName.getString(Constants.FIRST_NAME,null);
        mTextViewGreeting.setText(mTextViewGreeting.getText()+" "+name );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout_id:
                SharedPreferences.Editor activeUserEditor = activeUserPreference.edit();
                activeUserEditor.putBoolean(Constants.BOOLEAN,false);
                activeUserEditor.putString(Constants.ACTIVE_USER_EMAIL,"");
                activeUserEditor.apply();
                Intent intent= new Intent(this,MainActivity.class);
                intent.putExtra(Constants.EMAIL,sharedPreferencesFirstName.getString(Constants.EMAIL,null));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                return true;
            case R.id.about_us_id:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
