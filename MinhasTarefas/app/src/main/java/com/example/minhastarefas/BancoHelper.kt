package com.example.minhastarefas

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BancoHelper(context: Context): SQLiteOpenHelper(context,"bank.db",null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "create table if not exists tarefas (" +
                " id integer primary key autoincrement," +
                " imagem text,"+
                " nome text," +
                " descricao text," +
                " status text)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, before: Int, actual: Int) {
        db?.execSQL("drop table tarefas")
        this.onCreate(db)
    }
}