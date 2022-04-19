package com.example.amalmidterm2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int count=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addbttn=(Button)findViewById(R.id.bttnAdd);
        EditText id=(EditText)findViewById(R.id.editTextID);
        EditText name=(EditText)findViewById(R.id.editTextName);
        EditText email=(EditText)findViewById(R.id.editTextEmail);
        EditText phone=(EditText)findViewById(R.id.editTextPhone);

        Button bttnSecondAct=(Button)findViewById(R.id.button);
        Button bttnThirdAct=(Button)findViewById(R.id.button2);

        final DatabaseHelper MyDB = new DatabaseHelper(this);

        addbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    String id_val = id.getText().toString();
                    String name_val = name.getText().toString();
                    String email_val = email.getText().toString();
                    String phone_val = phone.getText().toString();

                    MyDB.addData(id_val, name_val, email_val, phone_val);
                    Log.d("Leena","after adding value");

                    Toast.makeText(MainActivity.this, "Successful Add", Toast.LENGTH_LONG).show();
                }
                catch (Exception e){
                    count=1;
                    Toast.makeText(MainActivity.this,"Unsuccessful Add please insert all necessary data",Toast.LENGTH_LONG).show();
                }
            }

        });

        bttnSecondAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });


        bttnThirdAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity3.class));
            }
        });
    }
}