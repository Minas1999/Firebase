package com.hfad.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener
{

    EditText editTextLogin, editTextPassword;
    Button buttonLogin, buttonReg;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    public void init(){
        editTextLogin = findViewById(R.id.login);
        editTextPassword = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        buttonLogin = findViewById(R.id.loginbtn);
        buttonLogin.setOnClickListener(this);
        buttonReg = findViewById(R.id.regbtn);
        buttonReg.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null)
            Toast.makeText(this, "!null", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginbtn:
                login();
                break;
            case R.id.regbtn:
                reg();
                break;
        }
    }

    private void login() {
    }

    private void reg() {
        String login = editTextLogin.getText().toString().trim();
        String password = editTextLogin.getText().toString().trim();

        if(!TextUtils.isEmpty(login) && !TextUtils.isEmpty(password))
        {
            mAuth.createUserWithEmailAndPassword(login, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Login.this, "Created", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(Login.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}
