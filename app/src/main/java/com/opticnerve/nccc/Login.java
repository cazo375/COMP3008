package com.opticnerve.nccc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Random;

public class Login extends AppCompatActivity {

    private TimePicker timePicker1;
    private TimePicker timePicker2;
    private TimePicker timePicker3;
    private TimePicker timePicker4;
    private Context context;

    private Button clock1;
    private Button clock2;
    private Button clock3;
    private Button clock4;

    private Button proceed_button;
    private Button check_button;

    private Random rand;
    private TextView password;
    private TextView current_password;
    private TextView password_check;
    private TextView password_type;

    private int pass_type;
    private static boolean isClicked = false;
    final int[] test_password = new int[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        rand = new Random();
        readPassword();
        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        timePicker1.setIs24HourView(true);
        timePicker1.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener(){
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                updateCurrentPasswordText();
            }
        });

        timePicker2 = (TimePicker) findViewById(R.id.timePicker2);
        timePicker2.setIs24HourView(true);
        timePicker2.setVisibility(View.INVISIBLE);
        timePicker2.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener(){
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                updateCurrentPasswordText();
            }
        });

        timePicker3 = (TimePicker) findViewById(R.id.timePicker3);
        timePicker3.setIs24HourView(true);
        timePicker3.setVisibility(View.INVISIBLE);
        timePicker3.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener(){
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                updateCurrentPasswordText();
            }
        });

        timePicker4 = (TimePicker) findViewById(R.id.timePicker4);
        timePicker4.setIs24HourView(true);
        timePicker4.setVisibility(View.INVISIBLE);
        timePicker4.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener(){
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                updateCurrentPasswordText();
            }
        });


        clock1 = (Button)findViewById(R.id.button);
        clock1.setBackgroundColor(Color.RED);
        clock1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timePicker1.getVisibility() == View.INVISIBLE) {
                    clock1.setBackgroundColor(Color.RED);
                    clock2.setBackgroundColor(Color.WHITE);
                    clock3.setBackgroundColor(Color.WHITE);
                    clock4.setBackgroundColor(Color.WHITE);
                    timePicker1.setVisibility(View.VISIBLE);
                    timePicker2.setVisibility(View.INVISIBLE);
                    timePicker3.setVisibility(View.INVISIBLE);
                    timePicker4.setVisibility(View.INVISIBLE);
                }
            }
        });

        clock2 = (Button)findViewById(R.id.button2);
        clock2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timePicker2.getVisibility() == View.INVISIBLE) {
                    clock1.setBackgroundColor(Color.WHITE);
                    clock2.setBackgroundColor(Color.RED);
                    clock3.setBackgroundColor(Color.WHITE);
                    clock4.setBackgroundColor(Color.WHITE);
                    timePicker1.setVisibility(View.INVISIBLE);
                    timePicker2.setVisibility(View.VISIBLE);
                    timePicker3.setVisibility(View.INVISIBLE);
                    timePicker4.setVisibility(View.INVISIBLE);
                }
            }
        });

        clock3 = (Button)findViewById(R.id.button3);
        clock3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timePicker3.getVisibility() == View.INVISIBLE) {
                    clock1.setBackgroundColor(Color.WHITE);
                    clock2.setBackgroundColor(Color.WHITE);
                    clock3.setBackgroundColor(Color.RED);
                    clock4.setBackgroundColor(Color.WHITE);
                    timePicker1.setVisibility(View.INVISIBLE);
                    timePicker2.setVisibility(View.INVISIBLE);
                    timePicker3.setVisibility(View.VISIBLE);
                    timePicker4.setVisibility(View.INVISIBLE);
                }
            }
        });

        clock4 = (Button)findViewById(R.id.button4);
        clock4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timePicker4.getVisibility() == View.INVISIBLE) {
                    clock1.setBackgroundColor(Color.WHITE);
                    clock2.setBackgroundColor(Color.WHITE);
                    clock3.setBackgroundColor(Color.WHITE);
                    clock4.setBackgroundColor(Color.RED);
                    timePicker1.setVisibility(View.INVISIBLE);
                    timePicker2.setVisibility(View.INVISIBLE);
                    timePicker3.setVisibility(View.INVISIBLE);
                    timePicker4.setVisibility(View.VISIBLE);
                }
            }
        });



        setTime();
        current_password = (TextView) findViewById(R.id.CurrentPassword);
        String new_output = "Current Input: " + timePicker1.getCurrentHour() + ":" + timePicker1.getCurrentMinute()
                + ",    " + timePicker2.getCurrentHour() + ":" + timePicker2.getCurrentMinute()
                + ",    " + timePicker3.getCurrentHour() + ":" + timePicker3.getCurrentMinute()
                + ",    " + timePicker4.getCurrentHour() + ":" + timePicker4.getCurrentMinute();
        current_password.setText(new_output, TextView.BufferType.SPANNABLE);


        password = (TextView) findViewById(R.id.passwordTextView);
        int[] old_pass_out = passCreator();
        String old_output = "Goal: " + old_pass_out[0] + ":" + old_pass_out[1] + ",    " + old_pass_out[2] + ":" + old_pass_out[3] + ",    " + old_pass_out[4] + ":" + old_pass_out[5] + ",    " + old_pass_out[6] + ":" + old_pass_out[7];

        //String temp = getFilesDir().getAbsolutePath();
        password.setText(old_output, TextView.BufferType.SPANNABLE);
        final String filename = old_pass_out[0] + ":" + old_pass_out[1] + ",    " + old_pass_out[2] + ":" + old_pass_out[3] + ",    " + old_pass_out[4] + ":" + old_pass_out[5] + ",    " + old_pass_out[6] + ":" + old_pass_out[7];
        proceed_button = (Button)findViewById(R.id.ProceedButton);
        proceed_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Login.class));
                isClicked = false;
            }
        });

        check_button = (Button)findViewById(R.id.CheckButton);
        password_check = (TextView) findViewById(R.id.PasswordCheck);
        check_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isClicked) {
                    String temp;
                    if (comparePassword(test_password)) {
                        temp = "true";
                        password_check.setTextColor(Color.GREEN);
                    } else {
                        temp = "false";
                        password_check.setTextColor(Color.RED);
                    }
                    isClicked = true;
                    password_check.setText(temp, TextView.BufferType.SPANNABLE);
                }


            }
        });

        password_type = (TextView)findViewById(R.id.PasswordType);
        String type = "";
        if(pass_type==0) {
            type = "Gmail";
        }
        if(pass_type==1) {
            type = "Facebook";
        }
        if(pass_type==2) {
            type = "Bank";
        }
        password_type.setText(type, TextView.BufferType.SPANNABLE);


    }

    public void setTime() {
        timePicker1.setCurrentHour(hourCreator());
        timePicker1.setCurrentMinute( minCreator());

        timePicker2.setCurrentHour(hourCreator());
        timePicker2.setCurrentMinute( minCreator());

        timePicker3.setCurrentHour(hourCreator());
        timePicker3.setCurrentMinute( minCreator());

        timePicker4.setCurrentHour(hourCreator());
        timePicker4.setCurrentMinute( minCreator());

    }

    public int hourCreator(){
        int  hour = rand.nextInt(24) + 1;
        return hour;
    }

    public int minCreator(){
        int  min = rand.nextInt(59);
        return min;
    }

    public int[] passCreator(){
        int[] pass = new int[8];
        for(int i = 0; i < 8; i++) {
            if(i%2 == 0) {
                pass[i] = rand.nextInt(12) + 1;
            }else{
                pass[i] = rand.nextInt(59);
            }
        }
        return pass;
    }

    public boolean comparePassword(int[] true_pass){
        int [] curr_pass = new int[8];
        curr_pass[0] = timePicker1.getCurrentHour(); curr_pass[1] = timePicker1.getCurrentMinute();

        curr_pass[2] = timePicker2.getCurrentHour(); curr_pass[3] = timePicker2.getCurrentMinute();

        curr_pass[4] = timePicker3.getCurrentHour(); curr_pass[5] = timePicker3.getCurrentMinute();

        curr_pass[6] = timePicker4.getCurrentHour(); curr_pass[7] = timePicker4.getCurrentMinute();

        for(int i = 0; i<8; i++){
            if(curr_pass[i] != true_pass[i]){
                return false;
            }
        }
        return true;
    }

    public void updateCurrentPasswordText(){
        current_password = (TextView) findViewById(R.id.CurrentPassword);
        String new_output = "Current Input: " + timePicker1.getCurrentHour() + ":" + timePicker1.getCurrentMinute()
                + ",    " + timePicker2.getCurrentHour() + ":" + timePicker2.getCurrentMinute()
                + ",    " + timePicker3.getCurrentHour() + ":" + timePicker3.getCurrentMinute()
                + ",    " + timePicker4.getCurrentHour() + ":" + timePicker4.getCurrentMinute();
        current_password.setText(new_output);


    }

    public void readPassword(){
        String filename = "";
        pass_type = rand.nextInt(3) + 1;
        if(pass_type==0) {
            filename = "Gmail";
        }
        if(pass_type==1) {
            filename = "Facebook";
        }
        if(pass_type==2) {
            filename = "Bank";
        }

        try {
            FileInputStream fin = openFileInput(filename);
            int c;
            String temp = "";
            while ((c = fin.read()) != -1) {
                temp = temp + Character.toString((char) c);
            }
            int foot = 0;
            int head = temp.indexOf(",");
            test_password[0] = Integer.parseInt(temp.substring(foot, head));

            foot = head;
            head = temp.indexOf(",", foot+1);
            test_password[1] = Integer.parseInt(temp.substring(foot+1, head));

            foot = head;
            head = temp.indexOf(",", foot+1);
            test_password[2] = Integer.parseInt(temp.substring(foot+1, head));

            foot = head;
            head = temp.indexOf(",", foot+1);
            test_password[3] = Integer.parseInt(temp.substring(foot+1, head));

            foot = head;
            head = temp.indexOf(",", foot+1);
            test_password[4] = Integer.parseInt(temp.substring(foot+1, head));

            foot = head;
            head = temp.indexOf(",", foot+1);
            test_password[5] = Integer.parseInt(temp.substring(foot+1, head));

            foot = head;
            head = temp.indexOf(",", foot+1);
            test_password[6] = Integer.parseInt(temp.substring(foot+1, head));

            foot = head;
            head = temp.indexOf("\n", foot+1);
            test_password[7] = Integer.parseInt(temp.substring(foot+1, head));

            password.setText(temp, TextView.BufferType.SPANNABLE);
            Toast.makeText(getBaseContext(), "file read", Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
