package com.example.condolencehelper;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DonorsActivity extends AppCompatActivity {
    TextView text_donors_count;
    ListView list_donors;

    SQLiteDatabase database;
    MyDBHelper myDBHelper;
    DonorsAdapter adapter;
    DonorsList donorsList;
    ArrayList<DonorsList> donorsLists = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donors);
        myDBHelper = new MyDBHelper(this);
        text_donors_count = (TextView)findViewById(R.id.text_donors_count);
        list_donors = (ListView)findViewById(R.id.list_donors);

        database = myDBHelper.getReadableDatabase();
        int count = 0;
        Cursor cursor;
        try {
            cursor = database.rawQuery("select name, donate from attendants where donate > 0;",null);
            cursor.moveToFirst();
            while(cursor.moveToNext()){
                donorsList = new DonorsList(cursor.getString(0),cursor.getFloat(1));
                count++;
                donorsLists.add(donorsList);
            }
        }catch (Exception e){
            Toast.makeText(this, "Failed to read from database", Toast.LENGTH_SHORT).show();
        }
        text_donors_count.setText(count+" People donated");
        adapter = new DonorsAdapter(this,donorsLists);
        list_donors.setAdapter(adapter);
    }
}