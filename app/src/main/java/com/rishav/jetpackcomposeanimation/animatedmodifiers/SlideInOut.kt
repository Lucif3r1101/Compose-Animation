package com.rishav.jetpackcomposeanimation.animatedmodifiers

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SlideFadeInOut(navController: NavController) {
    var isVisible by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.horizontalGradient(listOf(Color.Blue, Color.Green, Color.Gray))),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // First Composable
        SlideFadeInContent(isVisible = isVisible)

        if(!isVisible){
            SlideFadeOutContent(isVisible = isVisible)
        }

        // Button to toggle visibility
        Button(
            onClick = {
                isVisible = !isVisible
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Toggle")
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text("Back to Main")
        }
    }
}

@Composable
fun SlideFadeInContent(isVisible: Boolean) {
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInWithFade(),
        exit = slideOutWithFade()
    ) {
        // Content that will be animated in and out
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Color.Blue)
        ) {
            // Content in the enter transition
            Text(
                text = "Slide In and Out Transition",
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun SlideFadeOutContent(isVisible: Boolean) {

    AnimatedVisibility(
        visible = !isVisible,
        enter = slideInWithFade(),
        exit = slideOutWithFade()
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Color.Red)
        ) {
            // Content in the enter transition
            Text(
                text = "Slide In and Out Transition",
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun slideInWithFade() : EnterTransition {
    val slide = remember { slideInVertically(initialOffsetY = { -500 }) }
    val fadeIn = fadeIn(animationSpec = tween(200))
    return slide + fadeIn
}


@Composable
fun slideOutWithFade() : ExitTransition {
    val slide = remember { slideOutVertically(targetOffsetY = { -200 }) }
    val fadeOut = fadeOut(animationSpec = tween((500)))
    return slide + fadeOut
}



