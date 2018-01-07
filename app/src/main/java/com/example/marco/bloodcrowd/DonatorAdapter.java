package com.example.marco.bloodcrowd;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by Marco on 07/01/2018.
 */

public class DonatorAdapter extends ArrayAdapter {

    private ArrayList<Donator> donatorArrayList;
    private int resource;
    private LayoutInflater inflater;

    public DonatorAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Donator> objects) {
        super(context, resource, objects);

        donatorArrayList = objects;
        this.resource = resource;
        inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(resource, null);

            TextView nomeText;
            TextView bloodText;
            TextView idText;

            nomeText = convertView.findViewById(R.id.nomeText);
            bloodText = convertView.findViewById(R.id.bloodText);
            idText = convertView.findViewById(R.id.idText);

            nomeText.setText(donatorArrayList.get(position).getFirstName() + " " +donatorArrayList.get(position).getLastName());
            bloodText.setText(donatorArrayList.get(position).getBloodType());
            idText.setText("id: "+donatorArrayList.get(position).getNumber());

        }
        return convertView;
    }
}
