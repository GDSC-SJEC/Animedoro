package com.example.animedoro

import android.graphics.Paint.Align
import android.media.Image
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animedoro.data.Datasource
import com.example.animedoro.model.Session

@Composable
fun ChooseBackGroundScreen(){
    Column {

        val list = listOf(Header(), UploadBackGroundButton() , )
        LazyColumn(modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp))
        {
            items(list)
            {
                Header()
                UploadBackGroundButton()
                or()
                InAppBackgrounds()
                NextScreen()
            }
        }

        /*

        */


    }

}



@Composable
fun Header()
{
    Row(modifier = Modifier.padding(10.dp , top = 20.dp)) {
        OutlinedButton(onClick = { /*TODO*/ },
            modifier= Modifier.size(50.dp),  //avoid the oval shape
            shape = CircleShape,
            border= BorderStroke(1.dp, Color.Black),
            contentPadding = PaddingValues(0.dp),  //avoid the little icon
            colors = ButtonDefaults.outlinedButtonColors(contentColor =  Color.Blue)
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = "content description")
        }


        Column(modifier = Modifier.padding(start = 50.dp ) ) {
            Text(text = "Choose Your",
                style = MaterialTheme.typography.h4)
            Text(text = "Background",
                style = MaterialTheme.typography.h4)
        }
    }

        
}

@Composable
fun UploadBackGroundButton()
{
    val uploadImage = painterResource(R.drawable.upload)
    Button(onClick = { /*TODO*/ } ,
        modifier = Modifier
            .padding(top = 50.dp, start = 70.dp)
            .defaultMinSize(minWidth = 200.dp, minHeight = 80.dp) ,
        shape = RoundedCornerShape(10),

        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Gray.copy(
                alpha = 1F,
            ),)) {
        Image(painter = uploadImage, contentDescription = null , contentScale = ContentScale.Fit)
        Text(text = "  Upload Background"  ,style = MaterialTheme.typography.h6 , modifier = Modifier.padding(start = 10.dp))


    }
}

@Composable
fun or()
{
    Text(text = "or" , modifier = Modifier.padding(top = 35.dp, start = 190.dp) , style = MaterialTheme.typography.h6)
}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InAppBackgrounds()
{
    val list = mutableListOf<Painter>()

    val image4 = painterResource(R.drawable.image_4)
    val image5 = painterResource(R.drawable.image_5)
    val image6 = painterResource(R.drawable.image_6)
    val image7 = painterResource(R.drawable.image_7)
    val image8 = painterResource(R.drawable.image_8)
    val image9 = painterResource(R.drawable.image_9)
    val image10 = painterResource(R.drawable.image_10)
    val image11 = painterResource(R.drawable.image_11)
    val image1 = painterResource(R.drawable.image1)
    val image2 = painterResource(R.drawable.image2)
    val image3 = painterResource(R.drawable.image3)

    list.add(image1)
    list.add(image2)
    list.add(image3)
    list.add(image4)
    list.add(image5)
    list.add(image6)
    list.add(image7)
    list.add(image8)
    list.add(image9)
    list.add(image10)
    list.add(image11)

    LazyVerticalGrid(cells = GridCells.Fixed(2), modifier = Modifier.padding(top = 50.dp),content = {
        items(list.size) { i->

            Card(modifier = Modifier
                .padding(10.dp)
                .height(230.dp)
                .width(100.dp), elevation = 10.dp,
                shape = RoundedCornerShape(20.dp)
            ) {
                Image(painter = list[i], contentDescription = null)
            }
        }
    })
}

@Composable
fun NextScreen()
{
    Button(onClick = { /*TODO*/ },
        modifier = Modifier.padding(top = 40.dp, start = 900.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White.copy(
                alpha = 1F,
            ),
        )
    ){
        Text(text = "All Previous Sessions ",fontSize = 20.sp,
            fontWeight = FontWeight.Bold)
        Icon(imageVector = Icons.Rounded.ArrowForward, contentDescription = "Plus Icon")

    }
}
