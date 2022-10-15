package com.example.animedoro.data

//import com.example.animedoro.AnimeSuggestions
import com.example.animedoro.R
import com.example.animedoro.model.AnimeSuggestions
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

    fun loadAnimeSuggestions(): List<AnimeSuggestions> {
        return listOf<AnimeSuggestions> (
            AnimeSuggestions("One Piece", "https://www.imdb.com/title/tt0388629/", R.drawable.anime1),
            AnimeSuggestions("Naruto", "https://www.imdb.com/title/tt0409591/", R.drawable.anime2),
            AnimeSuggestions("Bleach", "https://www.imdb.com/title/tt0434665/", R.drawable.anime3),
            AnimeSuggestions("Spy x Family", "https://www.imdb.com/title/tt13706018/", R.drawable.anime4),
            AnimeSuggestions("Attack on Titan", "https://www.imdb.com/title/tt2560140/", R.drawable.anime5),
            AnimeSuggestions("Death Note", "https://www.imdb.com/title/tt0877057/", R.drawable.anime6),
            AnimeSuggestions("Chainsaw Man", "https://www.imdb.com/title/tt13616990/", R.drawable.anime7),
            AnimeSuggestions("Demon Slayer", "https://www.imdb.com/title/tt9335498/", R.drawable.anime8),
            AnimeSuggestions("Jujutsu Kaisen", "https://www.imdb.com/title/tt12343534/", R.drawable.anime9),
        )
    }
}