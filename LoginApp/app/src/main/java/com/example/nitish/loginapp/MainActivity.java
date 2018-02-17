package com.example.nitish.loginapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    private EditText mEditTextEmailID;
    private Spinner mSpinnerCity;
    private EditText mEditTextName;
    private EditText mEditTextPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences booleanSharedPreference = getSharedPreferences(Constants.BOOLEAN_PREFERENCE, Context.MODE_PRIVATE);
        if (booleanSharedPreference.getBoolean(Constants.BOOLEAN, false)) {
            Intent intentWelcome = new Intent(MainActivity.this, WelcomeActivity.class);
            intentWelcome.putExtra(Constants.EMAIL, booleanSharedPreference.getString(Constants.ACTIVE_USER_EMAIL, null));
            intentWelcome.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intentWelcome);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextEmailID = findViewById(R.id.edit_text_email_id);
        mEditTextEmailID.getOnFocusChangeListener();
        mSpinnerCity = findViewById(R.id.spinner_city);
        mSpinnerCity.getOnFocusChangeListener();
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                R.array.city_arrays, R.layout.spinner_item);
        mSpinnerCity.setAdapter(adapter);
        mEditTextName = findViewById(R.id.edit_text_first_name);
        mEditTextName.getOnFocusChangeListener();
        mEditTextPhoneNumber = findViewById(R.id.edit_text_phone_number);
        mEditTextPhoneNumber.getOnFocusChangeListener();

        Button mButtonSignUp = findViewById(R.id.button_sign_up);
        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View selectedView = mSpinnerCity.getSelectedView();
                    if (selectedView != null && selectedView instanceof TextView) {
                        TextView selectedTextView = (TextView) selectedView;
                        if (!isValidSpinner(mSpinnerCity.getSelectedItemPosition())) {
                            String errorString = selectedTextView.getResources().getString(R.string.error_spinner);
                            selectedTextView.setError(errorString);
                        }
                    }

                if (!isValidEmail(mEditTextEmailID.getText().toString())) {
                    mEditTextEmailID.setError(getString(R.string.error_email));
                }


                if (!isValidName(mEditTextName.getText().toString())) {
                    mEditTextName.setError(getString(R.string.error_first_name));
                }

                if (!isValidPhoneNumber(mEditTextPhoneNumber.getText().toString())) {
                    mEditTextPhoneNumber.setError(getString(R.string.error_phone));
                }

                if ((isValid((mEditTextName.getText().toString()), (mEditTextPhoneNumber.getText().toString()),(mSpinnerCity.getSelectedItemPosition())))
                        && (isValidEmail(mEditTextEmailID.getText().toString()))) {

                    Log.d("Insert: ", "Inserting ..");

                    Log.d("Reading: ", "Reading all contacts..");

                    SharedPreferences sharedPreferencesSignUpData = getApplicationContext().getSharedPreferences(mEditTextEmailID.getText().toString(), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferencesSignUpData.edit();
                    editor.putString(Constants.EMAIL, mEditTextEmailID.getText().toString());
                    editor.putString(Constants.FIRST_NAME, mEditTextName.getText().toString());
                    editor.apply();

                    Intent intentWelcome = new Intent(MainActivity.this, WelcomeActivity.class);
                    intentWelcome.putExtra(Constants.EMAIL, mEditTextEmailID.getText().toString());
                    intentWelcome.addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK | android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intentWelcome);
                }
            }
        });

    }

    private boolean isValid(String firstName, String lastName,int position) {
        return (firstName.length() >= 1 && lastName.length() >= 1 && position > 0);
    }

    private boolean isValidPhoneNumber(String phone) {
        return phone.length() >=10 && android.util.Patterns.PHONE.matcher(phone).matches();    }

    private boolean isValidName(String firstName) {
        return (firstName.length() >= 1);
    }

    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidSpinner(int position){
        return position>0;
    }

}
