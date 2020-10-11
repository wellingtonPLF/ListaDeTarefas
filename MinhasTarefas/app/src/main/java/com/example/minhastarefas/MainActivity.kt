package com.example.minhastarefas

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_formulario.*
import kotlinx.android.synthetic.main.activity_formulario.view.*

//Após tentar usar o SqlLite n consegui mais rodar o programa... N entendi pq o Base adapter
//não reconhece o ArrayList<ListaDeTarefas> da variavel Dao

class MainActivity : AppCompatActivity() {
    private lateinit var btCadastrar: Button
    private lateinit var lvTarefa : ListView
    //private lateinit var lista : ArrayList<ListaDeTarefas>
    private lateinit var dao : ListaDeTarefasDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.btCadastrar = findViewById(R.id.btCadastrar)
        this.lvTarefa = findViewById(R.id.lvTarefas)
        //this.lista = ArrayList()
        this.dao = ListaDeTarefasDAO(this)

        this.btCadastrar.setOnClickListener(eventoCadastro())

        //this.lvTarefa.adapter = Base_Adapter(this, this.lista)
        this.lvTarefa.adapter = Base_Adapter(this, this.dao.select())
        this.lvTarefa.setOnItemClickListener(clickCurto())
        this.lvTarefa.setOnItemLongClickListener(clickLongo())
    }

    private fun atualiza(){
        (this.lvTarefa.adapter as BaseAdapter).notifyDataSetChanged() //Importante (Ordem de precedência)
    }

    inner class eventoCadastro : View.OnClickListener{
        override fun onClick(v: View?) {
            val builder = AlertDialog.Builder(this@MainActivity)
            val view_ = LayoutInflater.from(this@MainActivity).inflate(R.layout.activity_formulario, null)

            val etFName = view_.findViewById<EditText>(R.id.etFName)
            val etFFoto = view_.findViewById<EditText>(R.id.etFFoto)
            val etFStatus = view_.findViewById<EditText>(R.id.etFStatus)
            val etFDescricao = view_.findViewById<EditText>(R.id.etFDescricao)
            val btFconcluir = view_.findViewById<Button>(R.id.btFConcluir)
            val btFcancelar = view_.findViewById<Button>(R.id.btFcancelar)

            builder.setView(view_)

            val janela = builder.show()
            view_.btFConcluir.setOnClickListener {
                val foto = view_.etFFoto.text.toString()  //Image: ic_launcher_background
                val nome = view_.etFName.text.toString()
                val descricao = view_.etFDescricao.text.toString()
                val tf = ListaDeTarefas(foto, nome, descricao)
                this@MainActivity.dao.add(tf)
                //this@MainActivity.lista.add(tf)
                this@MainActivity.atualiza()
                janela.dismiss()
            }
            view_.btFcancelar.setOnClickListener { janela.dismiss() }
        }
    }

    inner class clickCurto : AdapterView.OnItemClickListener{
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val it = Intent(this@MainActivity, tarefa::class.java)
            startActivity(it)
        }
    }

    inner class clickLongo : AdapterView.OnItemLongClickListener{
        override fun onItemLongClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) : Boolean{
            val aviso = AlertDialog.Builder(this@MainActivity)
            aviso.setTitle("AVISO!")
            aviso.setMessage("Realmente Deseja Excluir a Tarefa?")
            aviso.setNegativeButton("NÃO"){ dialog, which ->
                dialog.dismiss()
            }
            aviso.setPositiveButton("SIM"){ dialog, which ->
                //this@MainActivity.lista.removeAt(position)
                this@MainActivity.dao.delete(dao.select().get(position))
                this@MainActivity.atualiza()
            }
            aviso.show()
            return true
        }
    }
}