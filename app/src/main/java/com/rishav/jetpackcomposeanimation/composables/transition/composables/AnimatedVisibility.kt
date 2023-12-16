package com.rishav.jetpackcomposeanimation.composables.transition.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AnimatedVisibility(navController: NavController) {
    var isContent1 by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = isContent1,
            enter = fadeIn(animationSpec = tween(1000)),
            exit = fadeOut(animationSpec = tween(1000))
        ) {
            Content1()
        }

        AnimatedVisibility(
            visible = !isContent1,
            enter = fadeIn(animationSpec = tween(1000)),
            exit = fadeOut(animationSpec = tween(1000))
        ) {
            Content2()
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { isContent1 = !isContent1 }) {
            Text("Toggle Content")
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
fun Content1() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = ColorPainter(Color.Blue),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .background(Color.Gray)
        )

        Text(
            text = "Content 1",
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 24.sp,
            color = Color.Black
        )
    }
}

@Composable
fun Content2() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = ColorPainter(Color.Red),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .background(Color.Gray)
        )

        Text(
            text = "Content 2",
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 24.sp,
            color = Color.Black
        )
    }
}
