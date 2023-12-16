package com.rishav.jetpackcomposeanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rishav.jetpackcomposeanimation.composables.AnimatedSVGImage
import com.rishav.jetpackcomposeanimation.composables.AnimationLottie
import com.rishav.jetpackcomposeanimation.composables.transition.composables.AnimatedImageConten
import com.rishav.jetpackcomposeanimation.composables.transition.composables.AnimatedVisibility
import com.rishav.jetpackcomposeanimation.composables.transition.composables.CrossfadeExample
import com.rishav.jetpackcomposeanimation.composables.transition.composables.FadeInOut
import com.rishav.jetpackcomposeanimation.composables.transition.composables.ScaleInOut
import com.rishav.jetpackcomposeanimation.composables.transition.valueanimations.AnimatedAsState
import com.rishav.jetpackcomposeanimation.ui.theme.JetpackComposeAnimationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navHostController = rememberNavController()

            JetpackComposeAnimationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Navigation(navHostController = navHostController)
                }
            }
        }
    }
}

@Composable
fun Navigation(navHostController: NavHostController) {
    NavHost(navHostController, startDestination = "home") {
        composable("home") {
            MainScreen(navHostController)
        }
        composable("animatedSvg") {
            AnimatedSVGImage(navHostController)
        }
        composable("animatedLottie"){
            AnimationLottie(navHostController)
        }
        composable("fadeInOut"){
            FadeInOut(navController = navHostController)
        }
        composable("scaleOutIn"){
            ScaleInOut(navController = navHostController)
        }
        composable("animatedVisibility"){
            AnimatedVisibility(navController = navHostController)
        }
        composable("animatedContent"){
            AnimatedImageConten(navController = navHostController)
        }
        composable("crossfade"){
            CrossfadeExample(navController = navHostController)
        }
        composable("animateValue"){
            AnimatedAsState()
        }
    }
}


@Composable
fun MainScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Main Screen")

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("animatedSvg")
            }
        ) {
            Text("Go to Animate")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("animatedLottie")
            }
        ) {
            Text("Go to AnimateLottie")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("fadeInOut")
            }
        ) {
            Text("Go to FadeInOutTransition")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("scaleOutIn")
            }
        ) {
            Text("Go to ScaleInOutTransition")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("animatedVisibility")
            }
        ) {
            Text("Go to AnimatedVisibility")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("animatedContent")
            }
        ) {
            Text("Go to AnimatedContent")
        }


        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("crossfade")
            }
        ) {
            Text("Go to Crossfade")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("animateValue")
            }
        ) {
            Text("Go to ValueBased animation")
        }

    }
}