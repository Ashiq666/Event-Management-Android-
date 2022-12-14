package com.example.ax1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class second extends AppCompatActivity {

    private EditText email,password;
    private Button exit, go;
    private CheckBox rem_id, rem_pass;
    boolean ri, rp;
    SharedPreferences preferences6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        go = (Button) findViewById(R.id.goBtn);
        rem_id = findViewById(R.id.rem_ID);
        rem_pass = findViewById(R.id.rem_Pass);
        ri = rem_id.isChecked();
        rp = rem_pass.isChecked();

        preferences6 = getSharedPreferences("UserInfo", MODE_PRIVATE);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = email.getText().toString().trim();
                String Pass = password.getText().toString().trim();

                String registeredEmail = preferences6.getString("email", "");
                String registeredPass = preferences6.getString("password", "");
                if (Email.equals(registeredEmail) && Pass.equals(registeredPass)){
                    Intent i = new Intent(second.this,MainActivity.class);
                    startActivity(i);
                }
            }
        });



    }
}