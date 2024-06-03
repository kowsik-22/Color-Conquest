package com.example.myapplication

import NavigationStack
import android.content.Intent
import android.graphics.Path
import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.ui.Alignment
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.regular_one
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationStack()
            }
        }
    }


@Composable
fun content(
    modifier: Modifier = Modifier,
    navController: NavController
){
    val text = remember {
        mutableStateOf("")
    }

    Column(verticalArrangement = Arrangement.Center,
    modifier = modifier){
    val image = painterResource(R.drawable.screenshot_2024_05_27_011404_removebg_preview
    )

    val gradientColorList = listOf(
        Color(4294883696),
        Color(4294944880),
        Color(4294928237)
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = GradientBrush(
                    isVerticalGradient = true,
                    colors = gradientColorList
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "CONQUEST",
            fontSize = 57.sp,
            fontFamily = regular_one,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 160.dp),
            style = TextStyle(
                brush = GradientBrush(
                    isVerticalGradient = true,
                    colors = listOf(
                        Color(0xFF543331),
                        Color(0xFFcc7d78))
                )
            )
        )

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(0.1.dp),
            horizontalArrangement = Arrangement.SpaceEvenly){
            Button(
                onClick = { navController.navigate(route = Screen.Detail.route) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2FB6F0),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .padding(top = 650.dp, start = 100.dp)
                    .size(width = 200.dp, height = 75.dp)
            ) {
                Text(text = "PLAY", fontSize = 35.sp)
            }

            Dialog()
        }

        Image(
            painter = image,
            contentDescription = null,
            modifier= Modifier
                .padding(top = 100.dp)
                .size(350.dp),
            alpha = 1F
        )

        Text(
            text = "COLOR",
            color = Color(0xFF3B1D10),
            fontFamily = regular_one,
            fontSize = 57.sp,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 80.dp)
        )
    }
    }
}

@Composable
fun GradientBrush(
    isVerticalGradient: Boolean,
    colors: List<Color>
): Brush {
    val endOffset = if(isVerticalGradient){
        Offset(0F,Float.POSITIVE_INFINITY)
    } else{
        Offset(Float.POSITIVE_INFINITY, 0f)
    }

    return Brush.linearGradient(
        colors=colors,
        start = Offset.Zero,
        end = endOffset
    )
}

@Composable
fun Dialog(){
    Column (modifier = Modifier.fillMaxWidth()){
        val opendialog = remember {
            mutableStateOf(false)
        }

        val rules: List<String> = listOf("1. Players can choose any tile on the grid on their turn only. Clicking a tile assigns your colour to it and awards you 3 points.\n",
            "2. Clicking a tile with your colour adds 1 point to that tile\n",
            "3. When a tile with your colour reaches 4 points, it triggers an expansion\n",
            "4. Players take turns clicking on tiles and the objective is to eliminate your opponent's colour entirely from the screen\n")

        Button(
            onClick = { opendialog.value=true },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF000080),
                contentColor = Color.White
            ),
            modifier = Modifier
                .padding(top = 650.dp, start = 10.dp)
                .size(60.dp)
                .clip(CircleShape)
        ) {
            Text(text = "?", fontSize = 30.sp)
        }

        if (opendialog.value){
            AlertDialog(
                onDismissRequest = { opendialog.value = false },
                title = {
                    Text(text = "Rules",fontSize = 30.sp)
                },
                text = {
                    Box(modifier = Modifier.height(200.dp)) { // Adjust the height as needed
                        LazyColumn {
                            items(rules) { Text(text = "$it", fontSize = 20.sp)
                            }
                        }
                    }
                },
                confirmButton = {
                    Button(
                        onClick = { opendialog.value = false }
                    ) {
                        Text(text = "OK")
                    }
                },
            )
        }
    }
}


@Composable
fun content1(
    modifier: Modifier = Modifier
){ Column(verticalArrangement = Arrangement.Center,
    modifier = modifier) {
    val image = painterResource(R.drawable.screenshot_2024_05_27_011404_removebg_preview)
    val image1 = painterResource(R.drawable.screenshot_2024_05_30_191124_removebg_preview)
    val redicon = painterResource(R.drawable.redicon_removebg_preview)
    val blueicon = painterResource(R.drawable.remove_bg_ai_1717230881743)

    val gradientColorList = listOf(
        Color(4294883696),
        Color(4294944880),
        Color(4294928237)
    )
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = GradientBrush(
                    isVerticalGradient = true,
                    colors = gradientColorList
                )
            ),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .padding(top = 270.dp)
                .size(350.dp),
            alpha = 1F
        )

        Image(
            painter = image1,
            contentDescription = null,
            modifier = Modifier
                .padding(end = 200.dp, bottom = 200.dp)
                .size(height = 350.dp, width = 200.dp),
            alpha = 1F
        )


        Box (modifier = Modifier.padding (bottom = 50.dp, start = 200.dp)) {
            Card(
                modifier = Modifier
                    .size(width = 180.dp, height = 102.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF3e4176)
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column( verticalArrangement = Arrangement.spacedBy(0.5.dp)) {
                    Image(
                        painter = blueicon,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 2.dp, start = 70.dp)
                            .size(height = 50.dp, width = 40.dp),
                        alpha = 1F
                    )
                    TransparentTextField2()
                }
            }
        }


        Box (modifier = Modifier.padding (bottom = 320.dp, start = 200.dp)) {
            Card(
                modifier = Modifier
                    .size(width = 180.dp, height = 102.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF3e4176)
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column() {
                    Image(
                        painter = redicon,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 2.dp, start = 70.dp)
                            .size(height = 50.dp, width = 40.dp),
                        alpha = 1F
                    )
                    TransparentTextField1()
                }
            }
        }


        Button(
            onClick = {/*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2FB6F0),
                contentColor = Color.White
            ),
            modifier = Modifier
                .padding(top = 650.dp)
                .size(width = 200.dp, height = 75.dp)
        ) {
            Text(text = "START", fontSize = 35.sp)
        }

        Card(
            modifier = Modifier
                .padding(bottom = 700.dp, start = 20.dp, end = 20.dp)
                .size(width = 350.dp, height = 50.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFffd4b8)
            )
        )
        {
            Text(
                text = "        PLAYER  INFORMATION",
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color(0xFF5C4033),
                modifier = Modifier
                    .padding(10.dp)
            )
        }
    }
}
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransparentTextField1()
{
    var textstate = remember { mutableStateOf("") }
    TextField(value = textstate.value, onValueChange = {
        textstate.value = it },
        modifier = Modifier.border(width = 2.dp, color = Color.Transparent),
        placeholder = {
            Text(text = "Enter Player-1 Name", color = Color.Gray, fontSize = 15.sp) },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent),
        textStyle = TextStyle(color = Color.White)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransparentTextField2()
{
    var textstate = remember { mutableStateOf("") }
    TextField(value = textstate.value, onValueChange = {
        textstate.value = it },
        modifier = Modifier.border(width = 2.dp, color = Color.Transparent),
        placeholder = {
            Text(text = " Enter Player-2 Name", color = Color.Gray, fontSize = 14.5.sp) },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent),
        textStyle = TextStyle(color = Color.White)
    )
}



@Preview(showBackground = true)
@Composable
fun ContentPreview() {
    MyApplicationTheme {
        NavigationStack()
    }
}

