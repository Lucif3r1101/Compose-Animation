package com.rishav.jetpackcomposeanimation.composables.transition.valueanimations

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rishav.jetpackcomposeanimation.R


@Preview
@Composable
fun AnimatedAsState() {
    var animationEnabled by remember { mutableStateOf(true) }

    val animatedFloat by animateFloatAsState(
        if (animationEnabled) 360f else 0f,
        tween(durationMillis = 1000),
        label = "Angle"
    )

    val animatedInt by animateIntAsState(
        if (animationEnabled) 100 else 0,
        spring(Spring.DampingRatioLowBouncy),
        label = "Count"
    )

    val animatedDp by animateDpAsState(
        if (animationEnabled) 100.dp else 0.dp,
        repeatable(
            iterations = 2,
            animation = tween(durationMillis = 500),
        ),
        label = "Dp"
    )

    val animatedColor by animateColorAsState(
        if (animationEnabled) Color.Red else Color.Blue,
        infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "Color"
    )

    val animatedSize = AnimateSizeWithDp(animationEnabled, "Size")

    val animatedOffset by animateOffsetAsState(
        if (animationEnabled) Offset(0f, 0f) else Offset(100.dp.value, 100.dp.value),
        infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "Offset"
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .graphicsLayer(
                    translationX = animatedFloat,
                    translationY = animatedFloat,
                ),
            contentDescription = null,
            painter = painterResource(id = R.drawable.ic_running_man)
        )

    }

    Spacer(modifier = Modifier.height(16.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            DisplayAnimatedValue(animatedFloat, "°")
            DisplayAnimatedValue(animatedInt.toFloat(), "")
            DisplayAnimatedValue(animatedOffset.x, "dp")
            DisplayAnimatedValue(animatedOffset.y, "dp")
        }

        Column {
            Box(
                modifier = Modifier
                    .size(animatedSize)
                    .background(animatedColor)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .size(animatedDp)
                    .background(Color.Gray)
            )
        }
    }


Button(
onClick = {
    animationEnabled = !animationEnabled
},
modifier = Modifier.padding(16.dp)
) {
    Text(text = if (animationEnabled) "Stop Animation" else "Start Animation")
}
}

@Composable
fun DisplayAnimatedValue(value: Float, unit: String) {
    Text(
        text = "${value.toString()} $unit",
        fontSize = 20.sp,
    )
}

@Composable
fun AnimateSizeWithDp(animationEnabled: Boolean, label: String): DpSize {
    val width: Dp
    val height: Dp

    if (animationEnabled) {
        width = 100f.dp
        height = 100f.dp
    } else {
        width = 50f.dp
        height = 50f.dp
    }

    val animatedWidth = animateDpAsState(width, spring(dampingRatio = Spring.DampingRatioLowBouncy), label = "$label Width")
    val animatedHeight = animateDpAsState(height, spring(dampingRatio = Spring.DampingRatioLowBouncy), label = "$label Height")

    return DpSize(animatedWidth.value, animatedHeight.value)
}

