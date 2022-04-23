package com.example.rceapm.uiControlles.homePage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.rceapm.R
import com.example.rceapm.databinding.FragmentHomePageBinding
import androidx.navigation.Navigation
import com.example.rceapm.models.User
import com.example.rceapm.models.UserObserver
import com.example.rceapm.uiControlles.UserObservableImplementation


class HomePage : Fragment() ,UserObserver {
    private var _binding: FragmentHomePageBinding? = null
    private val binding get() = _binding!!
    private lateinit var userObservableImplementation: UserObservableImplementation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomePageBinding.inflate(inflater, container, false)
        init()
        userObservableImplementation = UserObservableImplementation(requireContext(),this)


        return binding.root
    }

    private fun init() {

//        val sp = activity?.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
//        val userRealName = sp?.getString("userRealName", "NO NAME")
//        val schoolName = sp?.getString("schoolName", "NO school")
//        binding.welcomingMessage.text =
//            String.format(resources.getString(R.string.welcoming_message), userRealName, schoolName)

        navigationMapping()

    }
    private fun navigationMapping(){
        binding.submitMaterials.setOnClickListener {
//            Toast.makeText(activity, "this feature is not activated", Toast.LENGTH_SHORT).show()
                view: View ->
            Navigation.findNavController(view)
                .navigate(R.id.action_homePage2_to_toTestObserverSecondScreen)
        }
        binding.Archive.setOnClickListener {
            Toast.makeText(activity, "this feature is not activated", Toast.LENGTH_SHORT).show()
        }
        binding.status.setOnClickListener {
            Toast.makeText(activity, "this feature is not activated", Toast.LENGTH_SHORT).show()
        }
        binding.announcement.setOnClickListener {
            Toast.makeText(activity, "this feature is not activated", Toast.LENGTH_SHORT).show()
        }
        binding.programList.setOnClickListener {
                view: View ->
            Navigation.findNavController(view)
                .navigate(R.id.action_homePage2_to_programList)
        }
    }

    override fun updateUserInformation(user: User) {
        binding.welcomingMessage.text =
            String.format(resources.getString(R.string.welcoming_message), user.userRealName, user.schoolName)
    }
}