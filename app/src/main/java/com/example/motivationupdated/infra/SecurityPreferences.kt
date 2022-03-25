package com.example.motivationupdated.infra

import android.content.Context
import android.content.SharedPreferences

//onde usa o Shared.preferences para armazenar dados pequenos

class SecurityPreferences (context: Context) { //instanciar o context por paramentro através do construtor pq
    //se não, não usa o SharedPreferences
    private val security: SharedPreferences =
        context.getSharedPreferences("Motivation", Context.MODE_PRIVATE) //- pronto pra salvar

    //recebe o nome
    //Em motivationConstants foi criado um objeto com uma constante para key.
    //MotivationConstants.key.userName, name)
    fun storeString(key: String, str: String){ //str é value - de chave e valor
        security.edit().putString(key, str).apply()
        //
    }

    //pega o nome
    fun getString(key: String) : String {
        return  security.getString(key, "") ?: ""
        //retorna uma string, mas se essa string for nula devolve vazio se nao, devolve a chave - getString
    }


}