package com.example.condolencehelper;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class AttendantsActivity extends AppCompatActivity {
    TextView text_attendants_count;
    ListView list_attendants;

    MyDBHelper myDBHelper;
    SQLiteDatabase database;
    AttendantsList attendantsList;
    ArrayList<AttendantsList> attendantsLists = new ArrayList<>();
    AttendantsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendants);
        myDBHelper = new MyDBHelper(this);
        text_attendants_count = (TextView)findViewById(R.id.text_attendants_count);
        list_attendants = (ListView)findViewById(R.id.list_attendants);
        database =myDBHelper.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(database,"attendants");
        Cursor cursor;
        try{
            cursor = database.rawQuery("select name, message from attendants;",null);
            cursor.moveToFirst();
            while(cursor.moveToNext()){
                attendantsList = new AttendantsList(cursor.getString(0),cursor.getString(1));
                attendantsLists.add(attendantsList);
            }
        } catch (Exception e){
            Toast.makeText(this, "Failed to read from database", Toast.LENGTH_SHORT).show();
        }
        text_attendants_count.setText(count+" People attended");
        adapter = new AttendantsAdapter(this, attendantsLists);
        list_attendants.setAdapter(adapter);
    }
}