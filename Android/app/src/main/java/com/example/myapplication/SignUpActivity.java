package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class SignUpActivity extends AppCompatActivity {
    EditText _oEtMobile, _oEtName, _oEtDOB, _oEtHome;
    Spinner _oSpnGender;
    Button _oBtnSignUp;
    TextView _oTvGoSignIn;
    SharedPreferences _oSharedPreferences;
    public static final String PREF_NAME = "UserPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        _oEtMobile = findViewById(R.id.et_mobile);
        _oEtName = findViewById(R.id.et_name);
        _oEtDOB = findViewById(R.id.et_dob);
        _oSpnGender = findViewById(R.id.spinner_gender);
        _oEtHome = findViewById(R.id.et_home);
        _oBtnSignUp = findViewById(R.id.btn_signup);
        _oTvGoSignIn = findViewById(R.id.tv_go_signin);

        _oSharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.gender_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _oSpnGender.setAdapter(adapter);


        _oEtDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });



        _oBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    SharedPreferences.Editor editor = _oSharedPreferences.edit();
                    editor.putString("mobile", _oEtMobile.getText().toString());
                    editor.putString("name", _oEtName.getText().toString());
                    editor.putString("dob", _oEtDOB.getText().toString());
                    editor.putString("gender", _oSpnGender.getSelectedItem().toString());
                    editor.putString("home", _oEtHome.getText().toString());
                    editor.apply();


                    Toast.makeText(SignUpActivity.this, "Registered!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                    finish();


                }
            }
        });



        _oTvGoSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
            }
        });


    }
    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePicker = new DatePickerDialog(this, (view, selectedYear, selectedMonth, selectedDay) -> {
            String dob = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
            _oEtDOB.setText(dob);

        }, year, month, day);
        datePicker.show();
    }

    private boolean validateInputs() {
        if (_oEtMobile.getText().toString().trim().isEmpty()) {
            _oEtMobile.setError("Mobile number required");
            return false;
        }
        String mobile = _oEtMobile.getText().toString().trim();
        if (!mobile.matches("\\d{10}")) {
            _oEtMobile.setError("Enter a valid 10-digit mobile number");
            return false;
        }





        if (_oEtName.getText().toString().trim().isEmpty()) {
            _oEtName.setError("Name required");
            return false;
        }
        String name = _oEtName.getText().toString();
        if(!name.matches("[a-zA-Z0-9]*")){

              _oEtName.setError("Special Characters are allowed ");
              return false;
        }

        String dobString = _oEtDOB.getText().toString().trim();
        if(dobString.isEmpty()){

            _oEtDOB.setError("DOB is required");
             return false;
        }
        String[] parts = dobString.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]) - 1;
        int year = Integer.parseInt(parts[2]);

        Calendar dob = Calendar.getInstance();
        dob.set(year, month, day);

        Calendar today = Calendar.getInstance();

        if(dob.after(today)){

          _oEtDOB.setError("DOB cannot be in the future");

          return false;
        }

        if (_oSpnGender.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Please select a gender", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (_oEtHome.getText().toString().trim().isEmpty()) {
            _oEtHome.setError("Home required");
            return false;
        }
        return true;
    }
}
