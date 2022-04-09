package com.example.finpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    TextView register_page;
    EditText email;
    EditText password;
    Button login_btn;

    View.OnClickListener register_redirect;


    private void initView(){
        register_page = findViewById(R.id.register_href);
        email = findViewById(R.id.email_in);
        password = findViewById(R.id.password_in);
        login_btn = findViewById(R.id.button);

        initEvent();

        register_page.setOnClickListener(register_redirect);
    }

    private void initEvent(){
         register_redirect = new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent redir = new Intent( getApplicationContext() ,com.example.finpro.RegisterActivity.class);
                 startActivity(redir);
             }
         };
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }
}
