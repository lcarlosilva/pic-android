package com.example.administrador.myapplication.model.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String BANCO_DADOS = "MY_DATABASE";
    public static final int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DatabaseHelper.BANCO_DADOS, null, DatabaseHelper.VERSION);
    }

    //instalacao do APP
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ClientContract.getSqlCreateSql());
        db.execSQL(UserContract.getSqlCreateAdmin());
        db.execSQL(UserContract.sqlInsertUser());
    }

    //atualizacao do APP
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
