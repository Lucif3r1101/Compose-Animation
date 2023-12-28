package com.rishav.jetpackcomposeanimation.animatedmodifiers

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.animation.*
import androidx.compose.ui.graphics.Brush
import androidx.navigation.NavController

@Composable
fun ScaleInOut(navController: NavController) {
    var isVisible by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(Brush.sweepGradient(listOf(Color.Blue, Color.Green, Color.Gray))),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = isVisible,
            enter = scaleInWithExpand(),
            exit = scaleOutWithShrink()
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
fun scaleInWithExpand() : EnterTransition {

    val expandHorizontally = expandHorizontally(animationSpec = tween(500))
    val scaleIn = scaleIn()

    return expandHorizontally + scaleIn
}

@Composable
fun scaleOutWithShrink() : ExitTransition {

    val shrinkVertically = shrinkVertically(animationSpec = tween(500))
    val scaleOut = scaleOut()

    return shrinkVertically + scaleOut
}
