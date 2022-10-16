package com.example.animedoro.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import java.lang.Boolean.valueOf

data class Tasks(val description: String, val completed: MutableState<Boolean>){
    var isCompleted:MutableState<Boolean> =this.completed
}