package com.horizon.procart.procart;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button btnMove;
    TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        btnMove = (Button)findViewById(R.id.ok);
        username = (EditText)findViewById(R.id.username);
        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().trim().length()==0){
                    Toast.makeText(MainActivity.this,"Enter Cart Number...",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String input = username.getText().toString();
                    Intent i = new Intent(MainActivity.this,Bill.class);
                    i.putExtra("input",input);
                    startActivity(i);



                }
            }
        });
    }
}
