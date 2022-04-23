package com.example.rceapm.models

import android.content.Context

interface UserObservable {
    fun registerNewUserObserver(fragmentObserver: UserObserver) :Unit
    fun removeNewUserObserver(fragmentObserver: UserObserver) :Unit
    fun notifyUserObserver() :Unit
}

