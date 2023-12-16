package com.rishav.jetpackcomposeanimation.composables.transition.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.animation.*
import androidx.navigation.NavController


@Composable
fun FadeInOut(navController: NavController) {
    var isVisible by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                    text = "Enter Transition",
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { isVisible = !isVisible },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = if (isVisible) "Hide" else "Show")
        }


    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(16.dp))

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
fun slideInWithFade() : EnterTransition {
    val slide = remember { slideInVertically(initialOffsetY = { 200 }) }
    val fadeIn = fadeIn()
    return slide + fadeIn
}


@Composable
fun slideOutWithFade() : ExitTransition {
    val slide = remember { slideOutVertically(targetOffsetY = { 200 }) }
    val fadeOut = fadeOut()
    return slide + fadeOut
}


