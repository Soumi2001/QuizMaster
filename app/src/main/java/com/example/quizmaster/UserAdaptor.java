package com.example.quizmaster;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UserAdaptor extends ArrayAdapter {
    ArrayList<UserClass> details;
    AppCompatActivity context;
    public UserAdaptor(AppCompatActivity context, ArrayList<UserClass> details) {
        super(context, 0, details);
        this.context = context;
        this.details = details;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        UserClass d = details.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.customuserlist, parent, false);
        }
        // Lookup view for data population
        TextView nametxt = (TextView) convertView.findViewById(R.id.namtxt);
        TextView markstxt = (TextView) convertView.findViewById(R.id.markstxt);
        TextView subtxt = (TextView) convertView.findViewById(R.id.subtxt);
        TextView datetxt = (TextView) convertView.findViewById(R.id.datetxt);
        // Populate the data into the template view using the data object
        nametxt.setText("Name: "+d.getName());
        nametxt.setTextColor(Color.RED);
        markstxt.setText("Marks: "+d.getMarks());
        subtxt.setText("Subject: "+d.getSubject());
        datetxt.setText("Date: "+d.getExamdate());

        // Return the completed view to render on screen
        return convertView;
    }
}

