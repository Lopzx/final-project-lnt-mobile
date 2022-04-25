package com.example.finpro;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    TextView login_page;
    EditText id_bimble;
    EditText email;
    EditText name;
    EditText password;
    EditText password_confirm;
    Button submit;

    View.OnClickListener login_redir;
    View.OnClickListener register_handler;

    private FirebaseAuth auth_instance;
    private FirebaseFirestore db;

    private void log_regis_error(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private boolean validate(){
        Boolean status = true;
        String id_val = id_bimble.getText().toString();
        String email_val = email.getText().toString();
        String name_val = name.getText().toString();
        String password_val = password.getText().toString();
        String password_confirm_val = password.getText().toString();

        ArrayList<String> error_message = new ArrayList<>();

        if(id_val.isEmpty() || email_val.isEmpty() || name_val.isEmpty()
        || password_val.isEmpty() || password_confirm_val.isEmpty()) {
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

        if(name_val.length() < 5){
            error_message.add("Nama minimal 5 huruf");
            status = false;
        }

        if(!password_confirm_val.equals(password_val)){
            error_message.add("Password dan konfirmasi password tidak sama");
            status = false;
        }

        if(password_val.length() < 6){
            error_message.add("Password minimal 6 huruf");
            status = false;
        }

        String message = String.join("\n",error_message);

        if(error_message.size() != 0){
            log_regis_error(message);
        }

        return status;
    }

    private void initView(){
        login_page = findViewById(R.id.login_href);
        id_bimble = findViewById(R.id.id_bimble_in);
        email = findViewById(R.id.email_in);
        name = findViewById(R.id.name_in);
        password = findViewById(R.id.password_in);
        password_confirm = findViewById(R.id.password_confirmation_in);
        submit = findViewById(R.id.submit_button);

        auth_instance = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        initEvent();

        login_page.setOnClickListener(login_redir);
        submit.setOnClickListener(register_handler);
    }

    private void initEvent(){
        login_redir = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent redir = new Intent( getApplicationContext() ,com.example.finpro.LoginActivity.class);
                startActivity(redir);
            }
        };

        register_handler = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean validate_status = validate();
                String id_val = id_bimble.getText().toString();
                String email_val = email.getText().toString();
                String name_val = name.getText().toString();
                String password_val = password.getText().toString();

                if(validate_status) {
                    auth_instance.createUserWithEmailAndPassword(email_val, password_val)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(getApplicationContext(), "Sign-up successfull", Toast.LENGTH_SHORT).show();

                                Map<String, Object> user = new HashMap<>();
                                user.put("class_id", id_val);
                                user.put("name", name_val);
                                user.put("email", email_val);

                                db.collection("user_details").add(user)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Log.d("ERROR", "DocumentSnapshot added with ID: " + documentReference.getId());
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w("ERROR", "Error adding document", e);
                                    }
                                });
                                Intent redir = new Intent( getApplicationContext() ,com.example.finpro.LoginActivity.class);
                                startActivity(redir);
                            } else {
                                Log.w("TAGS","createUser:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "Sign-up failed", Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        };
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }
}
