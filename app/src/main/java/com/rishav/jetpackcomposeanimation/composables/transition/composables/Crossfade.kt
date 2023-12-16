package com.rishav.jetpackcomposeanimation.composables.transition.composables


import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import coil.transform.GrayscaleTransformation
import kotlinx.coroutines.delay

@Composable
fun CrossfadeExample(navController: NavController) {
    var isContent1 by remember { mutableStateOf(true) }

    LaunchedEffect(isContent1) {
        while (true) {
            delay(2000)

            isContent1 = false

            delay(2000)

            isContent1 = true
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Crossfade(targetState = isContent1, label = "") { state ->
            when (state) {
                true -> ContentOne()
                false -> ContentTwo()
            }
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
fun ContentOne() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val painter =
            rememberImagePainter(data = "https://images.unsplash.com/photo-1628373383885-4be0bc0172fa?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1301&q=80",
                builder = {
                    transformations(
                        GrayscaleTransformation(),
                    )
                })
        Image(

            painter = painter,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ContentTwo() {
    val painter =
        rememberImagePainter(data = "https://images.unsplash.com/photo-1628373383885-4be0bc0172fa?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1301&q=80",
            builder = {
                transformations(
                    GrayscaleTransformation(),
                    CircleCropTransformation()
                )
            })
    Image(

        painter = painter,
        contentDescription = null,
        modifier = Modifier.fillMaxSize()
    )
}
