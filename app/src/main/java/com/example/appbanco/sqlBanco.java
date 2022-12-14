package com.example.appbanco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class sqlBanco extends SQLiteOpenHelper {
    //definicion de las tablas de la base de datos
    String tblcustomer= "CREATE TABLE customer (email text primary key, name text, password text, rol text)";

    String tblaccount="CREATE TABLE account(accountnumber integert primary key autoincrement, email text, date text, balance integert)";

    String tbltranstype="CREATE TABLE transtype(idtranstype text primary key, description text)";

    String tbltransactionc= "CREATE TABLE transactionc(accountnumber integer, idtranstype text, detetrans text, amount integert)";

    public sqlBanco(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //generar las instrucciones para crear las tablas
        db.execSQL(tblcustomer);
        db.execSQL(tbltranstype);
        db.execSQL(tblaccount);
        db.execSQL(tbltransactionc);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //actualizar las tablas
        db.execSQL("DROP TABLE customer");
        db.execSQL(tblcustomer);
        db.execSQL("DROP TABLE transtype");
        db.execSQL(tbltranstype);
        db.execSQL("DROP TABLE account");
        db.execSQL(tblaccount);
        db.execSQL("DROP TABLE transactionc");
        db.execSQL(tbltransactionc);

    }
}
