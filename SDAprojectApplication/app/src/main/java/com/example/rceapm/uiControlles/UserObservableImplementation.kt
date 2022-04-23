package com.example.rceapm.uiControlles

import android.content.Context
import com.example.rceapm.models.UserObservable
import com.example.rceapm.models.User
import com.example.rceapm.models.UserObserver

class UserObservableImplementation(val context: Context, newUserObserverFromIn: UserObserver) :
    UserObservable {


    private var userObservers: ArrayList<UserObserver> = ArrayList()
    private  lateinit var newUser : User



    init {
        registerNewUserObserver(newUserObserverFromIn)
       fetchDataFromLocalDatabase()

    }


    override fun registerNewUserObserver(newUserObserver: UserObserver) {
        if(!userObservers.contains(newUserObserver))
        userObservers.add(newUserObserver)
    }

    override fun removeNewUserObserver(deleteUserObserver: UserObserver) {
        if(userObservers.contains(deleteUserObserver))
        {userObservers.remove(deleteUserObserver)}
    }


    override fun notifyUserObserver() {
        userObservers.forEach{
            it.updateUserInformation(newUser)
        }
    }

    private fun fetchDataFromLocalDatabase() {

       val sp= context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        val userRealName = sp?.getString("userRealName", "NO NAME")
        val schoolName = sp?.getString("schoolName", "NO school")
        val email = sp?.getString("userEmail", "NO email")
        val password = sp?.getString("userPassword", "NO password")
        setUserData(User(schoolName!!,userRealName!!,email!!,password!!))
    }

     fun setUserData(user : User) {
         newUser =user
        notifyUserObserver()
    }
}