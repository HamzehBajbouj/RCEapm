package com.example.rceapm.uiControlles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rceapm.R
import com.example.rceapm.databinding.FragmentHomePageBinding
import com.example.rceapm.databinding.FragmentToTestObserverSecondScreenBinding
import com.example.rceapm.models.User
import com.example.rceapm.models.UserObserver

class ToTestObserverSecondScreen : Fragment(), UserObserver {

    private var _binding: FragmentToTestObserverSecondScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var userObservableImplementation: UserObservableImplementation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentToTestObserverSecondScreenBinding.inflate(inflater, container, false)

        userObservableImplementation = UserObservableImplementation(requireContext(), this)


        return binding.root
    }

    override fun updateUserInformation(user: User) {
        val stringTest =
            "this to show the name added to a different operation:\n" + user.userRealName + "...user " +
                    "is not allowed to open this page"
        val stringTest2 = "another example number of letters in the ${user.schoolName} :"+user.schoolName.length.toString()
        binding.textViewShow.text =stringTest
        binding.textViewBelow.text=stringTest2

    }


}