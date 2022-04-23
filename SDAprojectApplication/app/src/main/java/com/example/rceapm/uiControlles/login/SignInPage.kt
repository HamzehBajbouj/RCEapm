package com.example.rceapm.uiControlles.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rceapm.models.database.DatabaseHandler
import com.example.rceapm.databinding.ActivitySignInPageBinding
import com.example.rceapm.models.User
import com.example.rceapm.uiControlles.MainActivity
import com.example.rceapm.uiControlles.register.SignUpPage

class SignInPage : AppCompatActivity() {
    private lateinit var binding: ActivitySignInPageBinding


    companion object {
        fun getIntent(context: Context): Intent {
            val intent = Intent(context, SignInPage::class.java)
            // intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

    }

    private fun init() {


        binding.signUp.setOnClickListener {
            startActivity(SignUpPage.getIntent(this))
        }
        binding.forgotPassword.setOnClickListener {
            Toast.makeText(this, "This feature is not activated", Toast.LENGTH_SHORT).show()
        }

        binding.loginButton.setOnClickListener {
            validateUserInformation()

        }

    }

    private fun validateUserInformation() {

        val email = binding.emailTextField.text.toString()
        val password = binding.passwordTextField.text.toString()

        if (email.isNotBlank() && password.isNotBlank()) {

            if (checkUserInformationInDatabase(email, password)) {

                Toast.makeText(this, "correct data", Toast.LENGTH_SHORT).show()
               startActivity(MainActivity.getIntent(this))
            } else {
                Toast.makeText(this, "your password/email is incorrect", Toast.LENGTH_SHORT).show()
            }

        } else {
            Toast.makeText(this, "Please enter your details", Toast.LENGTH_LONG).show()
        }

    }

    private fun checkUserInformationInDatabase(email: String, password: String): Boolean {
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        val userRow: List<User> = databaseHandler.readUserRows()
        var userDetailsAreCorrect = false


        for (user in userRow) {
            if (user.email == email && user.password == password) {
                userDetailsAreCorrect = true
                //create a small xml storage file
                val sharedPreferences: SharedPreferences = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("userRealName", user.userRealName)
                editor.putString("schoolName", user.schoolName)
                editor.putString("userEmail", user.email)
                editor.putString("userPassword", user.password)
                editor.apply()
                editor.commit()
                break
            }
        }

        return userDetailsAreCorrect
    }

}


