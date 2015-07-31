package com.example.administrador.myapplication.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.administrador.myapplication.model.entities.Client;
import com.example.administrador.myapplication.model.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserContract {

    //dados Login

    public static final String TABLE_ADMIN = "Administator";
    public static final String ID_USER = "idUser";
    public static final String NOME_USUARIO = "admin";
    public static final String SENHA_USUARIO = "password";

    public static final String[] COLUMNS = {ID_USER, NOME_USUARIO, SENHA_USUARIO};

    public static String getSqlCreateSql() {

        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ");
        sql.append(TABLE_ADMIN);
        sql.append(" ( ");
        sql.append(ID_USER + " INTEGER PRIMARY KEY, ");
        sql.append(NOME_USUARIO + " TEXT, ");
        sql.append(SENHA_USUARIO + " TEXT, ");

        return sql.toString();
    }

    public static ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put(UserContract.ID_USER, user.getIdUser());
        values.put(UserContract.NOME_USUARIO, user.getNameUser());
        values.put(UserContract.SENHA_USUARIO, user.getPasswordUser());
        return values;
    }

    public static User bind(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            User user = new User();
            user.setIdUser(cursor.getLong(cursor.getColumnIndex(UserContract.ID_USER)));
            user.setNameUser(cursor.getString(cursor.getColumnIndex(UserContract.NOME_USUARIO)));
            user.setPasswordUser(cursor.getString(cursor.getColumnIndex(UserContract.SENHA_USUARIO)));
            return user;
        }
        return null;
    }

    public static List<User> bindList(Cursor cursor) {
        final List<User> serviceOrders = new ArrayList<>();
        while (cursor.moveToNext()) {
            serviceOrders.add(bind(cursor));
        }
        return serviceOrders;
    }

    public static String getSqlCreateAdmin() {

        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ");
        sql.append(TABLE_ADMIN);
        sql.append(" ( ");
        sql.append(ID_USER + " INTEGER PRIMARY KEY, ");
        sql.append(NOME_USUARIO + " TEXT, ");
        sql.append(SENHA_USUARIO + " TEXT );");

        return sql.toString();
    }

    public static String sqlInsertUser(){
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO ");
        sql.append(TABLE_ADMIN);
        sql.append(" ( ");
        sql.append(NOME_USUARIO + ", ");
        sql.append(SENHA_USUARIO + " ");
        sql.append(" ) ");
        sql.append(" VALUES ");
        sql.append("('admin', 'admin' )");

        return sql.toString();

    }
}