package com.opticnerve.nccc;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

public class EndScreen extends AppCompatActivity {

    private Button finish_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);


        finish_button = (Button) findViewById(R.id.Finish_Button);
        finish_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EndScreen.this, StartPage.class));
            }
        });
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
    }}
