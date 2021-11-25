package com.example.minhastarefas

import java.io.Serializable

class ListaDeTarefas: Serializable {

    private var id : Int
    private var imgList : String
    private var nomeList : String
    private var descrList : String
    private var status : String

    constructor(imgList: String, nomeList : String, descrList : String){
        this.id = -1
        this.imgList = imgList;
        this.nomeList = nomeList;
        this.descrList = descrList
        this.status = "ABERTO"
    }

    constructor(imgList: String, nomeList : String, descrList : String, status : String){
        this.id = -1
        this.imgList = imgList;
        this.nomeList = nomeList;
        this.descrList = descrList
        this.status = status
    }

    constructor(id: Int, imgList: String, nomeList : String, descrList : String, status : String){
        this.id = id
        this.imgList = imgList;
        this.nomeList = nomeList;
        this.descrList = descrList
        this.status = status
    }

    fun getImage() : String{
        return this.imgList
    }

    fun getNome() : String{
        return this.nomeList
    }

    fun getDescr() : String{
        return this.descrList
    }

    fun getId() : Int{
        return this.id
    }

    fun getStatus() : String{
        return this.status
    }

    fun setImg(img: String){
        this.imgList = img
    }

    fun setNome(nome: String){
        this.nomeList = nome
    }

    fun setDescr(descr: String){
        this.descrList = descr
    }

    fun setStatus(status: String){
        this.status = status
    }

    override fun toString(): String {
        return "ID: "+this.id+" | Nome: "+this.nomeList+" | Descricao: "+this.descrList
    }
}