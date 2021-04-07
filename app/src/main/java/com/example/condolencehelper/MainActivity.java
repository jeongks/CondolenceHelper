package com.example.condolencehelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edit_home_name, edit_home_message, edit_home_donate;
    Button button_home_save, button_home_clear, button_home_attendants, button_home_donors, button_home_donation;

    SQLiteDatabase database;
    MyDBHelper myDBHelper;
    private static String[] PERMISSION_STORAGE={
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    /**
     * First life cycle of the application.
     * all the elements are being instantiated.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verifyStoragePermission(MainActivity.this);
        myDBHelper = new MyDBHelper(this);
        edit_home_name = (EditText)findViewById(R.id.edit_home_name);
        edit_home_donate = (EditText)findViewById(R.id.edit_home_donate);
        edit_home_message = (EditText)findViewById(R.id.edit_home_message);
        button_home_save =(Button)findViewById(R.id.button_home_save);
        button_home_clear =(Button)findViewById(R.id.button_home_clear);
        button_home_attendants=(Button)findViewById(R.id.button_home_attendants);
        button_home_donors=(Button)findViewById(R.id.button_home_donors);
        button_home_donation=(Button)findViewById(R.id.button_home_donation);


        /**
         * Event listener for the click event on the save button to save all the input
         */
        button_home_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = myDBHelper.getWritableDatabase();
                try{
                    database.execSQL("insert into attendants (name, message, donate) values ('"+edit_home_name.getText().toString()+"', '"+edit_home_message.getText().toString()+"',"+Float.parseFloat(edit_home_donate.getText().toString())+")");
                    edit_home_name.getText().clear();
                    edit_home_message.getText().clear();
                    edit_home_donate.getText().clear();
                    Toast.makeText(MainActivity.this, "Save Completed", Toast.LENGTH_SHORT).show();
                } catch(NumberFormatException e){
                    Toast.makeText(MainActivity.this, "donate input should be in number with 2 decimal point", Toast.LENGTH_SHORT).show();
                } catch(Exception e){
                    Toast.makeText(MainActivity.this, "Failed to save", Toast.LENGTH_SHORT).show();
                }
            }
        });
        /**
         * handleing the event of clicking clear button. This will clear the inputs.
         */
        button_home_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_home_name.getText().clear();
                edit_home_message.getText().clear();
                edit_home_donate.getText().clear();
            }
        });
        /**
         * click event handler for displaying attendants.
         * Open and start another activity called AttendantsActivity.
         */
        button_home_attendants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AttendantsActivity.class);
                startActivity(intent);
            }
        });
        /**
         * click event handler for displaying donors.
         * open and start another activity called DonorsActivity.
         */
        button_home_donors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DonorsActivity.class);
                startActivity(intent);
            }
        });
        /**
         * click event handler for displaying total amount of donation.
         * open and start another activity called DonationStatusActivity.
         */
        button_home_donation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DonationStatusActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * verify the permission is grated or not.
     * the permission is reading and writing to the external storage of the device.
     * @param activity 
     */
    public static void verifyStoragePermission(Activity activity){
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity, PERMISSION_STORAGE,1);
        }
    }
}