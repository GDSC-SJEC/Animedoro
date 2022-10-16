package com.example.animedoro

import android.graphics.drawable.Icon
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.rounded.AddTask
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animedoro.data.Datasource
import com.example.animedoro.model.Session
import com.example.animedoro.model.Tasks
import com.example.animedoro.model.TasksAdded
import com.example.animedoro.ui.theme.White
import com.example.animedoro.ui.theme.cardTrans
import com.example.animedoro.ui.theme.primary
import com.example.animedoro.ui.theme.secondary

@Composable
fun WelcomeScreen(
    onAddNewSession: () -> Unit,
    allPreviousSessions: () -> Unit
){
    Column (modifier= Modifier
        .background(color = primary)
        .fillMaxSize()) {
        WelcomeTextWithImage()
        AddNewSessionButton(
            onAddNewSession = onAddNewSession

        )
//        RecentSessions()
//        AllPreviousSessionsButton(
//            allPreviousSessions = allPreviousSessions
//        )
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
                color=White

            )
            Text(
                text = "User",
                fontSize = 36.sp,
                modifier = Modifier.padding(start = 26.dp),
                color = White
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
fun AddNewSessionButton(
    onAddNewSession: () -> Unit,
    modifier: Modifier = Modifier) {
    Row (verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center){
        Button(
            onClick = onAddNewSession,
            modifier = Modifier
                .padding(top = 20.dp, start = 50.dp)
                .height(103.dp)
                .width(316.dp)
                .clip(RoundedCornerShape(40.dp)),
        )
         {
             Icon(imageVector = Icons.Filled.Add, contentDescription = "Plus Icon", modifier = Modifier.size(40.dp),tint=White
                 )
            Text(
                text = " Add New Session",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = White,
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
            fontWeight = FontWeight.Bold,
            color = White)
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
                          backgroundColor=cardTrans,
                        shape = RoundedCornerShape(20.dp)
                    ){
                        Column {
                            Row (modifier = Modifier
                                .padding(start = 10.dp, top = 10.dp, bottom = 0.dp, end = 0.dp)) {
                                androidx.compose.material.Icon(
                                    imageVector = Icons.Filled.Schedule,
                                    contentDescription = "schedule",
                                    modifier = Modifier.padding(end = 10.dp),
                                    tint=White
                                )
                                Text(text = stringResource(id = session.stringResourceId),color = White)
                        }
                        Row (modifier = Modifier
                            .padding(start = 10.dp, top = 10.dp, bottom = 0.dp, end = 0.dp)){
                            androidx.compose.material.Icon(
                                imageVector = Icons.Rounded.AddTask ,
                                contentDescription = "add task icon",
                                modifier = Modifier.padding(end = 10.dp),
                                tint=White
                            )

                            Text(text = stringResource(id = session.taskResourceId),color = White)
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
                                    tint=White,
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
fun AllPreviousSessionsButton(
    allPreviousSessions: () -> Unit
) {
    Button(onClick = allPreviousSessions,
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
             color=White,
            modifier = Modifier
                .width(250.dp)
                .height(40.dp)
                .padding(5.dp))
        Icon(Icons.Filled.ArrowForward, contentDescription = "Arrow Forward",
             tint=White,
            modifier = Modifier.size(29.17.dp))


    }
}





@Composable
fun SessionType(
    backButton: () -> Unit,
    onDefault: () -> Unit
) {
    Column(modifier=Modifier.background(color=primary).fillMaxSize()) {
        SessionTypeAppBar(
            backButton = backButton
        )
        Buttons(
            onDefault = onDefault
        )
    }
}

@Composable
fun SessionTypeAppBar(
    backButton: () -> Unit
) {
    Row(modifier = Modifier.padding(10.dp, top = 20.dp)){
        OutlinedButton(onClick = backButton,
        modifier = Modifier.size(50.dp),
        shape = CircleShape,

            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.outlinedButtonColors(backgroundColor =White)

        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = "back button")
        }
        Column (modifier = Modifier.padding(start = 50.dp)) {
            Text(text = "Session Type",
            style = MaterialTheme.typography.h4)
        }
    }
}

@Composable
fun Buttons(
    onDefault: () -> Unit
) {
    Column (horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = onDefault,
        modifier = Modifier
            .padding(start = 93.dp, top = 140.dp, end = 0.dp, bottom = 60.dp)
            .width(234.dp)
            .height(232.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Default", textAlign = TextAlign.Center,
                     color = White,
                fontSize = 40.sp, fontWeight = FontWeight.Bold
                )
                Text(text = "(40 mins)", textAlign = TextAlign.Center,
                     color = White,
                fontSize = 26.sp, modifier = Modifier
                        .padding())
            }
        }
//        Button(onClick = { /*TODO*/ },
//            modifier = Modifier
//                .padding(start = 93.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
//                .width(234.dp)
//                .height(232.dp),
//            shape = RoundedCornerShape(20.dp)
//        ) {
//            Text(text = "Custom", textAlign = TextAlign.Center,
//                 color = White,
//                fontSize = 40.sp, fontWeight = FontWeight.Bold)
//        }
    }
}





@Composable
fun AllSessions(
    backButton: () -> Unit
) {
    Column(modifier= Modifier
        .background(color = primary)
        .fillMaxSize()) {
        AllSessionsAppBar(
            backButton = backButton
        )
        AllSessionList(sessionList = Datasource().loadSessions())
    }
}

@Composable
fun AllSessionsAppBar(
    backButton: () -> Unit
) {
    Row(modifier = Modifier.padding(10.dp, top = 20.dp)){
        OutlinedButton(onClick = backButton,
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            border = BorderStroke(1.dp, Color.Black),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)

        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = "back button",tint=White)
        }
        Column (modifier = Modifier.padding(start = 50.dp)) {
            Text(text = "All Sessions",
//                style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
                 color = White,
            fontSize = 32.sp)
        }
    }
}

@Composable
fun AllSessionsCard(session: Session,
                    modifier: Modifier = Modifier) {
    Card(modifier = Modifier
        .padding(start = 25.dp, top = 20.dp, end = 10.dp, bottom = 20.dp)
        .height(97.dp)
        .width(322.dp), elevation = 10.dp,
         backgroundColor = cardTrans,
        shape = RoundedCornerShape(20.dp)
    ) {
        Row {
            Image(
                painter =
                painterResource(id = session.imageResourceId),
                contentDescription = stringResource(id = session.stringResourceId),
                modifier = Modifier
                    .width(129.dp)
                    .height(97.dp)
                    .clip(RoundedCornerShape(20.dp)),

                contentScale = ContentScale.Crop
            )
            Column() {
                Row (modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp, bottom = 0.dp, end = 0.dp)) {
                    androidx.compose.material.Icon(
                        imageVector = Icons.Filled.Schedule,
                        contentDescription = "schedule",
                        tint=White,
                        modifier = Modifier.padding(end = 10.dp)
                    )
                    Text(text = stringResource(id = session.stringResourceId),color = White)
                }
                Row (modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp, bottom = 0.dp, end = 0.dp)){
                    androidx.compose.material.Icon(
                        imageVector = Icons.Rounded.AddTask ,
                        contentDescription = "add task icon",
                        tint=White,
                        modifier = Modifier.padding(end = 10.dp)
                    )

                    Text(text = stringResource(id = session.taskResourceId),color = White)
                }
            }
        }
    }

}

@Composable
fun AllSessionList(sessionList: List<Session>, modifier: Modifier = Modifier) {
    LazyColumn (modifier = Modifier.padding(top = 100.dp, start = 20.dp, bottom = 10.dp, end = 0.dp)){
        items(sessionList) {
                session ->  AllSessionsCard(session = session)
        }
    }
}

@Composable
fun StartYourSessionScreen(
    backButton: () -> Unit,
) {
    Column(modifier= Modifier
        .background(color = primary)
        .fillMaxSize()) {
        StartYourSessionsAppBar(
            backButton = backButton
        )
        StartYourSessionButton()
    }
}

@Composable
fun StartYourSessionsAppBar(
    backButton: () -> Unit
) {
    Row(modifier = Modifier.padding(10.dp, top = 20.dp)){
        OutlinedButton(onClick = backButton,
            modifier = Modifier.size(50.dp),
            shape = CircleShape,

            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.outlinedButtonColors(backgroundColor =White)

        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = "back button")
        }
        Column (modifier = Modifier.padding(start = 50.dp)) {
            Text(text = "Start Your",
                style = MaterialTheme.typography.h4)
            Text(text = "Session?",
                style = MaterialTheme.typography.h4)
        }
    }
}

@Composable
fun StartYourSessionButton(
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center) {
        Button(onClick = {},
            modifier = Modifier
                .padding(start = 93.dp, top = 140.dp, end = 0.dp, bottom = 60.dp)
                .width(234.dp)
                .height(232.dp),
            shape = RoundedCornerShape(20.dp),
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Start", textAlign = TextAlign.Center,
                    color = White,
                    fontSize = 40.sp, fontWeight = FontWeight.Bold
                )
            }
        }
    }
}






@Composable
fun AddYourTasksScreen(
    backButton: () -> Unit,
    onNext: () -> Unit,
    tasks: SnapshotStateList<Tasks>
) {
//    val tasks = remember {
//        mutableStateListOf<Tasks>()
//    }
    Column(modifier= Modifier
        .background(color = primary)
        .fillMaxSize()){
        AddYourTasksAppBar(
            backButton = backButton
        )
        AddYourTasksTextFieldWithButton(
            onNext = onNext,
            addToList = {
                tasks.add(Tasks(it, true))
            }
        )
        TasksAddedList(tasksAdded = tasks)
//    AddTaskScreenNextButton()
    }
}

//@Composable
//fun AddYourTasksScreen(
//    backButton: () -> Unit,
//    onNext: () -> Unit
//) {
//// this variable use to handle list state
//    val notesList = remember {
//        mutableStateListOf<String>()
//    }
//// this variable use to handle edit text input value
//    val inputvalue = remember { mutableStateOf(TextFieldValue()) }
//
//    Column {
//        Row(
//            Modifier
//                .fillMaxWidth()
//                .height(Dp(60f))
//        ) {
//
//            TextField(
//                value = inputvalue.value,
//                onValueChange = {
//                    inputvalue.value = it
//                },
//                modifier = Modifier.weight(0.8f),
//                placeholder = { Text(text = "Enter your note") },
//                keyboardOptions = KeyboardOptions(
//                    capitalization = KeyboardCapitalization.None,
//                    autoCorrect = true, keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
//                ),
//                textStyle = TextStyle(
//                    color = Color.Black, fontSize = TextUnit.Unspecified,
//                    fontFamily = FontFamily.SansSerif
//                ),
//                maxLines = 1,
//                singleLine = true
//            )
//
//            Button(
//                onClick = {
//                    notesList.add(inputvalue.value.text)
//                },
//                modifier = Modifier
//                    .weight(0.2f)
//                    .fillMaxHeight()
//            ) {
//                Text(text = "ADD")
//            }
//        }
//
//        Spacer(modifier = Modifier.height(Dp(1f)))
//
//        Surface(modifier = Modifier.padding(all = Dp(5f))) {
//            LazyColumn {
//
//                itemsIndexed(notesList) { index, item ->
//
//                    val annotatedText = buildAnnotatedString {
//                        withStyle(
//                            style = SpanStyle(
//                                color = Color.Blue,
//                                fontWeight = FontWeight.Bold
//                            )
//                        ) {
//                            append("Delete")
//                        }
//                    }
//                    Row(
//                        Modifier
//                            .fillMaxWidth()
//                            .height(Dp(50f))
//                    ) {
//
//                        Text(text = item, Modifier.weight(0.85f))
//
//                        ClickableText(
//                            text = annotatedText, onClick = {
//
//                                notesList.remove(item)
//
//                            },
//                            modifier = Modifier.weight(0.15f)
//                        )
//                    }
//                }
//            }
//        }
//
//
//    }
//
//
//}

@Composable
fun AddYourTasksAppBar(
    backButton: () -> Unit
) {
    Row(modifier = Modifier.padding(10.dp, top = 20.dp)){
        OutlinedButton(onClick = backButton,
            modifier = Modifier.size(50.dp),
            shape = CircleShape,

            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.outlinedButtonColors(backgroundColor =White)

        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = "back button")
        }
        Column (modifier = Modifier.padding(start = 50.dp)) {
            Text(text = "Add Your Tasks",
                style = MaterialTheme.typography.h4)
        }
    }
}
@Composable
fun AddYourTasksTextFieldWithButton(
    onNext: () -> Unit,
    addToList: (String) -> Unit
) {
    var text by remember {
        mutableStateOf(TextFieldValue(""))
    }
    Row(horizontalArrangement = Arrangement.Center) {
        TextField(
            value = text,
            onValueChange = { newText ->
                text = newText
            },
            modifier = Modifier
                .height(236.dp)
                .width(350.dp)
                .padding(start = 60.dp, top = 50.dp, end = 0.dp, bottom = 0.dp),
            shape = RoundedCornerShape(20.dp),

            colors = TextFieldDefaults.textFieldColors(
                textColor = White,
                disabledTextColor = Color.Transparent,
              backgroundColor = secondary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }
    Row(horizontalArrangement = Arrangement.Center) {
        Button(
            onClick = { addToList(text.text) },
            modifier = Modifier
                .padding(start = 80.dp, top = 30.dp, end = 0.dp, bottom = 0.dp)
                .width(120.dp)
                .height(50.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(
                text = "Add Task",
                color = Color.White
            )
        }
        Button(
            onClick = onNext,
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
                tint=White,
                modifier = Modifier.size(29.17.dp))
        }
         }
    Row(modifier = Modifier.padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)) {
        Text(
            text = "Tasks",
            modifier = Modifier
                .width(212.dp)
                .height(48.dp)
                .padding(start = 63.dp, top = 20.dp, end = 0.dp, bottom = 0.dp),
//            style = MaterialTheme.typography.h4
        fontSize = 20.sp,

        )
    }
}

@Composable
fun TasksAddedCard(task: Tasks,
                    modifier: Modifier = Modifier) {
    Card(modifier = Modifier
        .padding(start = 25.dp, top = 0.dp, end = 10.dp, bottom = 20.dp)
        .height(60.dp)
        .width(322.dp),
        elevation = 10.dp,
        backgroundColor = secondary,
        shape = RoundedCornerShape(20.dp)
    ) {


                Row (modifier = Modifier
                    .padding(start = 60.dp, top = 15.dp, bottom = 0.dp, end = 0.dp)) {
                    Text(text = task.description,color = White,
                    textAlign = TextAlign.Center)
                }



    }

}

@Composable
fun TasksAddedList(tasksAdded: List<Tasks>, modifier: Modifier = Modifier) {
    LazyColumn (modifier = Modifier.padding(top = 100.dp, start = 20.dp, bottom = 0.dp, end = 0.dp)){
        items(tasksAdded) {
                task ->  TasksAddedCard(task = task)
        }
    }
}

@Composable
fun AddTaskScreenNextButton() {
    Row(horizontalArrangement = Arrangement.Center) {
        Button(onClick = { /*TODO*/ },
        modifier = Modifier
            .width(132.dp)
            .height(61.dp)) {
            Text(text = "Next ",fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color=White,
                modifier = Modifier
                    .width(48.dp)
                    .height(30.dp)
                    .padding(5.dp))
            Icon(Icons.Filled.ArrowForward, contentDescription = "Arrow Forward",
                tint=White,
                modifier = Modifier.size(29.17.dp))
        }
    }
}
