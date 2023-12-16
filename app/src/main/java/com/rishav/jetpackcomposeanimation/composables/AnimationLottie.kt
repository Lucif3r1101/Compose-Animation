package com.rishav.jetpackcomposeanimation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.rishav.jetpackcomposeanimation.R

@Composable
fun AnimationLottie(navController: NavController) {
    var isAnimating by remember { mutableStateOf(true) }
    val density = LocalDensity.current.density
    val lottieModifier = Modifier
        .fillMaxSize()
        .background(Color.Gray)

    Box(modifier = Modifier.fillMaxSize()) {
        rememberCoroutineScope()
        val composition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(R.raw.moon) // Replace with your Lottie JSON resource
        )

        val progress by animateLottieCompositionAsState(
            composition = composition,
            iterations = if (isAnimating) LottieConstants.IterateForever else 1
        )

        LottieAnimation(
            composition,
            progress = progress,
            modifier = lottieModifier.then(
                Modifier
                    .graphicsLayer(
                        translationX = with(density) {
                            if (isAnimating) {
                                // Adjust the X translation as needed for your linear motion
                               5f.dp.dpToPx()
                            } else {
                                0f
                            }
                        }
                    )

            )
        )

        // Toggle animation when the animation is clicked
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    isAnimating = !isAnimating
                }
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
}

@Composable
fun Dp.dpToPx() = with(LocalDensity.current) { this@dpToPx.toPx() }


@Composable
fun Int.pxToDp() = with(LocalDensity.current) { this@pxToDp.toDp() }