package com.example.ax1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class first extends AppCompatActivity {
    private Button exit, go;
    private EditText nameE,email, phone, userid, password, rePassword;
    private TextView login;
    SharedPreferences preferences6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        nameE = findViewById(R.id.etName);
        email = findViewById(R.id.etEmail);
        phone = findViewById(R.id.etPhone);
        userid = findViewById(R.id.etUserID);
        password = findViewById(R.id.etPassword);
        rePassword = findViewById(R.id.etResetPassword);
        login = findViewById(R.id.login_link);
        exit = (Button) findViewById(R.id.exitBtn);
        go = (Button) findViewById(R.id.goBtn);

        preferences6= getSharedPreferences("UserInfo", MODE_PRIVATE);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(first.this, second.class);
                startActivity(i);
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = nameE.getText().toString().trim();
                String Email = email.getText().toString().trim();
                String Phone = phone.getText().toString().trim();
                String Uid = userid.getText().toString().trim();
                String Pass = password.getText().toString().trim();
                String RePass = rePassword.getText().toString().trim();

                if (Name.length()>1){
                    SharedPreferences.Editor editor = preferences6.edit();
                    editor.putString("nameE", Name);
                    editor.putString("email", Email);
                    editor.putString("phone", Phone);
                    editor.putString("userid", Uid);
                    editor.putString("password", Pass);
                    editor.putString("rePassword", RePass);
                    editor.apply();
                    Toast.makeText(first.this, "user registered", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(first.this, second.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(first.this, "enter value in the field", Toast.LENGTH_LONG).show();
                }

            }

        });
    }
}