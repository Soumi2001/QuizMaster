package com.example.quizmaster;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class EditActivity extends AppCompatActivity {

    EditText upque,upopt1,upopt2,upopt3,upopt4, upans;
    TextView upid, Head3;
    Button upbtn;
    public static final String editurl = "https://soumitri.000webhostapp.com/QuizProject/Update_all_qna.php";

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Head3 = findViewById(R.id.Head3);

        Bundle bundle = getIntent().getExtras();
        String n = bundle.getString("subject");

        Head3.setText(n+" QNA Edit");

        ProgressDialog progressDialog = new ProgressDialog(EditActivity.this);
        progressDialog.setMessage("Loading..."); // Setting Message
        progressDialog.setTitle("Question & Answer List"); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(false);

        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
        }).start();

        upid = findViewById(R.id.upid);
        upque = findViewById(R.id.upque);
        upopt1 = findViewById(R.id.upopt1);
        upopt2 = findViewById(R.id.upopt2);
        upopt3 = findViewById(R.id.upopt3);
        upopt4 = findViewById(R.id.upopt4);
        upans = findViewById(R.id.upans);
        upbtn = findViewById(R.id.upbtn);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        upid.setText(showActivity.details.get(position).getId());
        upque.setText(showActivity.details.get(position).getQuestion());
        upopt1.setText(showActivity.details.get(position).getOpt1());
        upopt2.setText(showActivity.details.get(position).getOpt2());
        upopt3.setText(showActivity.details.get(position).getOpt3());
        upopt4.setText(showActivity.details.get(position).getOpt4());
        upans.setText(showActivity.details.get(position).getAns());

    }

    public void btn_updata(View view) {

        String ans = upans.getText().toString();
        String question = upque.getText().toString();
        String opt1 = upopt1.getText().toString();
        String opt2 = upopt2.getText().toString();
        String opt3 = upopt3.getText().toString();
        String opt4 = upopt4.getText().toString();
        String id = upid.getText().toString();

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating.....");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, editurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(EditActivity.this, "Data Updated!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),showActivity.class));
                finish();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(EditActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();
                params.put("ID",id);
                params.put("Question",question);
                params.put("OptionA",opt1);
                params.put("OptionB",opt2);
                params.put("OptionC",opt3);
                params.put("OptionD",opt4);
                params.put("Answer", ans);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(EditActivity.this);
        requestQueue.add(request);

    }
}
