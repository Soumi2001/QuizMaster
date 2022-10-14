package com.example.quizmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DeleteActivity extends AppCompatActivity {

    TextView ques,vop1,vop2,vop3,vop4, vans, Head4;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        Head4 = findViewById(R.id.Head4);
        Bundle bundle = getIntent().getExtras();
        String n = bundle.getString("subject");

        Head4.setText(n+" QNA");

        ques = findViewById(R.id.ques);
        vop1 = findViewById(R.id.vop1);
        vop2 = findViewById(R.id.vop2);
        vop3 = findViewById(R.id.vop3);
        vop4 = findViewById(R.id.vop4);
        vans = findViewById(R.id.vans);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        ques.setText("Question:\n "+showActivity.details.get(position).getQuestion());
        vop1.setText("Option A:\n "+showActivity.details.get(position).getOpt1());
        vop2.setText("Option B:\n "+showActivity.details.get(position).getOpt2());
        vop3.setText("Option C:\n "+showActivity.details.get(position).getOpt3());
        vop4.setText("Option D:\n "+showActivity.details.get(position).getOpt4());
        vans.setText("Answer:\n "+showActivity.details.get(position).getAns());

    }
}