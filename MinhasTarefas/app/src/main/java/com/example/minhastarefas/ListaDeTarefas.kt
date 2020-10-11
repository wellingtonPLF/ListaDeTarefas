package com.example.minhastarefas

class ListaDeTarefas {

    private var id : Int
    private var imgList : String
    private var nomeList : String
    private var descrList : String

    constructor(imgList: String, nomeList : String, descrList : String){
        this.id = -1
        this.imgList = imgList;
        this.nomeList = nomeList;
        this.descrList = descrList
    }

    constructor(id: Int, imgList: String, nomeList : String, descrList : String){
        this.id = id
        this.imgList = imgList;
        this.nomeList = nomeList;
        this.descrList = descrList
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
}