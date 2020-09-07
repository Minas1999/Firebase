package com.hfad.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.EventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonSave, buttonRead;
    EditText editTextName, editTextLastName, editTextMail;
    DatabaseReference databaseReference;
    final String NAME = "User";
    private static final String TAG = "MyLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    public void init(){
        buttonSave = findViewById(R.id.btnSave);
        buttonSave.setOnClickListener(this);
        buttonRead = findViewById(R.id.btnRead);
        buttonRead.setOnClickListener(this);

        editTextLastName = findViewById(R.id.edLastname);
        editTextName = findViewById(R.id.edName);
        editTextMail = findViewById(R.id.edMail);

        databaseReference = FirebaseDatabase.getInstance().getReference(NAME);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                Save();
                break;
            case R.id.btnRead:
                Read();
                break;
        }
    }

    private void Read() {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot i: snapshot.getChildren()) {
                    User user1 = i.getValue(User.class);

                    assert user1 != null;
                    Log.i(TAG, "Child -> " + user1.name);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        databaseReference.addValueEventListener(valueEventListener);
        Toast.makeText(this, "Read", Toast.LENGTH_SHORT).show();
    }

    private void Save() {
        String id = databaseReference.getKey();
        String name = editTextName.getText().toString();
        String lastname = editTextLastName.getText().toString();
        String email = editTextMail.getText().toString();
        Log.i(TAG, "id -> " + id + " name-> "+ name + " lastname-> "+lastname + " email-> " +email);
        final User user = new User(name, lastname, email, id);
        databaseReference.push().setValue(user);
        Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
    }
}

