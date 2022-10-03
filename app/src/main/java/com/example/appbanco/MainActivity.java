package com.example.appbanco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText email = findViewById(R.id.etemail);
        EditText contraseña = findViewById(R.id.etpassword);
        Button startsesion = findViewById(R.id.tbnstartsesion);
        TextView reglink= findViewById(R.id.tvregister);

        //evento click para registro

        reglink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //invocar la actividad del registro

                startActivity(new Intent(getApplicationContext(),Register.class));

            }
        });
    }
}