package com.example.quizmaster;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class showActivity extends AppCompatActivity {

    public static final String url = "https://soumitri.000webhostapp.com/QuizProject/Display_all_qna.php";
    public static final String deleteurl = "https://soumitri.000webhostapp.com/QuizProject/Delete_all_qna.php";

    ListView listData;
    RequestQueue reque;
    TextView Head2;
    int subcode;
    public static ArrayList<model> details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        listData = findViewById(R.id.listData);
        Head2 = findViewById(R.id.Head2);

        Bundle bundle = getIntent().getExtras();
        String n = bundle.getString("subject");

        subcode = 0;

        if (n.equals("C")){
            subcode = 1;
        }else if (n.equals("C++")){
            subcode = 2;
        }else if (n.equals("Java")){
            subcode = 3;
        }else if (n.equals("Python")){
            subcode = 4;
        }

        Head2.setText(n+" Question List");

        listData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = {"View Data","Edit Data","Delete Data"};
                builder.setTitle(details.get(position).getQuestion());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        switch (i){

                            case 0:
                                startActivity(new Intent(getApplicationContext(),DeleteActivity.class)
                                        .putExtra("position",position)
                                        .putExtra("subject",n));
                                break;
                            case 1:
                                startActivity(new Intent(getApplicationContext(),EditActivity.class)
                                        .putExtra("position",position)
                                        .putExtra("subject",n));
                                break;
                            case 2:
                                deleteData(details.get(position).getId());
                                break;
                        }

                    }

                });

                builder.create().show();


            }
        });

        ProgressDialog progressDialog = new ProgressDialog(showActivity.this);
        progressDialog.setMessage("Loading..."); // Setting Message
        progressDialog.setTitle("Questions List"); // Setting Title
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

        reque = Volley.newRequestQueue(showActivity.this);

        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {

                        String res = response.toString();

                        try {
                            JSONObject jsonObject = new JSONObject(res);
                            JSONArray jsonArray = jsonObject.getJSONArray("result");
                            int records = jsonArray.length();

                            details = new ArrayList<>();

                            for(int i=0;i<records;i++){
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                                String id = jsonObject1.getString("ID");
                                String question = jsonObject1.getString("Question");
                                String opt1 = jsonObject1.getString("OptionA");
                                String opt2 = jsonObject1.getString("OptionB");
                                String opt3 = jsonObject1.getString("OptionC");
                                String opt4 = jsonObject1.getString("OptionD");
                                String ans = jsonObject1.getString("Answer");
                                int subject = jsonObject1.getInt("Subject");

                                model m = new model(id,question,opt1,opt2,opt3,opt4, ans);

                                details.add(m);

                            }
                            MyAdoptar ml = new MyAdoptar(showActivity.this,details);
                            listData.setAdapter(ml);


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(showActivity.this, "Error: "+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();

                map.put("Subject", ""+subcode);
                return map;
            }
        };
        reque.add(stringRequest1);

    }

    private void deleteData(final String id) {

        StringRequest request = new StringRequest(Request.Method.POST, deleteurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equalsIgnoreCase("Data Deleted")){
                    Toast.makeText(showActivity.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(showActivity.this, "Data not Deleted", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(showActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();

                params.put("ID",id);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
}