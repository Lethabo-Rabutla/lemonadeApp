package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeImage(modifier= Modifier
                        .wrapContentSize())
                }
            }
        }
    }
}

@Composable
fun LemonadeImage(modifier: Modifier = Modifier) {

    var results by remember { mutableStateOf(1) }
    val lastValue = 4 // Define the last value of the range
    val randomNumber = (2..4).random()
    val imageResource = when (results) {
        in 1..lastValue -> {
            val index = (results - 1) % lastValue + 1
            when (index) {
                1 -> R.drawable.lemon_tree
                2 -> R.drawable.lemon_squeeze
                3 -> R.drawable.lemon_drink
                else -> R.drawable.lemon_restart
            }
        }
        else -> R.drawable.lemon_restart
    }
    val buttonText = when (results) {
        1 -> "Tap the lemon tree to select a lemon."
        2 -> "Keep tapping the lemon to squeez it."
        3 -> "Tap the lemonade to drink it."
        else -> "Tap the empty class to start again"
    }
    var tapCount by remember { mutableStateOf(0) }
    val onLemonTap: () -> Unit = {
        if (results == 2) {
            tapCount++
            if (tapCount == 3) {
                results++
                tapCount = 0 // Reset tap count
            }
        } else {
            results++
        }
    }

    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Lemonade",
            modifier = Modifier
                .background(Color.Yellow)
                .padding(horizontal = 139.dp, vertical = 20.dp),
            color = Color.Black

        )
        Spacer(modifier = modifier.height(180.dp))
        Button(onClick = { results = (results % lastValue) + 1 },
            shape = RoundedCornerShape(40.dp)
        ) {
            Image(painter = painterResource(id = imageResource),
                contentDescription = results.toString(),

                )
        }
        Spacer(modifier = modifier.height(50.dp))
        Text(
            text = buttonText
        )
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        LemonadeImage(modifier = Modifier)
    }
}