package com.example.minhastarefas

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText

class Tarefa : AppCompatActivity() {
    private lateinit var etName : EditText
    private lateinit var etStatus : EditText
    private lateinit var etDescricao : EditText
    private lateinit var btConcluir : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tarefa)

        this.etName = findViewById(R.id.etName)
        this.etStatus = findViewById(R.id.etStatus)
        this.etDescricao = findViewById(R.id.etDescricao)
        this.btConcluir = findViewById(R.id.btConcluir)

        this.btConcluir.setOnClickListener(OnClickBotao())
    }

    inner class OnClickBotao: View.OnClickListener{
        override fun onClick(v: View?) {
            val it = Intent()
            val tf = ListaDeTarefas(intent.getStringExtra("Position").toInt() + 1,
                    "ic_launcher_background",
                this@Tarefa.etName.text.toString(), this@Tarefa.etDescricao.text.toString(),
                this@Tarefa.etStatus.text.toString())
            it.putExtra("NewLista", tf)
            it.putExtra("index", intent.getStringExtra("Position"))
            setResult(Activity.RESULT_OK, it)
            finish()
        }
    }
}