package com.opticnerve.nccc;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends Activity {
    private TimePicker timePicker1;
    private TimePicker timePicker2;
    private TimePicker timePicker3;
    private TimePicker timePicker4;
    private Context context;

    private Button clock1;
    private Button clock2;
    private Button clock3;
    private Button clock4;

    private Button saveButton;

    private Random rand;
    private TextView password;
    private TextView current_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        rand = new Random();

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
        saveButton = (Button)findViewById(R.id.Save_Button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // File file = new File("/storage/emulated/0", "/storage/emulated/0/TESTERINO");
                String filename = "TESTERINO";
                String string = "Hello world!";
                FileOutputStream outputStream;

                try {
                    //File file = new File("/storage/emulated/0", "/storage/emulated/0/TESTERINO");

                    outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                    outputStream.write(string.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

//                try {
//                    FileInputStream fin = openFileInput(filename);
//                    int c;
//                    String temp = "";
//                    while ((c = fin.read()) != -1) {
//                        temp = temp + Character.toString((char) c);
//                    }
//                    //password.setText(temp, TextView.BufferType.SPANNABLE);
//                    Toast.makeText(getBaseContext(), "file read", Toast.LENGTH_SHORT).show();
//                } catch (Exception e){
//                    e.printStackTrace();
//                }
            }
        });

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

    public boolean comparePassword(){
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

}