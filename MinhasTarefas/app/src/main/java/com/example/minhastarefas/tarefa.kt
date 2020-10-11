package com.example.minhastarefas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class tarefa : AppCompatActivity() {
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
    }
}