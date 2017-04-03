package com.opticnerve.nccc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntroPage extends AppCompatActivity {

    private Button login_button;
    private Button create_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_page);

        create_button = (Button)findViewById(R.id.Create_Button);
        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroPage.this, MainActivity.class));
            }
        });

        login_button = (Button)findViewById(R.id.Login_Button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroPage.this, Login.class));
            }
        });


    }
}
