package com.example.quizmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LogIn extends AppCompatActivity {

    AppCompatButton login;
    EditText name, password, email;
    TextView headLog, passforget;
    RequestQueue reqobj;

    public static final String logurl = "https://soumitri.000webhostapp.com/QuizProject/LogIn.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        headLog = (TextView) findViewById(R.id.headLog);
        passforget = (TextView) findViewById(R.id.passforget);
        login = (AppCompatButton) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reqobj = Volley.newRequestQueue(LogIn.this);

                String em = name.getText().toString();
                String mail = email.getText().toString();
                String pass = password.getText().toString();

                if (em.isEmpty()) {
                    Toast.makeText(LogIn.this, "Enter Name!", Toast.LENGTH_SHORT).show();
                    return;
                }else if (pass.isEmpty()) {
                    Toast.makeText(LogIn.this, "Enter Password!", Toast.LENGTH_SHORT).show();
                    return;
                }else if(mail.isEmpty()){
                    Toast.makeText(LogIn.this, "Enter Email!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(password.length()!=8){
                    Toast.makeText(LogIn.this, "Password must contain 8 characters!", Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, logurl,
                            new Response.Listener() {
                                @Override
                                public void onResponse(Object response) {
                                    String res = response.toString();

                                    if (res.equals("success")) {
                                        Intent intent = new Intent(LogIn.this, HomeScreen.class);
                                        intent.putExtra("nm", em);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(LogIn.this, "Invalid Email or password!!!" + res, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(LogIn.this, "No Internet! Check your connection", Toast.LENGTH_SHORT).show();
                                }
                            }) {
                        public Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> map = new HashMap<>();
                            map.put("email", mail);
                            map.put("password", pass);
                            return map;
                        }
                    };
                    reqobj.add(stringRequest);
                }
            }
        });

        passforget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LogIn.this, ForgetPassword.class);
                startActivity(i);
            }
        });
    }
}