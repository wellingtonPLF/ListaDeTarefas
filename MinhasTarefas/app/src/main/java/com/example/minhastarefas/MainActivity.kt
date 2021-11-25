package com.example.minhastarefas

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_formulario.view.*

class MainActivity : AppCompatActivity() {
    private lateinit var btCadastrar: Button
    private lateinit var lvTarefa : ListView
    //private lateinit var lista : ArrayList<ListaDeTarefas>
    private lateinit var dao : ListaDeTarefasDAO
    val token = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //this.lista = ArrayList()
        this.dao = ListaDeTarefasDAO(this)
        this.btCadastrar = findViewById(R.id.btCadastrar)
        this.lvTarefa = findViewById(R.id.lvTarefas)

        this.btCadastrar.setOnClickListener(eventoCadastro())

        //this.lvTarefa.adapter = Base_Adapter(this, this.lista)
        this.lvTarefa.adapter = Base_Adapter(this, this.dao.getAll())
        this.lvTarefa.setOnItemClickListener(clickCurto())
        this.lvTarefa.setOnItemLongClickListener(clickLongo())
    }

    private fun atualiza(){
        (this.lvTarefa.adapter as BaseAdapter).notifyDataSetChanged()
        this.lvTarefa.adapter = Base_Adapter(this, this.dao.getAll())
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
            val it = Intent(this@MainActivity, Tarefa::class.java)
            it.putExtra("Position", position.toString())
            startActivityForResult(it, token)
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
                this@MainActivity.dao.delete(dao.getAll().get(position))
                this@MainActivity.atualiza()
            }
            aviso.show()
            return true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK){
            if (requestCode == token) {
                val novo = data?.getSerializableExtra("NewLista") as ListaDeTarefas
                //this.lista.set(data?.getStringExtra("index").toInt(), novo)
                this.dao.update(novo)
                atualiza()
            }
        }else{
            Toast.makeText(this, "Voltou pelo Dispositivo", Toast.LENGTH_SHORT).show()
        }
    }
}