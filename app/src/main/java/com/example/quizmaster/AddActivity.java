package com.example.quizmaster;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class AddActivity extends AppCompatActivity {

    EditText questiontxt, opAtxt, opBtxt, opCtxt, opDtxt, anstxt;
    Spinner subtxt;
    Button addCbtn;
    int sub;
    TextView msg;
    ArrayAdapter<CharSequence> adapter;
    public static final String addurl = "https://soumitri.000webhostapp.com/QuizProject/Add_QNA.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        msg = (TextView) findViewById(R.id.msg);
        questiontxt = (EditText) findViewById(R.id.questiontxt);
        subtxt = (Spinner) findViewById(R.id.subtxt);
        opAtxt = (EditText) findViewById(R.id.opAtxt);
        opBtxt = (EditText) findViewById(R.id.opBtxt);
        opCtxt = (EditText) findViewById(R.id.opCtxt);
        opDtxt = (EditText) findViewById(R.id.opDtxt);
        anstxt = (EditText) findViewById(R.id.anstxt);

        addCbtn = (Button) findViewById(R.id.addCbtn);

        adapter=ArrayAdapter.createFromResource(this, R.array.subtxt, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        subtxt.setAdapter(adapter);

        addCbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();

                subtxt.setAdapter(adapter);
                questiontxt.setText("");
                opAtxt.setText("");
                opBtxt.setText("");
                opCtxt.setText("");
                opDtxt.setText("");
                anstxt.setText("");
            }
        });
    }

    private void insertData() {


        //String sub = subtxt.getText().toString().trim();
        String subject = subtxt.getSelectedItem().toString();
        String ques = questiontxt.getText().toString().trim();
        String o1 = opAtxt.getText().toString().trim();
        String o2 = opBtxt.getText().toString().trim();
        String o3 = opCtxt.getText().toString().trim();
        String o4 = opDtxt.getText().toString().trim();
        String ans = anstxt.getText().toString().trim();

        if(subject.equals("--Select Your Option--")){
            Toast.makeText(this, "Choose Subject", Toast.LENGTH_SHORT).show();
            return;
        }else if (subject.equals("C")){
            sub = 1;
        }else if (subject.equals("C++")){
            sub = 2;
        }else if (subject.equals("Java")){
            sub = 3;
        }else if (subject.equals("Python")){
            sub = 4;
        }
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading....");

            if(ques.isEmpty()){
                Toast.makeText(this, "Enter Question", Toast.LENGTH_SHORT).show();
                return;
            }
            else if(o1.isEmpty()){
                Toast.makeText(this, "Enter Option A", Toast.LENGTH_SHORT).show();
                return;
            }
            else if(o2.isEmpty()){
                Toast.makeText(this, "Enter option B", Toast.LENGTH_SHORT).show();
                return;
            }
            else if(o3.isEmpty()){
                Toast.makeText(this, "Enter option C", Toast.LENGTH_SHORT).show();
                return;
            }
            else if(o4.isEmpty()){
                Toast.makeText(this, "Enter option D", Toast.LENGTH_SHORT).show();
                return;
            }
            else if(ans.isEmpty()){
                Toast.makeText(this, "Enter Answer", Toast.LENGTH_SHORT).show();
                return;
            } else {
                progressDialog.show();
                StringRequest request = new StringRequest(Request.Method.POST, addurl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equalsIgnoreCase("inserted")){
                            Toast.makeText(AddActivity.this, "inserted", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                        else {
                            Toast.makeText(AddActivity.this,response, Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String,String> params = new HashMap<String,String>();
                        params.put("Question",ques);
                        params.put("OptionA",o1);
                        params.put("OptionB",o2);
                        params.put("OptionC",o3);
                        params.put("OptionD",o4);
                        params.put("Answer", ans);
                        params.put("Subject", ""+sub);

                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(AddActivity.this);
                requestQueue.add(request);


            }


    }
}
