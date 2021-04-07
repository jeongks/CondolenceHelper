package com.example.condolencehelper;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class DonationStatusActivity extends AppCompatActivity {
    TextView text_donate_status_total;

    SQLiteDatabase database;
    MyDBHelper myDBHelper;

    /**
     * onCreate method that starts the application.
     * In this method, all the donation is calculated and accumulated.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_status);
        myDBHelper = new MyDBHelper(this);
        text_donate_status_total = (TextView)findViewById(R.id.text_donate_status_total);
        float total = 0;
        database = myDBHelper.getReadableDatabase();
        Cursor cursor;
        try{
            cursor = database.rawQuery("select donate from attendants;",null);
            cursor.moveToFirst();
            while(cursor.moveToNext()){
                total += cursor.getFloat(0);
            }
        }catch (Exception e){
            Toast.makeText(this, "Failed to get data from database", Toast.LENGTH_SHORT).show();
        }
        text_donate_status_total.setText("$ "+total);
    }
}