package com.example.amalmidterm2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity4 extends AppCompatActivity {






        DatabaseHelper db;
        int count =1;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main4);

            Button delete=(Button)findViewById(R.id.bttnDelete);
            Button view=(Button)findViewById(R.id.buttonView);
            EditText id_val=(EditText)findViewById(R.id.editTextId);
            Button bttnfirstAct1=(Button)findViewById(R.id.bttnact1);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String user_id =id_val.getText().toString();
                    db.delete(user_id);

                    Toast.makeText(MainActivity4.this,"Successful Delete",Toast.LENGTH_LONG).show();
                }
            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        db.getListContents();
                        Toast.makeText(MainActivity4.this,"Successful View",Toast.LENGTH_LONG).show();
                    }
                    catch (Exception e){
                        count=1;
                        Toast.makeText(MainActivity4.this,"Unsuccessful View please insert id",Toast.LENGTH_LONG).show();
                    }
                }

            });

            bttnfirstAct1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity4.this,MainActivity.class));
                }
            });


        }
    }