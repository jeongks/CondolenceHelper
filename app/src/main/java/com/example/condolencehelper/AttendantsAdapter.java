package com.example.condolencehelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.ArrayList;

public class AttendantsAdapter extends ArrayAdapter<AttendantsList> {

    public AttendantsAdapter(Context context, ArrayList<AttendantsList> attendantsLists) {
        super(context,0, attendantsLists);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AttendantsList attendantsList = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.attendants_list, parent ,false);
        }
        TextView text_attendants_list_name = (TextView) convertView.findViewById(R.id.text_attendants_list_name);
        TextView text_attendants_list_message = (TextView) convertView.findViewById(R.id.text_attendants_list_message);

        text_attendants_list_name.setText(attendantsList.name);
        text_attendants_list_message.setText(attendantsList.message);
        return convertView;
    }
}
