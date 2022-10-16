package com.example.animedoro


import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
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

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animedoro.ui.theme.primary
import com.example.animedoro.ui.theme.secondary
import com.example.animedoro.ui.theme.taskbottom
import kotlinx.coroutines.delay
import java.lang.Math.*


    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun SessionTimer()
    {
                val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
                // A surface container using the 'background' color from the theme
                val scaffoldState= rememberBottomSheetScaffoldState(
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


                    }
                    },
                    modifier = Modifier.fillMaxSize(),
                    sheetBackgroundColor = taskbottom,

                    sheetShape = RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp)
                ) {
                    Session()
                }

}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Session() {
    Box(modifier= Modifier
        .background(color = primary)
        .fillMaxSize()){
        Image(modifier= Modifier
            .fillMaxSize().alpha(0.8F).blur(radius =5.dp)
            ,contentScale = ContentScale.FillBounds,
            alignment = Alignment.CenterEnd,painter = painterResource(id = R.drawable.sessionbg), contentDescription = null)

        Column {
            Row {
                Text(
                    text = "3/5", color = White,
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
                    modifier=Modifier.size(300.dp)
                )
            }

        }
    }
}

@Composable
fun Timer(
    totalTime:Long,
    handleColor:Color,
    inactiveBarColor:Color,
    activeBarColor: Color,
    modifier:Modifier=Modifier,
    initialValue:Float=1f,
    strokeWidth: Dp =10.dp
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
            modifier=Modifier.align(Alignment.BottomCenter).height(60.dp),
            shape=CircleShape,
            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = White),


            ){
            if(currentTime==0L) {
                Log.i("timer", "timer done")
            }
            if(!isTimerRunning||currentTime==0L){
                Icon(Icons.Default.PlayArrow, contentDescription = "play button",tint=Black)
            }
            else{
                Icon(Icons.Default.Pause, contentDescription = "pause button",tint=Black)
            }
        }
    }
}
