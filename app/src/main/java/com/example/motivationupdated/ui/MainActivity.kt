package com.example.motivationupdated.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.motivationupdated.infra.MotivationConstants
import com.example.motivationupdated.R
import com.example.motivationupdated.data.Mock
import com.example.motivationupdated.infra.SecurityPreferences
import com.example.motivationupdated.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    private var categoryId = MotivationConstants.FILTER.all

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        //expande o layout e cria o layout
        setContentView(binding.root)
        //atribui para a view

        //Esconder a barra de navegação
        supportActionBar?.hide()

        changeUserName()
        changeButtonColor(R.id.image_all)
        handleNextPhrase()
        //todas as funções são logicamente pensadas - pega o nome pra sempre do usuario
        //muda a cor dos botões e pega a frase

        //Click
        binding.buttonNewPhase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSun.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_newPhase) {
            handleNextPhrase()
        } else if (view.id in listOf(R.id.image_all, R.id.image_happy, R.id.image_sun)) {
            changeButtonColor(view.id)
        }
    }

    private fun handleNextPhrase() {
        val phrase = Mock().getPhrase(categoryId)
        binding.textPhrase.text = phrase
    }

    private fun changeButtonColor(id: Int) {

        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageSun.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        when (id) {
            R.id.image_all -> {
                binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.all
            }
            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.happy
            }
            R.id.image_sun -> {
                binding.imageSun.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.sun
            }
        }
    }

    private fun changeUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.userName)
        binding.textName.text = "Olá, $name!"
    }
}