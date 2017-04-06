package com.opticnerve.nccc;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.io.FileWriter;
import java.io.Writer;
import java.util.Calendar;
import java.util.Random;

public class Login extends AppCompatActivity {

    private TimePicker clockFace1;
    private TimePicker clockFace2;
    private TimePicker clockFace3;
    private TimePicker clockFace4;

    private Button clock1;
    private Button clock2;
    private Button clock3;
    private Button clock4;

    private Button proceed_button;
    private Button check_button;

    private TextView curr_pass;
    private TextView password_check;
    private TextView password_type;

    private String start_time = "";
    private String end_time = "";
    private Calendar calendar;
    private Random rand;
    private int pass_type;
    private int current_attempts;
    private boolean login_success = false;
    private static int test_number = 0;
    private static int script_counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        start_time = getCurrentTime();
        rand = new Random();
        TestScript scripter = new TestScript();
        if(test_number == 0){
            scripter.generateScript();
        }

        pass_type = scripter.getTest_order()[test_number];
        current_attempts = 0;
        // init clock faces
        clockFace1 = (TimePicker) findViewById(R.id.Login_Clock1);
        clockFace1.setIs24HourView(true);
        clockFace1.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener(){
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                //updateCurrentLoginPasswordText();
            }
        });

        clockFace2 = (TimePicker) findViewById(R.id.Login_Clock2);
        clockFace2.setIs24HourView(true);
        clockFace2.setVisibility(View.INVISIBLE);
        clockFace2.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener(){
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                //updateCurrentLoginPasswordText();
            }
        });

        clockFace3 = (TimePicker) findViewById(R.id.Login_Clock3);
        clockFace3.setIs24HourView(true);
        clockFace3.setVisibility(View.INVISIBLE);
        clockFace3.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener(){
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                //updateCurrentLoginPasswordText();
            }
        });

        clockFace4 = (TimePicker) findViewById(R.id.Login_Clock4);
        clockFace4.setIs24HourView(true);
        clockFace4.setVisibility(View.INVISIBLE);
        clockFace4.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener(){
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                //updateCurrentLoginPasswordText();
            }
        });


        //init buttons for selecting clock faces
        clock1 = (Button)findViewById(R.id.Clock_Button1);
        clock1.setBackgroundColor(Color.RED);
        clock1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clockFace1.getVisibility() == View.INVISIBLE) {
                    clock1.setBackgroundColor(Color.RED);
                    clock2.setBackgroundColor(Color.WHITE);
                    clock3.setBackgroundColor(Color.WHITE);
                    clock4.setBackgroundColor(Color.WHITE);
                    clockFace1.setVisibility(View.VISIBLE);
                    clockFace2.setVisibility(View.INVISIBLE);
                    clockFace3.setVisibility(View.INVISIBLE);
                    clockFace4.setVisibility(View.INVISIBLE);
                }
            }
        });

        clock2 = (Button)findViewById(R.id.Clock_Button2);
        clock2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clockFace2.getVisibility() == View.INVISIBLE) {
                    clock1.setBackgroundColor(Color.WHITE);
                    clock2.setBackgroundColor(Color.RED);
                    clock3.setBackgroundColor(Color.WHITE);
                    clock4.setBackgroundColor(Color.WHITE);
                    clockFace1.setVisibility(View.INVISIBLE);
                    clockFace2.setVisibility(View.VISIBLE);
                    clockFace3.setVisibility(View.INVISIBLE);
                    clockFace4.setVisibility(View.INVISIBLE);
                }
            }
        });

        clock3 = (Button)findViewById(R.id.Clock_Button3);
        clock3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clockFace3.getVisibility() == View.INVISIBLE) {
                    clock1.setBackgroundColor(Color.WHITE);
                    clock2.setBackgroundColor(Color.WHITE);
                    clock3.setBackgroundColor(Color.RED);
                    clock4.setBackgroundColor(Color.WHITE);
                    clockFace1.setVisibility(View.INVISIBLE);
                    clockFace2.setVisibility(View.INVISIBLE);
                    clockFace3.setVisibility(View.VISIBLE);
                    clockFace4.setVisibility(View.INVISIBLE);
                }
            }
        });

        clock4 = (Button)findViewById(R.id.Clock_Button4);
        clock4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clockFace4.getVisibility() == View.INVISIBLE) {
                    clock1.setBackgroundColor(Color.WHITE);
                    clock2.setBackgroundColor(Color.WHITE);
                    clock3.setBackgroundColor(Color.WHITE);
                    clock4.setBackgroundColor(Color.RED);
                    clockFace1.setVisibility(View.INVISIBLE);
                    clockFace2.setVisibility(View.INVISIBLE);
                    clockFace3.setVisibility(View.INVISIBLE);
                    clockFace4.setVisibility(View.VISIBLE);
                }
            }
        });
        setTime();

        // init current password display
        /*curr_pass = (TextView) findViewById(R.id.Current_Password);
        String new_output = "Current: " + clockFace1.getCurrentHour() + ":" + clockFace1.getCurrentMinute()
                + ", " + clockFace2.getCurrentHour() + ":" + clockFace2.getCurrentMinute()
                + ", " + clockFace3.getCurrentHour() + ":" + clockFace3.getCurrentMinute()
                + ", " + clockFace4.getCurrentHour() + ":" + clockFace4.getCurrentMinute();
        curr_pass.setText(new_output, TextView.BufferType.SPANNABLE);*/

        //init password type
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

        // T or F password checker
        password_check = (TextView) findViewById(R.id.PasswordCheck);
        password_check.setText("", TextView.BufferType.SPANNABLE);
        password_check.setVisibility(View.INVISIBLE);


        //init action buttons
        proceed_button = (Button)findViewById(R.id.ProceedButton);
        proceed_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Login.class));
                User data = new User();
                if(end_time == ""){
                    end_time = getCurrentTime();
                }
                data.recordResults(pass_type, script_counter, current_attempts, login_success, start_time, end_time);
                if(test_number <2) {
                    test_number++;
                }
                else{
                    test_number = 0;
                    if(script_counter<2) {
                        script_counter++;
                    }
                    else {
                        script_counter = 0;
                        writeOut(data.printResults());
                        startActivity(new Intent(Login.this, EndScreen.class));
                    }
                }
            }
        });

        check_button = (Button)findViewById(R.id.CheckButton);
        check_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(current_attempts < 2) {
                    if(password_check.getVisibility() == View.INVISIBLE) {
                        password_check.setVisibility(View.VISIBLE);
                    }
                    String temp;
                    if (comparePassword()) {
                        temp = "Success";
                        login_success = true;
                        end_time = getCurrentTime();
                        password_check.setTextColor(Color.GREEN);
                    } else {
                        temp = "Fail x" + (current_attempts+1);
                        password_check.setTextColor(Color.RED);
                    }

                    password_check.setText(temp, TextView.BufferType.SPANNABLE);
                }
                current_attempts++;
            }
        });

    }

    public String getCurrentTime(){
        calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND);
    }

    public void setTime() {
        clockFace1.setCurrentHour(hourCreator());
        clockFace1.setCurrentMinute( minCreator());

        clockFace2.setCurrentHour(hourCreator());
        clockFace2.setCurrentMinute( minCreator());

        clockFace3.setCurrentHour(hourCreator());
        clockFace3.setCurrentMinute( minCreator());

        clockFace4.setCurrentHour(hourCreator());
        clockFace4.setCurrentMinute( minCreator());

    }

    public int hourCreator(){
        int  hour = rand.nextInt(24) + 1;
        return hour;
    }

    public int minCreator(){
        int  min = rand.nextInt(59);
        return min;
    }

    public boolean comparePassword(){
        int [] true_pass = new int[8];
        User data = new User();
        if(pass_type==0) {
            true_pass = data.getGmail_pass();
        }
        if(pass_type==1) {
            true_pass = data.getFacebook_pass();

        }
        if(pass_type==2) {
            true_pass = data.getBank_pass();
        }

        int [] curr_pass = new int[8];
        curr_pass[0] = clockFace1.getCurrentHour(); curr_pass[1] = clockFace1.getCurrentMinute();

        curr_pass[2] = clockFace2.getCurrentHour(); curr_pass[3] = clockFace2.getCurrentMinute();

        curr_pass[4] = clockFace3.getCurrentHour(); curr_pass[5] = clockFace3.getCurrentMinute();

        curr_pass[6] = clockFace4.getCurrentHour(); curr_pass[7] = clockFace4.getCurrentMinute();

        for(int i = 0; i<8; i++){
            if(curr_pass[i] != true_pass[i]){
                return false;
            }
        }
        return true;
    }

    public void updateCurrentLoginPasswordText(){
        /*curr_pass = (TextView) findViewById(R.id.Current_Password);
        String new_output = "Current: " + clockFace1.getCurrentHour() + ":" + clockFace1.getCurrentMinute()
                + ", " + clockFace2.getCurrentHour() + ":" + clockFace2.getCurrentMinute()
                + ", " + clockFace3.getCurrentHour() + ":" + clockFace3.getCurrentMinute()
                + ", " + clockFace4.getCurrentHour() + ":" + clockFace4.getCurrentMinute();
        curr_pass.setText(new_output);*/
    }


    public void writeOut(String out){
        Boolean flag = isExternalStorageWritable();
        Boolean flag2 = isExternalStorageReadable();
        //Log.e(LOG_TAG,"is it writable: "+flag);
        // Log.e(LOG_TAG,"is it readable: "+flag2);
        File root = new File(Environment.getExternalStorageDirectory().getAbsoluteFile(),"Clock_Lock_Output.txt");
        Writer writer = null;
        try {
            writer = new FileWriter(root,true);
            writer.append(out + "\n\n\n");
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeOut(){
        Boolean flag = isExternalStorageWritable();
        Boolean flag2 = isExternalStorageReadable();
        //Log.e(LOG_TAG,"is it writable: "+flag);
       // Log.e(LOG_TAG,"is it readable: "+flag2);
        File root = new File(Environment.getExternalStorageDirectory().getAbsoluteFile(),"testing.txt");
        Writer writer = null;
         try {
             writer = new FileWriter(root,true);
             writer.append("Testerino\n");
             writer.flush();
             writer.close();
         } catch (Exception e) {
             e.printStackTrace();
         }
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
            Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

}
