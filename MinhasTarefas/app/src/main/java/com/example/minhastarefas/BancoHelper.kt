package com.example.minhastarefas

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BancoHelper(context: Context): SQLiteOpenHelper(context,"banco.db",null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "create table if not exists tarefas (" +
                " id integer primary key autoincrement," +
                " imagem text"+
                " nome text," +
                " descricao text)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, before: Int, actual: Int) {
        db?.execSQL("drop table tarefas")
        this.onCreate(db)
    }
}