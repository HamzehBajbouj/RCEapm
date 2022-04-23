package com.example.rceapm.models

import android.view.View
import com.example.rceapm.uiControlles.programList.ProgramListAdapter

interface UserObserver {

fun updateUserInformation(user :User) : Unit
}
