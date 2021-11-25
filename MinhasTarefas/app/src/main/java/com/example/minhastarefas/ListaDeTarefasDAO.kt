package com.example.minhastarefas

import android.content.ContentValues
import android.content.Context

class ListaDeTarefasDAO {
    var banco : BancoHelper

    constructor(context : Context){
        this.banco = BancoHelper(context)
    }

    fun add(tarefa : ListaDeTarefas){
        val cv = ContentValues()
        cv.put("imagem", tarefa.getImage())
        cv.put("nome", tarefa.getNome())
        cv.put("descricao", tarefa.getDescr())
        cv.put("status", tarefa.getStatus())

        this.banco.writableDatabase.insert("tarefas", null, cv)
    }

    fun count(): Int{
        val colunas = arrayOf("id")
        val banco = this.banco.readableDatabase
        val c = banco.query("tarefas", colunas, null, null, null, null, null)

        return c.count
    }

    fun getAll(): ArrayList<ListaDeTarefas>{
        val lista = ArrayList<ListaDeTarefas>()
        val banco = this.banco.readableDatabase
        val colunas = arrayOf("id", "imagem", "nome", "descricao", "status")
        val c = banco.query("tarefas", colunas, null, null, null, null,null)

        if (count() > 0){
            c.moveToFirst()
            do {
                var id = c.getInt(c.getColumnIndex("id"))
                var imagem = c.getString(c.getColumnIndex("imagem"))
                var nome = c.getString(c.getColumnIndex("nome"))
                var descr = c.getString(c.getColumnIndex("descricao"))
                var status = c.getString(c.getColumnIndex("status"))
                lista.add(ListaDeTarefas(id, imagem, nome, descr, status))
            }while(c.moveToNext())
        }
        return lista
    }

    fun get(id: Int) : ListaDeTarefas?{
        var result : ListaDeTarefas
        val banco = this.banco.readableDatabase
        val colunas = arrayOf("id", "imagem","nome", "descricao", "status")
        val c = banco.query("tarefas", colunas, null, null, null, null,null)
        c.moveToFirst()
        do {
            var pk = c.getInt(c.getColumnIndex("id"))
            var imagem = c.getString(c.getColumnIndex("imagem"))
            var nome = c.getString(c.getColumnIndex("nome"))
            var descr = c.getString(c.getColumnIndex("descricao"))
            var status = c.getString(c.getColumnIndex("status"))
            if(id == pk){
                result = ListaDeTarefas(pk, imagem, nome, descr, status);
                return result
            }
        }while(c.moveToNext())
        return null
    }

    fun delete(tarefa:ListaDeTarefas){
        val where = "id = ?"
        val wherep = arrayOf(tarefa.getId().toString())
        this.banco.writableDatabase.delete("tarefas", where, wherep)
    }

    fun update(tarefa : ListaDeTarefas){
        val cv = ContentValues()
        val where = "id = ?"
        val wherep = arrayOf(tarefa.getId().toString())
        cv.put("imagem", tarefa.getImage())
        cv.put("nome", tarefa.getNome())
        cv.put("descricao", tarefa.getDescr())
        cv.put("status", tarefa.getStatus())
        this.banco.writableDatabase.update("tarefas", cv, where, wherep)
    }
}