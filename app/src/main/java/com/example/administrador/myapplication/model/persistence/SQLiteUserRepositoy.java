package com.example.administrador.myapplication.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrador.myapplication.model.entities.Client;
import com.example.administrador.myapplication.model.entities.User;
import com.example.administrador.myapplication.util.AppUtil;

import java.util.List;

public class SQLiteUserRepositoy implements UserRepository {

    private static SQLiteUserRepositoy singletonInstance;

    private SQLiteUserRepositoy() {
        super();
    }

    //pega a instacia do SQLite, que e um singleton
    public static SQLiteUserRepositoy getInstance() {
        if (SQLiteUserRepositoy.singletonInstance == null) {
            SQLiteUserRepositoy.singletonInstance = new SQLiteUserRepositoy();
        }
        return SQLiteUserRepositoy.singletonInstance;
    }

    @Override
    public void save(User user) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT); //capturei meu db
        SQLiteDatabase db = helper.getReadableDatabase(); //obtive uma instacia do db

        //valores
        ContentValues values = UserContract.getContentValues(user);

        if (user.getIdUser() == null) {
            db.insert(ClientContract.TABLE, null, values);
        } else {
            String where = ClientContract.ID + " = ?";
            String[] args = {user.getIdUser().toString()};
            db.update(ClientContract.TABLE, values, where, args);
        }
        db.close();
        helper.close();
    }

    @Override
    public List<User> getAll() {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT); //capturei meu db
        SQLiteDatabase db = helper.getReadableDatabase(); //obtive uma instacia do db

        Cursor cursor = db.query(UserContract.TABLE_ADMIN, UserContract.COLUMNS, null, null, null, null, UserContract.NOME_USUARIO);

        List<User> user = UserContract.bindList(cursor);

        db.close();
        helper.close();

        return user;
    }

    @Override
    public void delete(User user) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT); //capturei meu db
        SQLiteDatabase db = helper.getReadableDatabase(); //obtive uma instacia do db

        String where = ClientContract.ID + " = ?";
        String[] args = {user.getIdUser().toString()};

        db.delete(ClientContract.TABLE, where, args);

        db.close();
        helper.close();
    }
}
