package com.example.animedoro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
                modifier = Modifier.padding(top = 16.dp, start = 20.dp),
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.Bold,
//                fontSize = 10.sp
            )
            Text(
                text = "User",
                modifier = Modifier.padding(start = 26.dp),
                style = MaterialTheme.typography.h4,
//                fontSize = 10.sp
            )
        }
        Column(modifier = Modifier.padding(top = 16.dp, start = 20.dp), horizontalAlignment = Alignment.End) {
            Image(
                painter = painterResource(id = R.drawable.user),
                contentDescription = "userImage",
                modifier = Modifier
                    .height(110.dp)
                    .padding(start = 20.dp),
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
            modifier = Modifier.padding(top = 40.dp, start = 90.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White.copy(
                    alpha = 0.2F
                ))
        )
         {
             Icon(imageVector = Icons.Default.Add, contentDescription = "Plus Icon")
//            Text(text = "+", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Text(
                text = " Add New Session",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun RecentSessions(modifier: Modifier = Modifier) {
    Row(modifier = Modifier.padding(start = 20.dp,top = 10.dp, bottom = 10.dp,end = 0.dp )) {
        Text(text = "Recent Sessions", fontSize = 30.sp,
            modifier = Modifier.padding(start = 20.dp),
            fontWeight = FontWeight.Bold,)
    }
    SessionList(sessionList = Datasource().loadSessions())
}

@Composable
fun SessionsCard(session: Session,
                 modifier: Modifier = Modifier) {
    Card(modifier = Modifier
        .padding(8.dp)
        .height(300.dp)
        .width(200.dp), elevation = 4.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column {
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
                Row (verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center){
                    Card(modifier = Modifier.padding(top = 280.dp)) {
                        Text(text = "something")
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
        modifier = Modifier.padding(top = 40.dp, start = 90.dp),
                colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White.copy(
                    alpha = 0.2F,
                ),
    )
    ){
        Text(text = "All Previous Sessions ",fontSize = 20.sp,
            fontWeight = FontWeight.Bold)
        Icon(imageVector = Icons.Rounded.ArrowForward, contentDescription = "Plus Icon")

    }
}