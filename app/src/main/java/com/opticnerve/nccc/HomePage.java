package com.opticnerve.nccc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    private Button login_button;
    private Button practice_button;
    private static User user;

    private TextView username_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        username_textview = (TextView)findViewById(R.id.Username);

        String name = "Username: ";
        User data = new User();
        name = name + data.getUser_name();
        username_textview.setText(name, TextView.BufferType.SPANNABLE);

        practice_button = (Button)findViewById(R.id.Practice_Button);
        practice_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, MainActivity.class));
            }
        });

        login_button = (Button)findViewById(R.id.Login_Button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, Login.class));
            }
        });

    }
}
