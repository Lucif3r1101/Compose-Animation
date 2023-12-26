package com.rishav.jetpackcomposeanimation.animatedvectordrawables

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
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

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f, // 360 degrees for one full rotation
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 3000
                0f at 0 with FastOutLinearInEasing
                360f at 3000 // 360 degrees at the end
            },
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier
            .size(100.dp)
            .offset(x = 0.dp, y = 0.dp)
            .graphicsLayer(rotationX = rotation)
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
            Text("Back to Previous Screen")
        }
    }
}


