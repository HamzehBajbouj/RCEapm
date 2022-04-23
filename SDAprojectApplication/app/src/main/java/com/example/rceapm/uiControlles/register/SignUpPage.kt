package com.example.rceapm.uiControlles.register

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rceapm.models.database.DatabaseHandler
import com.example.rceapm.databinding.ActivitySignUpPageBinding
import com.example.rceapm.models.User

class SignUpPage : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            val intent = Intent(context, SignUpPage::class.java)
            return intent
        }
    }

    private lateinit var binding : ActivitySignUpPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        binding.createNewAccountButton.setOnClickListener {
           storeUserDetailsInDatabase()
        }
        binding.haveAccountSignIn.setOnClickListener { finish() }
    }

    private fun storeUserDetailsInDatabase() {
        val userRealName = binding.realNameTextField.text.toString()
        val schoolName = binding.schoolName.text.toString()
        val password = binding.passwordTextField.text.toString()
        val email = binding.emailTextField.text.toString()

        val databaseHandler: DatabaseHandler = DatabaseHandler(this)

        if (userRealName.isNotBlank() && schoolName.isNotBlank() && password.isNotBlank() && email.isNotBlank()) {
            val newUser = User(schoolName, userRealName, email, password)
            val status = databaseHandler.addNewUser(newUser)
            if    (status > -1) {
                Toast.makeText(this, "record save", Toast.LENGTH_LONG).show()
                finish()
            } else {
            Toast.makeText(
                this,
                "there is error in storing records",
                Toast.LENGTH_LONG
            ).show()
             }
    } else
        {
            Toast.makeText(this,"please fill all the fields",Toast.LENGTH_SHORT).show()
        }
    }

}