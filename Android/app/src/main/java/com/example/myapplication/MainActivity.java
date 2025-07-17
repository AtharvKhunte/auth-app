package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Button _oBtnShowDetails;
    TextView _oTvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _oBtnShowDetails = findViewById(R.id.btn_show_details);
        _oTvWelcome = findViewById(R.id.tv_welcome);
        sharedPreferences = getSharedPreferences(SignUpActivity.PREF_NAME, MODE_PRIVATE);

        String name = sharedPreferences.getString("name", "User");
        _oTvWelcome.setText("Welcome, " + name + "!");

      _oBtnShowDetails.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              String details = "Name: " + sharedPreferences.getString("name", "") + "\n" +
                      "Mobile: " + sharedPreferences.getString("mobile", "") + "\n" +
                      "DOB: " + sharedPreferences.getString("dob", "") + "\n" +
                      "Gender: " + sharedPreferences.getString("gender", "") + "\n" +
                      "Home: " + sharedPreferences.getString("home", "");

              UserDetailsBottomSheet bottomSheet = new UserDetailsBottomSheet(details);
              bottomSheet.show(getSupportFragmentManager(), "UserDetails");
          }
      });
    }
}

