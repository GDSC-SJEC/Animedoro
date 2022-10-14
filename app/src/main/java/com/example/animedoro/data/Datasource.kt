package com.example.animedoro.data

import com.example.animedoro.R
//import com.example.animedoro.TasksAdded
import com.example.animedoro.model.Session
import com.example.animedoro.model.TasksAdded

class Datasource {
    fun loadSessions(): List<Session> {
        return listOf<Session>(
            Session(R.string.session1,R.string.task1, R.drawable.image1),
            Session(R.string.session2,R.string.task2, R.drawable.image2),
            Session(R.string.session3,R.string.task3, R.drawable.image3))
    }

    fun loadTasks(): List<TasksAdded> {
        return listOf<TasksAdded>(
        TasksAdded(R.string.addedTask1),
        TasksAdded(R.string.addedTask2),
        TasksAdded(R.string.addedTask3),
        TasksAdded(R.string.addedTask4),
        TasksAdded(R.string.addedTask5)
        )
    }
}