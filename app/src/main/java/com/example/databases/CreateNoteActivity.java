package com.example.databases;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class CreateNoteActivity extends AppCompatActivity {

    MyStorage    storage;
    Date date = new Date();
    android.text.format.DateFormat df = new android.text.format.DateFormat();

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        storage= new MyStorage(this);
        try {

        }catch(Exception e){
            Toast.makeText(this,"No database yet",Toast.LENGTH_SHORT).show();
        }

        Button save_button   = findViewById(R.id.save_button);
        final EditText input_field    = findViewById(R.id.input_field);

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input_field.getText()!=null&&!(input_field.getText().toString().isEmpty())) {
                    storage.createNote(input_field.getText().toString(),"incomplete",String.valueOf(df.format("hh:mm dd-MM-yyyy",date)));
                    Toast.makeText(getApplicationContext(),"Note Saved Successfully",Toast.LENGTH_SHORT).show();
                    input_field.getText().clear();
                }else{
                    Toast.makeText(getApplicationContext(),"Please type in a Note to save first!!!",Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         super.onOptionsItemSelected(item);
       Intent intent = new Intent(this,EntryActivity.class);
        startActivity(intent);
        finish() ;
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,EntryActivity.class);
        startActivity(intent);
        finish() ;
    }
}