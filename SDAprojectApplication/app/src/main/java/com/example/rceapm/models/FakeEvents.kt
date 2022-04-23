package com.example.rceapm.models

object FakeEvents {
    var fakeList: MutableList<Event> = mutableListOf(
        Event("summer break", "2021-12-12", true),
        Event("winter break", "2021-11-2", true),
        Event("bootcamp", "2021-2-23", false),
        Event("certification heroes", "2021-12-16", false),
        Event("buddies heroes", "2021-2-16", false)
    )

}