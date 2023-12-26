package com.rishav.jetpackcomposeanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rishav.jetpackcomposeanimation.animatedmodifiers.AnimatedVisibility
import com.rishav.jetpackcomposeanimation.animatedmodifiers.ComposablesModifiersScreen
import com.rishav.jetpackcomposeanimation.animatedmodifiers.ScaleInOut
import com.rishav.jetpackcomposeanimation.animatedmodifiers.SlideFadeInOut
import com.rishav.jetpackcomposeanimation.animatedvectordrawables.AnimatedSVGImage
import com.rishav.jetpackcomposeanimation.animatedvectordrawables.AnimationLottie
import com.rishav.jetpackcomposeanimation.animatedvectordrawables.SimpleAnimatedVectorDrawable
import com.rishav.jetpackcomposeanimation.animatedvectordrawables.SimpleAnimationsScreen
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
        composable("controllerScreens") {
            ControllingScreen(navController = navHostController)
        }
        composable("simpleAnimations") {
            SimpleAnimationsScreen(navController = navHostController)
        }
        composable("animatableDrawables") {
            SimpleAnimatedVectorDrawable(navController = navHostController)
        }
        composable("animatableLottie") {
            AnimationLottie(navController = navHostController)
        }
        composable("animatableSVGs") {
            AnimatedSVGImage(navController = navHostController)
        }
        composable("animationsModifiers") {
            ComposablesModifiersScreen(navController = navHostController)
        }
        composable("animationsVisibility") {
            AnimatedVisibility(navController = navHostController)
        }
        composable("animationsSlideInOut") {
            SlideFadeInOut(navController = navHostController)
        }
        composable("animationsScaleInOut") {
            ScaleInOut(navController = navHostController)
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
                navController.navigate("controllerScreens")
            }
        ) {
            Text("Go to Controller Screen")
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}