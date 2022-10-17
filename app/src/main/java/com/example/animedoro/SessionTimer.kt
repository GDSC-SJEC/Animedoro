package com.example.animedoro


import android.util.Log
import android.widget.CheckBox
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowRight

import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animedoro.model.Tasks
import com.example.animedoro.ui.theme.primary
import com.example.animedoro.ui.theme.secondary
import com.example.animedoro.ui.theme.taskbottom
import kotlinx.coroutines.delay
import java.lang.Math.*
import java.util.*


@OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun SessionTimer(
        tasks: SnapshotStateList<Tasks>,
        toBreak: () -> Unit,
        music: playMusic
    )
    {
                music.startMusic()
                var sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
                // A surface container using the 'background' color from the theme
                var scaffoldState= rememberBottomSheetScaffoldState(
                    bottomSheetState = sheetState
                )
                BottomSheetScaffold(
                    scaffoldState=scaffoldState,
                    sheetContent={ Box(
                        modifier= Modifier
                            .fillMaxWidth()
                            .height(300.dp)

                    ){
                        Text(
                            text="Tasks",
                            fontSize = 30.sp,
                            modifier = Modifier.padding(start=30.dp,top=10.dp),
                            color=White
                        )

                        TasksList(tasksAdded = tasks)
                    }
                    },
                    modifier = Modifier.fillMaxSize(),
                    sheetBackgroundColor = taskbottom,

                    sheetShape = RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp)
                ) {
                    Session(sheetState=sheetState,tasks=tasks, toBreak = toBreak, music=music)
                }

}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Session(sheetState:BottomSheetState, tasks: SnapshotStateList<Tasks>, toBreak: () -> Unit,music: playMusic) {

    Box(modifier= Modifier
        .background(color = primary)
        .fillMaxSize()){
        Image(modifier= Modifier
            .fillMaxSize()
            .alpha(0.8F)
            .blur(radius = 5.dp)
            ,contentScale = ContentScale.FillBounds,
            alignment = Alignment.CenterEnd,painter = painterResource(id = R.drawable.sessionbg), contentDescription = null)

        Column {
            Row {
                //do the nav here bro when tasks are over 🥲
                //if(tasks.filter{it.isCompleted.value}.size.toString()==tasks.count().toString())

                Text(
                    text = tasks.filter{it.isCompleted.value}.size.toString()+"/"+tasks.count().toString(), color = White,
                    fontSize = 50.sp,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(top = 250.dp),horizontalArrangement = Arrangement.Center){
                Timer(
                    //Add your time in seconds here
                    totalTime=10,
                    handleColor=Black,
                    inactiveBarColor=White,
                    activeBarColor = secondary,
                    modifier=Modifier.size(300.dp),
                    sheetState=sheetState,
                    toBreak = toBreak,
                    music = music
                )
            }

        }
    }
}



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Timer(
    totalTime:Long,
    handleColor:Color,
    inactiveBarColor:Color,
    activeBarColor: Color,
    modifier:Modifier=Modifier,
    initialValue:Float=1f,
    strokeWidth: Dp =10.dp,
    sheetState:BottomSheetState,
    toBreak: () -> Unit,
    music: playMusic
){
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    var value by remember{
        mutableStateOf(initialValue)
    }
    var currentTime by remember{
        mutableStateOf(totalTime)
    }
    var isTimerRunning by remember{
        mutableStateOf(false)
    }
    LaunchedEffect(key1=currentTime,key2=isTimerRunning){
        if(currentTime>0 && isTimerRunning){
            delay(1000)
            currentTime-=1
            value =currentTime/totalTime.toFloat()
        }
    }
    Box(
        contentAlignment =Alignment.Center,
        modifier=modifier.onSizeChanged {
            size=it
        }

    ){
        Canvas(modifier=modifier){
            drawArc(
                color =inactiveBarColor,
                startAngle =-215f,
                sweepAngle =250f,
                useCenter=false,
                size= Size(size.width.toFloat(),size.height.toFloat()),
                style= Stroke(strokeWidth.toPx(),cap= StrokeCap.Round)
            )
            drawArc(
                color =activeBarColor,
                startAngle =-215f,
                sweepAngle =250f *  value,
                useCenter=false,
                size= Size(size.width.toFloat(),size.height.toFloat()),
                style= Stroke(strokeWidth.toPx(),cap= StrokeCap.Round)
            )
            val center= Offset(size.width/2f,size.height/2f)
            val beta=(250f*value +145f)*(PI/180f).toFloat()
            val r=size.width/2f
            val a= kotlin.math.cos(beta)*r
            val b = kotlin.math.sin(beta)*r
            drawPoints(
                listOf(Offset(center.x+a,center.y+b)),
                pointMode= PointMode.Points,
                color=handleColor,
                strokeWidth=(strokeWidth*3f).toPx(),
                cap=StrokeCap.Round

            )
        }
        var hours =  currentTime/ 3600;
        var minutes = (currentTime % 3600) / 60;
        var seconds = currentTime % 60;
        Text(
            text=String.format("%02d:%02d:%02d", hours, minutes, seconds),
            fontSize = 44.sp,
            fontWeight= FontWeight.Bold,
            color=White,
        )
        //do the nav here bro when time over 🥲
        //if(currentTime <= 0 && !isTimerRunning)

        OutlinedButton(onClick=
        {
            if(currentTime<=0)
            {
                currentTime=totalTime
                isTimerRunning=true
            }
            else{
                isTimerRunning=!isTimerRunning
            }
        },
            modifier= Modifier
                .align(Alignment.BottomCenter)
                .height(60.dp),
            shape=CircleShape,
            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = White),


            ){
            if(currentTime==0L) {
//                Log.i("timer", "timer done")
//                Text(text="go to get chai")
//                Icon(Icons.Default.ArrowForward, contentDescription = "play button",tint=Black)
                music.stopMusic()

                Button(
                    onClick = toBreak,
                    modifier = Modifier
                        .padding(start = 20.dp, top = 30.dp, end = 0.dp, bottom = 0.dp)
                        .width(120.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = "Next",
                        color = Color.White
                    )
                    Icon(Icons.Filled.ArrowForward, contentDescription = "Arrow Forward",
                        tint= com.example.animedoro.ui.theme.White,
                        modifier = Modifier.size(29.17.dp))
                }
            }
            if(!isTimerRunning&&currentTime>0){
                Icon(Icons.Default.PlayArrow, contentDescription = "play button",tint=Black)
            }
            else if(isTimerRunning&&currentTime>0){
                Icon(Icons.Default.Pause, contentDescription = "pause button",tint=Black)
            }
        }
    }
}
@Composable
fun TasksCard(task: Tasks,
                   modifier: Modifier = Modifier) {
    Card(modifier = Modifier
        .padding(start = 25.dp, top = 0.dp, end = 10.dp, bottom = 20.dp)
        .height(60.dp)
        .width(322.dp),
        elevation = 10.dp,
        backgroundColor = secondary,
        shape = RoundedCornerShape(20.dp)
    ) {

        val checkedState = remember { mutableStateOf(false) }
        Row (modifier = Modifier
            .padding(start = 60.dp, top = 15.dp, bottom = 0.dp, end = 0.dp),

        )
        {
            Checkbox(
                checked = task.completed.value,
                onCheckedChange = {
                    task.completed.value =it
                    Log.i("task", task.completed.value.toString())},
                modifier = Modifier.padding(bottom=18.dp))
            Text(text = task.description,color = com.example.animedoro.ui.theme.White,
                textAlign = TextAlign.Center)
        }



    }

}

@Composable
fun TasksList(tasksAdded: List<Tasks>, modifier: Modifier = Modifier) {
    LazyColumn (modifier = Modifier.padding(top = 100.dp, start = 20.dp, bottom = 0.dp, end = 0.dp)){
        items(tasksAdded) {
                task ->  TasksCard(task = task)
        }
    }
}