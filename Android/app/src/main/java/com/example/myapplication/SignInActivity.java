package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {
    private EditText _oETMobile;
    private Button _oBtnSignIn;
    private SharedPreferences _oSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        _oETMobile = findViewById(R.id.et_mobile);
        _oBtnSignIn = findViewById(R.id.btn_signin);
        _oSharedPreferences = getSharedPreferences(SignUpActivity.PREF_NAME, MODE_PRIVATE);

        _oBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredMobile = _oETMobile.getText().toString().trim();


                if (enteredMobile.isEmpty()) {
                    _oETMobile.setError("Mobile number is required");
                    _oETMobile.requestFocus();
                    return;
                }


                if (!enteredMobile.matches("\\d{10}")) {
                    _oETMobile.setError("Enter a valid 10-digit mobile number");
                    _oETMobile.requestFocus();
                    return;
                }


                String savedMobile = _oSharedPreferences.getString("mobile", "");


                if (enteredMobile.equals(savedMobile)) {
                    startActivity(new Intent(SignInActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(SignInActivity.this, "Mobile number not found!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
