package com.example.animedoro

import android.content.Intent
import android.net.Uri
import android.os.Build.VERSION.SDK_INT
import android.os.SystemClock
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.rounded.AddTask
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
//import coil.ImageLoader
//import coil.compose.AsyncImagePainter.State.Empty.painter
import com.example.animedoro.data.Datasource
import com.example.animedoro.model.AnimeSuggestions
import com.example.animedoro.model.TasksAdded
import com.example.animedoro.ui.theme.White
import com.example.animedoro.ui.theme.cardTrans
import com.example.animedoro.ui.theme.primary
import com.example.animedoro.ui.theme.secondary
import kotlinx.coroutines.delay


enum class ChibiPosition {
    One, Two, Three, Four
}

@Composable
fun BreakScreen(
    backButton: () -> Unit,
    homeButton: () -> Unit,


    ) {

//    Confetti()
    Box(modifier=Modifier.background(color = primary)) {
        ChibiAnimation()
        BreakText()
        BreakButtons(homeButton = homeButton)
    }
}

@Composable
fun BreakButtons(homeButton: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp, end = 10.dp),
        Alignment.BottomEnd,

    ) {
        Button(
            onClick = { homeButton() },
            modifier = Modifier
//                .fillMaxWidth(0.5f)
                .padding(bottom = 20.dp, end = 20.dp)
                .height(50.dp)
                .width(120.dp),
            contentPadding = PaddingValues(0.dp),
            shape = RoundedCornerShape(10.dp),

        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Home",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = White,
            )
            Icon(
                modifier = Modifier.padding(start = 2.dp, end = 2.dp),
                tint = White,
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "home button"
            )
        }
    }

}

@Composable
fun BreakText() {
    var breakState by remember { mutableStateOf(1) }

    Row (verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center){
        if(breakState == 1) {
            Button(
                onClick = { breakState = 2 },
                modifier = Modifier
//                .align(CenterHorizontally)
                    .padding(top = 300.dp, start = 60.dp, end = 60.dp)
                    .height(150.dp)
                    .width(260.dp)
//                .wrapContentSize(
//                    Alignment.Center
//                )
                    .clip(RoundedCornerShape(20.dp)),
//                Alignment.CenterHorizontally,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = androidx.compose.ui.graphics.Color.White,
                    contentColor = androidx.compose.ui.graphics.Color.Black
                )
            )
            {
                Text(
                    text = "Great u got this far. \nEnjoy your break!",
//                style = MaterialTheme.typography.h4,
//                color = androidx.compose.ui.graphics.Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
        else {
            Box(modifier = Modifier
                .padding(top = 30.dp, start = 30.dp, end = 30.dp, bottom = 300.dp)
                .height(1000.dp)
                .width(500.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(color = androidx.compose.ui.graphics.Color.White),
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
                        .fillMaxSize()
                ) {
                    Text(
                        text = "Here's some anime suggestions as a reward. Enjoy!",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = androidx.compose.ui.graphics.Color.Black,

                    )
                }
                AnimeSuggestionsList(animeSuggestions = Datasource().loadAnimeSuggestions())
            }
        }

    }
}

@Composable
fun AnimeSuggestionsList(animeSuggestions: List<AnimeSuggestions>, modifier: Modifier = Modifier) {
    LazyColumn (modifier = Modifier.padding(top = 100.dp, start = 20.dp, bottom = 0.dp, end = 20.dp)){
        items(animeSuggestions) {
                suggestions ->  AnimeSuggestionsCard(suggestions = suggestions)
        }
    }
}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AnimeSuggestionsCard(suggestions: AnimeSuggestions,
                   modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(suggestions.animeLink)) }
    Card(modifier = Modifier
        .padding(start = 20.dp, top = 0.dp, end = 20.dp, bottom = 20.dp)
        .height(80.dp)
        .width(650.dp),
        elevation = 10.dp,
        backgroundColor = secondary,
        shape = RoundedCornerShape(20.dp),
        onClick = {
            context.startActivity(intent)
        }
    ) {
        Row {
            Image(
                painter =
                painterResource(id = suggestions.animeImage),
                contentDescription = suggestions.animeName,
                modifier = Modifier
                    .width(97.dp)
                    .height(97.dp)
                    .clip(RoundedCornerShape(20.dp)),

                contentScale = ContentScale.Crop
            )
            Column() {
                Row (modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp, bottom = 0.dp, end = 0.dp)) {


                    Text(text = suggestions.animeName,color = White)
                }

            }
        }
//        Box {
//            Column(
//                modifier = Modifier
//                    .padding(top = 0.dp, start = 0.dp, end = 125.dp, bottom = 0.dp)
//                    .fillMaxSize()
//            ) {
//                Image(
//                    painter =
//                    painterResource(id = suggestions.animeImage),
//                    contentDescription = suggestions.animeName,
//                    modifier = Modifier
//                        .fillMaxHeight()
//                        .fillMaxWidth(),
//
//                    contentScale = ContentScale.Crop
//                )
//            }
//            Column(
//                modifier = Modifier
//                    .padding(start = 116.dp, top = 25.dp, bottom = 25.dp, end = 20.dp)
//            ) {
//                Text(
//                    text = suggestions.animeName, color = White,
//                    textAlign = TextAlign.Center
//                )
//            }
//        }
    }
}

@Composable
fun ChibiAnimation() {
    var chibiState by remember { mutableStateOf(ChibiPosition.One) }

    val infiniteTransition = rememberInfiniteTransition()
    val offsetAnimation by infiniteTransition.animateValue(
        initialValue = (-130).dp,
        targetValue = (-130).dp,
        typeConverter = Dp.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 10000
                (-240).dp at 1000
                20.dp at 4000
                20.dp at 8000
            },
            repeatMode = RepeatMode.Restart
        )
    )
//    val offsetAnimation: Dp by animateDpAsState(
//        when (chibiState) {
//            ChibiPosition.One -> (-130).dp
//            ChibiPosition.Two -> 0.dp
//            ChibiPosition.Three -> 10.dp
//            else -> 25.dp
//        },
//        animationSpec = tween(1000)
//    )
    Box(
        modifier = Modifier
            .fillMaxSize(),
        Alignment.BottomStart
    ) {
        Image(
            painter = painterResource(R.drawable.chibi_levi),
            contentDescription = null,
            modifier = Modifier
                .height(250.dp)
//                .height(1000.dp)
//                .aspectRatio(painter.intrinsicSize.width / painter.intrinsicSize.height)
                .absoluteOffset(x = offsetAnimation) ,
//            contentScale = ContentScale.Fit

        )
    }
}

//@Composable
//fun Confetti() {
//    // Creates the infinite transition
//    val infiniteTransition = rememberInfiniteTransition()
//
//    // Animate from 0f to 1f
//    val animationProgress by infiniteTransition.animateFloat(
//        initialValue = 0f,
//        targetValue = 1f,
//        animationSpec = infiniteRepeatable(
//            animation = tween(durationMillis = 800)
//        )
//    )
//
//    Box(
//        modifier = Modifier
//            .scale(animationProgress)
//            .alpha(1 - animationProgress),
//    )
//}

//@Composable
//fun GifImage(
//    modifier: Modifier = Modifier,
//) {
//    val context = LocalContext.current
//    val imageLoader = ImageLoader.Builder(context)
//        .components {
//            if (SDK_INT >= 28) {
//                add(ImageDecoderDecoder.Factory())
//            } else {
//                add(GifDecoder.Factory())
//            }
//        }
//        .build()
//    Image(
//        painter = rememberAsyncImagePainter(
//            ImageRequest.Builder(context).data(data = R.drawable.YOUR_GIF_HERE).apply(block = {
//                size(Size.ORIGINAL)
//            }).build(), imageLoader = imageLoader
//        ),
//        contentDescription = null,
//        modifier = modifier.fillMaxWidth(),
//    )
//}