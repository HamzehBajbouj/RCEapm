package com.example.rceapm.uiControlles.login

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rceapm.R
import com.example.rceapm.databinding.ActivityUserSignInCategoryBinding

class UserSignInCategory : AppCompatActivity() {
    private lateinit var binding: ActivityUserSignInCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserSignInCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

    }

   private fun init(){


        binding.authorityButton.setOnClickListener {
            Toast.makeText(this,"This feature is not activated",Toast.LENGTH_SHORT).show()
        }
        binding.judgeButton.setOnClickListener {
            Toast.makeText(this,"This feature is not activated",Toast.LENGTH_SHORT).show()
        }

        binding.participantButton.setOnClickListener {
            startActivity(SignInPage.getIntent(this))
        }

    }



}