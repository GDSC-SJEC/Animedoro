package com.example.animedoro.data

import com.example.animedoro.R
import com.example.animedoro.model.Session

class Datasource {
    fun loadSessions(): List<Session> {
        return listOf<Session>(
            Session(R.string.session1,R.string.task1, R.drawable.image1),
            Session(R.string.session2,R.string.task2, R.drawable.image2),
            Session(R.string.session3,R.string.task3, R.drawable.image3))
    }
}