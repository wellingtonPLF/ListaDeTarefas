package com.example.minhastarefas

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class Base_Adapter(private var context: Context, private var listaT : ArrayList<ListaDeTarefas>) : BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val listaDT = this.listaT.get(position)
        val view_ : View

        if (convertView == null){
            view_ = LayoutInflater.from(context).inflate(R.layout.activity_listview, null)
        }
        else{
            view_ = convertView
        }

        val ivFoto = view_.findViewById<ImageView>(R.id.ivFoto)
        val tvNome = view_.findViewById<TextView>(R.id.tvNamed)
        val tvDescr = view_.findViewById<TextView>(R.id.tvDescr)

        //Image: ic_launcher_background
        val img = context.resources.getIdentifier(listaDT.getImage(), "drawable", context.packageName)
        ivFoto.setImageResource(img)
        tvNome.text = listaDT.getNome()
        tvDescr.text = listaDT.getDescr()
        Log.i("APP_TEST", listaDT.getNome())
        Log.i("APP_TEST", listaDT.getDescr())
        return view_
    }

    override fun getItem(position: Int): Any {
        return this.listaT.get(position)
    }

    override fun getItemId(position: Int): Long {
        return -1
    }

    override fun getCount(): Int {
        return this.listaT.count()
    }
}
