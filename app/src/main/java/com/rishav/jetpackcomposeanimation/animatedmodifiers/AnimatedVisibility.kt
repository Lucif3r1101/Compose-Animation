import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.rishav.jetpackcomposeanimation.R

@Composable
fun AnimatedVisibilityExample(navController: NavController) {
    var isContent1 by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.horizontalGradient(listOf(Color.Yellow, Color.Green, Color.Gray))),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // Content1 is always visible
            Content1(zIndex = if (isContent1) 1f else 0f)

            // Content2 is animated behind Content1 when switching visibility
            androidx.compose.animation.AnimatedVisibility(
                visible = !isContent1,
                enter = fadeIn(animationSpec = tween(1000,easing = LinearEasing)),
                exit = fadeOut(animationSpec = tween(1000, easing = LinearEasing))
            ) {
                Content2(zIndex = if (isContent1) 0f else 1f)
            }
        }
    }

    Column(
        modifier = Modifier
            .size(32.dp)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(modifier = Modifier.height(50.dp).width(150.dp), onClick = { isContent1 = !isContent1 }) {
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
            Text("Back to Previous Screen")
        }
    }
}

@Composable
fun Content1(zIndex: Float) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .zIndex(zIndex)
    ) {
        Image(
            painter = ColorPainter(Color.Blue),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .background(Color.Gray)
        )

        Text(
            text = "Content 1",
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 24.sp,
            color = Color.Yellow
        )
    }
}

@Composable
fun Content2(zIndex: Float) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .zIndex(zIndex)
    ) {
        Image(
            painter = ColorPainter(Color.Red),
            contentDescription = null,
            modifier = Modifier
                .size(300.dp)
                .background(Color.Gray)
        )

        Text(
            text = "Content 2",
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 24.sp,
            color = Color.Red
        )
    }
}
