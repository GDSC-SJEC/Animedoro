package com.example.animedoro

import android.graphics.drawable.Icon
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.rounded.AddTask
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animedoro.data.Datasource
import com.example.animedoro.model.Session

@Composable
fun WelcomeScreen(){
    Column {
        WelcomeTextWithImage()
        AddNewSessionButton()
        RecentSessions()
        AllPreviousSessionsButton()
    }
}

@Composable
fun WelcomeTextWithImage(modifier: Modifier = Modifier) {
    Row(modifier = Modifier.padding(10.dp)) {
        Column {
            Text(
                text = "Welcome",
                fontSize = 40.sp,
                modifier = Modifier.padding(top = 17.dp, start = 23.dp),
                fontWeight = FontWeight.Bold,

            )
            Text(
                text = "User",
                fontSize = 36.sp,
                modifier = Modifier.padding(start = 26.dp),
            )
        }
        Column(modifier = Modifier.padding(top = 30.dp, start = 60.dp), horizontalAlignment = Alignment.End) {
            Image(
                painter = painterResource(id = R.drawable.user),
                contentDescription = "userImage",
                modifier = Modifier
                    .clip(RoundedCornerShape(40.dp))
                    .height(79.dp)
                    .width(82.dp)
                    ,

                contentScale = ContentScale.Crop,
                alignment = Alignment.CenterEnd
            )
        }
    }
}

@Composable
fun AddNewSessionButton(modifier: Modifier = Modifier) {
    Row (verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center){
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(top = 20.dp, start = 50.dp)
                .height(103.dp)
                .width(316.dp)
                .clip(RoundedCornerShape(40.dp)),
        )
         {
             Icon(imageVector = Icons.Filled.Add, contentDescription = "Plus Icon", modifier = Modifier.size(40.dp),
                 )
            Text(
                text = " Add New Session",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .height(32.63.dp)
                    .width(208.9.dp)
            )
        }
    }
}

@Composable
fun RecentSessions(modifier: Modifier = Modifier) {
    Row(modifier = Modifier
        .padding(start = 10.dp,top = 20.dp, bottom = 10.dp,end = 0.dp )) {
        Text(text = "Recent Sessions", fontSize = 32.sp,
            modifier = Modifier
                .padding(start = 27.dp)
                .width(266.dp)
                .height(48.dp),
            fontWeight = FontWeight.Bold,)
    }
    SessionList(sessionList = Datasource().loadSessions())
}

@Composable
fun SessionsCard(session: Session,
                 modifier: Modifier = Modifier) {
    Card(modifier = Modifier
        .padding(8.dp)
        .height(278.dp)
        .width(225.dp), elevation = 4.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
            Box {
                Image(
                    painter =
                    painterResource(id = session.imageResourceId),
                    contentDescription = stringResource(id = session.stringResourceId),
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),

                    contentScale = ContentScale.Crop
                )
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Start

                ) {

                    Card (modifier = Modifier

                        .fillMaxHeight()
                        .fillMaxWidth()
                        .padding(top = 170.dp, start = 0.dp, end = 0.dp, bottom = 0.dp),
                        shape = RoundedCornerShape(20.dp)
                    ){
                        Column {
                            Row (modifier = Modifier
                                .padding(start = 10.dp, top = 10.dp, bottom = 0.dp, end = 0.dp)) {
                                androidx.compose.material.Icon(
                                    imageVector = Icons.Filled.Schedule,
                                    contentDescription = "schedule",
                                    modifier = Modifier.padding(end = 10.dp)
                                )
                                Text(text = stringResource(id = session.stringResourceId))
                            }
                            Row (modifier = Modifier
                                .padding(start = 10.dp, top = 10.dp, bottom = 0.dp, end = 0.dp)){
                                androidx.compose.material.Icon(
                                    imageVector = Icons.Rounded.AddTask ,
                                    contentDescription = "add task icon",
                                    modifier = Modifier.padding(end = 10.dp)
                                )

                                Text(text = stringResource(id = session.taskResourceId))
                            }
//                            Icon(
//                                imageVector = Icons.Filled.ArrowForward,
//                                contentDescription = "Arrow Forward",
//                                modifier = Modifier
//                                    .padding(start = 190.dp, top = 0.dp, bottom = 0.dp, end = 0.dp),
//
//                            )
//                            Row (horizontalArrangement = Arrangement.End){
//                                Button(
//                                    onClick = { /*TODO*/ },
//                                    modifier = Modifier
//                                        .size(30.dp)
//                                        .padding(start = 0.dp),
//                                    shape = CircleShape,
//                                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Blue),
//                                    border = BorderStroke(1.dp, Color.Black),
//                                    contentPadding = PaddingValues(start = 100.dp)
//                                ) {
//                                    Icon(
//                                        imageVector = Icons.Rounded.ArrowForward,
//                                        contentDescription = "Arrow Forward",
//                                        modifier = Modifier
//                                            .padding(
//                                                start = 0.dp,
//                                                top = 0.dp,
//                                                bottom = 0.dp,
//                                                end = 0.dp
//                                            )
//                                    )
//                                }
//                            }
                            IconButton(onClick = { /*TODO*/ },
//                            modifier = Modifier.border(BorderStroke(1.dp, Color.Black)),

                            ) {
                                androidx.compose.material.Icon(
                                    imageVector = Icons.Rounded.ArrowForward,
                                    contentDescription = "Arrow forward",
                                    modifier = Modifier
                                    .padding(start = 190.dp, top = 0.dp, bottom = 0.dp, end = 0.dp)
                                )
                            }
                        }
                    }

                }
            }

    }
}

@Composable
fun SessionList(sessionList: List<Session>, modifier: Modifier = Modifier) {
    LazyRow (modifier = Modifier.padding(top = 10.dp, start = 20.dp, bottom = 10.dp, end = 0.dp)){
        items(sessionList) {
             session ->  SessionsCard(session = session)
        }
    }
}

@Composable
fun AllPreviousSessionsButton() {
    Button(onClick = { /*TODO*/ },
        modifier = Modifier
            .padding(top = 20.dp, start = 50.dp)
            .height(66.dp)
            .width(310.dp)
            .clip(
                RoundedCornerShape(40.dp)
            ),

    ){

        Text(text = "All Previous Sessions ",fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .width(250.dp)
                .height(40.dp)
                .padding(5.dp))
        Icon(Icons.Filled.ArrowForward, contentDescription = "Arrow Forward",
            modifier = Modifier.size(29.17.dp))


    }
}