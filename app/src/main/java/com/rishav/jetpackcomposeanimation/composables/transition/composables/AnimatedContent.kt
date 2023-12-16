package com.rishav.jetpackcomposeanimation.composables.transition.composables

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rishav.jetpackcomposeanimation.R
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedImageConten(navController: NavController) {
    var isContent1 by remember { mutableStateOf(true) }

    val transition = updateTransition(targetState = isContent1, label = "")

    val alpha by transition.animateFloat(label = "") { targetState ->
        if (targetState) 1f else 0f
    }

    LaunchedEffect(isContent1) {
        while (true) {

            delay(3000)

            // Switch to Content 1
            isContent1 = true

            delay(3000)

            // Switch to Content 2
            isContent1 = false
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedContent(
            targetState = isContent1,
            transitionSpec = {
                fadeIn() + slideInVertically(animationSpec = tween(3000),
                    initialOffsetY = { fullHeight -> fullHeight }) with
                        fadeOut(animationSpec = tween(3000))
            }, label = ""
        ) { state ->
            when (state) {
                true -> Content1(alpha)
                false -> Content2(alpha)
            }
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
fun Content1(alpha: Float) {
    Column(
        modifier = Modifier
            .alpha(alpha)
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.sun),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .background(Color.Gray)
        )

        Text(
            text = "Content 1",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black
        )
    }
}

@Composable
fun Content2(alpha: Float) {
    Column(
        modifier = Modifier
            .alpha(alpha)
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.moon),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .background(Color.Gray)
        )

        Text(
            text = "Content 2",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black
        )
    }
}
