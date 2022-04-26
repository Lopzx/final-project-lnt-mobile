package com.example.finpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    TextView register_page;
    EditText email;
    EditText password;
    Button login_btn;

    View.OnClickListener register_redirect;
    View.OnClickListener login;

    private FirebaseAuth auth_instance;


    private void initView(){
        register_page = findViewById(R.id.register_href);
        email = findViewById(R.id.email_in);
        password = findViewById(R.id.password_in);
        login_btn = findViewById(R.id.button);
        auth_instance = FirebaseAuth.getInstance();

        initEvent();

        login_btn.setOnClickListener(login);
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

         login = new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String email_val = email.getText().toString();
                 String password_val = password.getText().toString();

                 if(validate()){
                     auth_instance.signInWithEmailAndPassword(email_val, password_val)
                             .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                 @Override
                                 public void onComplete(Task<AuthResult> task) {
                                     if (task.isSuccessful()) {
                                         // Sign in success, update UI with the signed-in user's information
                                         FirebaseUser user = auth_instance.getCurrentUser();

                                         Intent main_activity = new Intent(getApplicationContext(), com.example.finpro.MainActivity.class);
                                         startActivity(main_activity);
                                     } else {
                                         // If sign in fails, display a message to the user.
                                         Toast.makeText(getApplicationContext(), "Authentication failed.",
                                                 Toast.LENGTH_SHORT).show();
                                     }
                                 }
                             });
                 }
             }
         };
    }

    private boolean validate(){

        String email_val = email.getText().toString();
        String password_val = password.getText().toString();

        Boolean status = true;
        ArrayList<String> error_message = new ArrayList<>();

        if( email_val.isEmpty() || password_val.isEmpty()) {
            error_message.add("Field tidak boleh kosong");
            status = false;
        }

        if(!email_val.contains("@")){
            error_message.add("Email harus memiliki tanda @");
            status = false;
        }


        if(!email_val.endsWith(".com")){
            error_message.add("Email harus diakhiri dengan .com");
            status = false;
        }

        String message = String.join("\n",error_message);

        if(error_message.size() != 0){
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }

        return status;
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }
}
