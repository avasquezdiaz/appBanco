package com.example.appbanco;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String [] roles = {"Administracion","Usuario"};
    String rolSel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrer);

        EditText email=findViewById(R.id.etemailreg);
        EditText name=findViewById(R.id.etnamereg);
        EditText password=findViewById(R.id.etpasswordreg);
        Spinner rol=findViewById(R.id.sprolreg);
        Button register=findViewById(R.id.btnregister);

        ArrayAdapter adpRol= new ArrayAdapter(this,android.R.layout.simple_list_item_checked,roles);
        rol.setAdapter(adpRol);

        //general evento para seleccionar un rol
        rol.setOnItemSelectedListener(this);
        //evento click en el boton registrar

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchCustomer(email.getText().toString(), name.getText().toString(), password.getText().toString(),rolSel);
            }
        });


        };





    private void searchCustomer(String sEmail, String sName, String sPassword, String srolSel) {
        //crear array para alm,acenar los datos de la consulta (query)
        ArrayList<String> dataCustomer= new ArrayList<String>();

        //instanciar la clase sqlBanco (SQLiteopenhelper)
        sqlBanco ohBanco= new sqlBanco(this, "dbbanco",null,1);
        //instanciar la clase SQLiteDAtabase para el crud
        SQLiteDatabase db= ohBanco.getReadableDatabase();
        //crear variable para consulta
        String sql = "Select email From customer Where email = '"+sEmail+"'";
        //ejecutar la instruccion que contiene la variable SQL, a traves de una tabla en curso
        Cursor cCustomer=db.rawQuery(sql,null);
        //chequear si la tabla cursor cCustomer quedo con, al menos, un registro
        if (!cCustomer.moveToFirst()){//no lo encontro
            //agregar el cliente con tosos sus datos
            //instanciar la base de datos en modo escritura porque se agregara un cliente
            SQLiteDatabase dbadd=ohBanco.getWritableDatabase();
            //manejo de exepciones
            try {
                ContentValues cvCustomer=new ContentValues();
                cvCustomer.put("email",sEmail);
                cvCustomer.put("name",sName);
                cvCustomer.put("password",sPassword);
                cvCustomer.put("rol",srolSel);
                dbadd.insert("customer", null,cvCustomer);
                dbadd.close();
                Toast.makeText(getApplicationContext(),"Cliente agregado correctamente",Toast.LENGTH_LONG).show();
                // chequear si el rol es administrador o usuario
                if(srolSel.equals("Administracion")){
                    startActivity(new Intent(getApplicationContext(),Cuenta.class));
                }
                else{
                    startActivity(new Intent(getApplicationContext(),Usuarios.class));
                }

            }
            catch (Exception e){
              Toast.makeText(getApplicationContext(), "Error: "+e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

        else{
            Toast.makeText(getApplicationContext(),"Email existente!.Intentelo con otro",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        rolSel=roles[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}