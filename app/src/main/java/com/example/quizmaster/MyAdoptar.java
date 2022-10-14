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

public class MyAdoptar extends ArrayAdapter<model> {

    AppCompatActivity context;
    ArrayList<model> details;

    public MyAdoptar(AppCompatActivity context, ArrayList<model> details) {
        super(context,0,details);
        this.context = context;
        this.details = details;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        model m = details.get(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.coustom_view,parent,false);
        }

        TextView tview = convertView.findViewById(R.id.tview);

        tview.setText(m.getQuestion());

        return convertView;
    }
}

