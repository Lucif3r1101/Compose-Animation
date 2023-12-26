package com.rishav.jetpackcomposeanimation.animatedvectordrawables

import androidx.compose.animation.core.*
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rishav.jetpackcomposeanimation.R

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun SimpleAnimatedVectorDrawable(navController: NavController) {
    var isAnimating by remember { mutableStateOf(false) }

    val animatedHeart = AnimatedImageVector.animatedVectorResource(R.drawable.ic_heart)

    val infiniteTransition = rememberInfiniteTransition(label = "simple animations")

    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val animatedPainter = rememberAnimatedVectorPainter(animatedHeart, isAnimating)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.qoh),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
            )

            Image(
                painter = animatedPainter,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .offset(
                        x = with(LocalDensity.current) { 100.dp * progress },
                        y = with(LocalDensity.current) { (-10).dp * progress }
                    )
                    .align(Alignment.Center)
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


    }
}

