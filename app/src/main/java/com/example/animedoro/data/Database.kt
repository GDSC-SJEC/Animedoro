package com.example.animedoro

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "animedoro.db", factory, 1) {
 

    override fun onCreate(db: SQLiteDatabase) {
        // raw SQL query
        val query1 = ("CREATE TABLE IF NOT EXISTS sessions (
                    session_id INTEGER PRIMARY KEY,
                    duration TEXT NOT NULL,
                    background_resource_id TEXT,
                    music_resource_id TEXT,
                    )")
 

        db.execSQL(query)

        val query2 = ("CREATE TABLE tasks (
                    tasks_id INTEGER PRIMARY KEY,
                    FOREIGN KEY (session_id) REFERENCES sessions (session_id),
                    description TEXT NOT NULL,
                    completed INTEGER NOT NULL
                    )")

        db.execSQL(query2)

        println("database created")
    }
 
 
    // This method is for adding data in our database
    fun addSession(sid : Integer, dur : String, bg : String, mus : String) {
 
        // below we are creating a content values variable
        val values = ContentValues()
 
        // we are inserting our values in the form of key-value pair
        values.put(session_id, sid)
        values.put(duration, dur)
        values.put(background_resource_id, bg)
        values.put(music_resource_id, mus)
 
        // below line is to get writable database from our database helper class.
        val db = this.writableDatabase
 
        // all values are inserted into database
        db.insert(sessions, null, values)
 
        db.close()
    }
 
    // GETTER method for getting all the data from our database.
    fun getSessionData(sid : Integer): Cursor? {
 
        // below line is to get readable database from our database helper class.
        val db = this.readableDatabase
 
        // below code returns a cursor to
        // read data from the database
        return db.rawQuery("SELECT * FROM sessions where session_id = " + sid, null)

        db.close()
    }

    fun addTask(tid : Integer, sid : Integer, desc : String, comp : Integer) {
 
        // below we are creating a content values variable
        val values = ContentValues()
 
        // we are inserting our values in the form of key-value pair
        values.put(task_id, tid)
        values.put(session_id, sid)
        values.put(description, desc)
        values.put(completed, comp)
 
        // below line is to get writable database from our database helper class.
        val db = this.writableDatabase
 
        // all values are inserted into database
        db.insert(tasks, null, values)
 
        db.close()
    }

    fun getTaskData(sid : Integer): Cursor? {
 
        // below line is to get readable database from our database helper class.
        val db = this.readableDatabase
 
        // below code returns a cursor to
        // read data from the database
        return db.rawQuery("SELECT * FROM tasks where session_id = " + sid, null)

        db.close()
    }
}