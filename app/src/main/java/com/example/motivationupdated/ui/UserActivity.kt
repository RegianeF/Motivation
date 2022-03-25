package com.example.motivationupdated.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.motivationupdated.infra.MotivationConstants
import com.example.motivationupdated.R
import com.example.motivationupdated.infra.SecurityPreferences
import com.example.motivationupdated.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar?.hide()

        binding.buttonSave.setOnClickListener(this)

        verifyUserName()
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_save) {
            handleSave()
        }
    }

    fun verifyUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.userName)
        if (name != "") {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    fun handleSave() {
        val name = binding.editName.text.toString()

        SecurityPreferences(this).storeString(MotivationConstants.KEY.userName, name)

        if (name != "") {
            //navegação entre abas acontece por causa do startActivity
            //Intent é quem carrega
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            //Toast é uma notificação..
            Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_SHORT).show()
        }
    }
}