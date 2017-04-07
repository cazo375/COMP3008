/*
StartPage.java is the initializer of our app

Enters from End Screen

This page welcomes the participant and creates a new user object upon clicking begin.

Exits to home screen
 */



package com.opticnerve.nccc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class StartPage extends AppCompatActivity {

    private Button begin_button;
    private static int use_count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        begin_button = (Button) findViewById(R.id.Begin_Button);
        begin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_name = "user" + String.valueOf(use_count++);
                User new_user = new User(new_name); // init new user
                startActivity(new Intent(StartPage.this, HomePage.class));
            }
        });


    }
}
