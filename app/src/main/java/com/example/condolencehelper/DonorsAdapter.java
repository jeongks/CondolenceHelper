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

public class DonorsAdapter extends ArrayAdapter<DonorsList> {
    public DonorsAdapter(Context context, ArrayList<DonorsList> donorsList) {
        super(context, 0,donorsList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DonorsList donorsList = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.donors_list,parent,false);
        }
        TextView text_donors_list_name = (TextView) convertView.findViewById(R.id.text_donors_list_name);
        TextView text_donors_list_donation = (TextView) convertView.findViewById(R.id.text_donors_list_donation);

        text_donors_list_name.setText(donorsList.name);
        text_donors_list_donation.setText("$"+donorsList.donation);

        return convertView;
    }
}
