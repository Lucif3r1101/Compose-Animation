package com.rishav.jetpackcomposeanimation.animatedvectordrawables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
@Composable
fun SimpleAnimationsScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Simple Animations Screen")

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("animatableDrawables")
            }
        ) {
            Text("Simple plane Animations")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("animatableSVGs")
            }
        ) {
            Text("Animations via SVGs")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("animatableLottie")
            }
        ) {
            Text("Animations via Lottie")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text("Back to Controller Screen")
        }
    }
}
