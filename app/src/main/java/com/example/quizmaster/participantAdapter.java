package com.example.quizmaster;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class participantAdapter extends ArrayAdapter {
    ArrayList<modelClass> details;
    AppCompatActivity context;
    public participantAdapter(AppCompatActivity context, ArrayList<modelClass> details) {
        super(context, 0, details);
        this.context = context;
        this.details = details;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        modelClass d = details.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.customparticipantslist, parent, false);
        }
        // Lookup view for data population
        TextView nametxt = (TextView) convertView.findViewById(R.id.namtxt);
        TextView contacttxt = (TextView) convertView.findViewById(R.id.contacttxt);
        TextView emailtxt = (TextView) convertView.findViewById(R.id.emailtxt);
        TextView passwordtxt = (TextView) convertView.findViewById(R.id.passwordtxt);
        // Populate the data into the template view using the data object
        nametxt.setText("Name: "+d.getName());
        contacttxt.setText("Ph. no.: "+d.getContact());
        emailtxt.setText("Email: "+d.getEmail());
        passwordtxt.setText("Password: "+d.getPassword());

        // Return the completed view to render on screen
        return convertView;
    }
}
