package com.example.rceapm.uiControlles

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.rceapm.R
import com.example.rceapm.databinding.ActivityMainBinding
import com.example.rceapm.models.FakeEvents
import com.example.rceapm.models.database.DatabaseHandler
import com.example.rceapm.uiControlles.programList.ProgramList

class MainActivity : AppCompatActivity() {

    private lateinit var fragmentManger: FragmentManager
    private lateinit var binding: ActivityMainBinding

    companion object {
        fun getIntent(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //this part is to add fake event/programs to the database
//        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
//        FakeEvents.fakeList.forEach { databaseHandler.addEvent(it) }


    }




}