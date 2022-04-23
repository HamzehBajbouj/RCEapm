package com.example.rceapm.models.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.rceapm.models.Event
import com.example.rceapm.models.User

class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        const val DATABASE_NAME = "RCEapm"
        const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val userTable =
            "CREATE TABLE User(id INTEGER PRIMARY KEY AUTOINCREMENT, schoolName VARCHAR(30) ,userRealName VARCHAR(30), email VARCHAR(30), password VARCHAR(30))"

        val eventTable =
            "CREATE TABLE Event(id INTEGER PRIMARY KEY AUTOINCREMENT, eventName VARCHAR(30) ,eventDate VARCHAR(10) ,haveRegistered INTEGER )"

        db?.execSQL(userTable)
        db?.execSQL(eventTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + "User")
        db!!.execSQL("DROP TABLE IF EXISTS " + "Event")
        onCreate(db)
    }

    fun addNewUser(user: User): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("schoolName", user.schoolName)
        contentValues.put("userRealName", user.userRealName)
        contentValues.put("email", user.email)
        contentValues.put("password", user.password)
        val success = db.insert("User", null, contentValues)
        db.close()
        return success
    }
    //not used since they should be added from the admin side
    fun addEvent(event: Event): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("eventName", event.eventName)
        contentValues.put("eventDate", event.eventDate)
        contentValues.put("haveRegistered", booleanToInt(event.haveRegistered))

        val success = db.insert("Event", null, contentValues)
        db.close()
        return success
    }

    fun readEventRows(): ArrayList<Event> {
        val eventList: ArrayList<Event> = ArrayList<Event>()
        val selectQuery = "SELECT  * FROM Event"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var event_name: String
        var event_date: String
        var have_Registered : Boolean
        if (cursor.moveToFirst()) {
            do {

                event_name = cursor.getString(cursor.getColumnIndexOrThrow("eventName"))
                event_date = cursor.getString(cursor.getColumnIndexOrThrow("eventDate"))
                have_Registered = intToBoolean(cursor.getInt(cursor.getColumnIndexOrThrow("haveRegistered")))
                val event = Event(eventName = event_name, eventDate = event_date,haveRegistered=have_Registered)
                eventList.add(event)
            } while (cursor.moveToNext())
        }
        return eventList
    }

    fun readUserRows(): ArrayList<User> {
        val userList: ArrayList<User> = ArrayList<User>()
        val selectQuery = "SELECT  * FROM User"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var user_real_name: String
        var user_email: String
        var user_password: String
        var school_name: String
        if (cursor.moveToFirst()) {
            do {

                user_real_name = cursor.getString(cursor.getColumnIndexOrThrow("userRealName"))
                user_email = cursor.getString(cursor.getColumnIndexOrThrow("email"))
                user_password = cursor.getString(cursor.getColumnIndexOrThrow("password"))
                school_name = cursor.getString(cursor.getColumnIndexOrThrow("schoolName"))

                val user = User(
                    userRealName = user_real_name,
                    email = user_email,
                    password = user_password,
                    schoolName = school_name
                )
                userList.add(user)
            } while (cursor.moveToNext())
        }
        return userList
    }

    fun booleanToInt(b: Boolean): Int {
        return if (b) 1 else 0
    }
    fun intToBoolean(i : Int) : Boolean{
        return i==1
    }

}