package com.rishav.jetpackcomposeanimation.composables

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rishav.jetpackcomposeanimation.R


@Composable
fun AnimatedSVGImage(navController: NavController) {
    // Load your image using painterResource
    val painter = painterResource(id = R.drawable.moonshine)

    // Initialize the animation controller
    val infiniteTransition = rememberInfiniteTransition(label = "infiniteTransition")

    val xOffset by infiniteTransition.animateValue(
        initialValue = 0f,
        targetValue = 400f, // Adjust this value as needed
        typeConverter = Float.VectorConverter, // Specify the type here
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "animation"
    )

    Image(
        painter = painter,
        contentDescription = null, // Provide a content description
        modifier = Modifier.size(100.dp).offset(x = xOffset.dp, y = 0.dp)
    )

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


