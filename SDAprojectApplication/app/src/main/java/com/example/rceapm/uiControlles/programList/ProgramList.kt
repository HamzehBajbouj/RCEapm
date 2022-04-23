package com.example.rceapm.uiControlles.programList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rceapm.databinding.FragmentProgramListBinding
import com.example.rceapm.models.FakeEvents
import com.example.rceapm.models.database.DatabaseHandler


class ProgramList : Fragment() {
    companion object {
        fun getProgramList(): Companion {
        return this
        }
    }
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: ProgramListAdapter
    private val databaseHandler by lazy { DatabaseHandler(requireContext()) }
    private var _binding: FragmentProgramListBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentProgramListBinding.inflate(inflater, container, false)
        init()
        return binding.root

    }

    private fun init() {


        linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.programListRecyclerview.layoutManager = linearLayoutManager
        adapter=ProgramListAdapter(FakeEvents.fakeList)
        binding.programListRecyclerview.adapter = adapter

    }



}