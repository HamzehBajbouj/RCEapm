package com.example.rceapm.uiControlles.programList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.rceapm.R
import com.example.rceapm.models.Event
import com.example.rceapm.models.FakeEvents
import com.example.rceapm.models.User
import com.example.rceapm.models.UserObserver

class ProgramListAdapter(private var programsList: MutableList<Event>) :RecyclerView.Adapter<ProgramListAdapter.ListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramListAdapter.ListHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return ListHolder(inflatedView)
    }


    override fun onBindViewHolder(holder: ProgramListAdapter.ListHolder, position: Int) {
        val program = programsList[position]
        holder.bindDate(program,position)
    }

    override fun getItemCount(): Int = programsList.size


    class ListHolder(private val view: View) : RecyclerView.ViewHolder(view){


        lateinit var programTitle: TextView
        lateinit var programDate: TextView
        lateinit var registerButton: Button

        init {
            programTitle = view.findViewById(R.id.program_title)
            programDate = view.findViewById(R.id.program_date)
            registerButton = view.findViewById(R.id.register_button)
        }


        fun bindDate(event : Event, position :Int){
            programTitle.text= event.eventName
            programDate.text= event.eventDate


            if (event.haveRegistered)
            {
                registerButton.text=String.format("registered")
            }

            registerButton.setOnClickListener {
                    view: View ->
                if (!event.haveRegistered) {
                    FakeEvents.fakeList.removeAt(position)
                    FakeEvents.fakeList.add(position,Event(event.eventName,event.eventDate,true))
                    Navigation.findNavController(view)
                        .navigate(R.id.action_programList_to_registeration)
                }else{
                    Toast.makeText(view.context,"you already register in the program",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}