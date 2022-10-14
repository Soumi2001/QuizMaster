package com.example.quizmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Read_Data_Activity extends AppCompatActivity implements View.OnClickListener{

    CardView coption, cplusop, javaop, pythonop;
    String sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_data);

        coption = (CardView) findViewById(R.id.coption);
        cplusop = (CardView) findViewById(R.id.cplusop);
        javaop = (CardView) findViewById(R.id.javaop);
        pythonop = (CardView) findViewById(R.id.pythonop);

        coption.setOnClickListener(this);
        cplusop.setOnClickListener(this);
        javaop.setOnClickListener(this);
        pythonop.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){
            case R.id.coption:
                i = new Intent(Read_Data_Activity.this, showActivity.class);
                i.putExtra("subject", "C");
                i.putExtra("table", "Cqna");
                startActivity(i);
                Toast.makeText(Read_Data_Activity.this, "welcome to C Quiz!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cplusop:
                i = new Intent(Read_Data_Activity.this, showActivity.class);
                i.putExtra("subject", "C++");
                i.putExtra("table", "Cplusqna");
                startActivity(i);
                Toast.makeText(Read_Data_Activity.this, "welcome to C++ Quiz!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.javaop:
                i = new Intent(Read_Data_Activity.this, showActivity.class);
                i.putExtra("subject", "Java");
                i.putExtra("table", "JAVAqna");
                startActivity(i);
                Toast.makeText(Read_Data_Activity.this, "welcome to JAVA Quiz!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pythonop:
                i = new Intent(Read_Data_Activity.this, showActivity.class);
                i.putExtra("subject", "Python");
                i.putExtra("table", "PYTHONqna");
                startActivity(i);
                Toast.makeText(Read_Data_Activity.this, "welcome to Python Quiz!", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}