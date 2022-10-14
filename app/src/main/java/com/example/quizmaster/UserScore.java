package com.example.quizmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

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

public class UserScore extends AppCompatActivity {

    public  static final String showurl = "https://soumitri.000webhostapp.com/QuizProject/show_result.php";


    ListView listData;
    RequestQueue reque;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_score);

        listData = (ListView) findViewById(R.id.listData);

        ProgressDialog progressDialog = new ProgressDialog(UserScore.this);
        progressDialog.setMessage("Loading..."); // Setting Message
        progressDialog.setTitle("User's List"); // Setting Title
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

        reque = Volley.newRequestQueue(UserScore.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, showurl,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object result) {
                        String res = result.toString();
                        try {
                            JSONObject jsonObject = new JSONObject(res);
                            JSONArray jsonArray = jsonObject.getJSONArray("response");
                            int records = jsonArray.length();

                            ArrayList<UserClass> details = new ArrayList<>();

                            for(int i=0; i<records; i++){

                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                                String  name = jsonObject1.getString("name");
                                String subject = jsonObject1.getString("subject");
                                String marks = jsonObject1.getString("marks");
                                String examdate = jsonObject1.getString("examdate");

                                UserClass s = new UserClass(name, subject, marks,examdate);
                                details.add(s);
                            }

                            UserAdaptor ml = new UserAdaptor(UserScore.this, details);
                            listData.setAdapter(ml);
                            Toast.makeText(UserScore.this, "records: "+records, Toast.LENGTH_SHORT).show();
                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(UserScore.this, "Error: "+e, Toast.LENGTH_SHORT).show();
                        }
                        //Toast.makeText(ParticipantsList.this, "response "+res, Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(UserScore.this, "error "+error, Toast.LENGTH_SHORT).show();
                    }
                });
        reque.add(stringRequest);

    }
}