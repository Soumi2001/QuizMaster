package com.example.quizmaster;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity {

    TextView Head1;
    AppCompatButton adddata, read, showpat, showuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        //getSupportActionBar().hide();

        Head1 = (TextView) findViewById(R.id.Head1);
        adddata =findViewById(R.id.addBtn);
        read = findViewById(R.id.viewBtn);
        showpat = findViewById(R.id.patviewBtn);
        showuser = findViewById(R.id.userviewBtn);

        Bundle bundle = getIntent().getExtras();
        String n = bundle.getString("nm");

        Head1.setText("Welcome! "+n);

        adddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext;
                inext  = new Intent(HomeScreen.this,AddActivity.class);
                startActivity(inext);
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext;
                inext  = new Intent(HomeScreen.this,Read_Data_Activity.class);
                startActivity(inext);
            }
        });

        showpat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext;
                inext  = new Intent(HomeScreen.this,ParticipantsList.class);
                startActivity(inext);
            }
        });

        showuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext;
                inext  = new Intent(HomeScreen.this,UserScore.class);
                startActivity(inext);
            }
        });

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomeScreen.this);
        alertDialog.setTitle("Exit App");
        alertDialog.setMessage("Do you want to exit app");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }
}