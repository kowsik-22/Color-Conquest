package com.example.myapplication.ui.theme


import android.widget.Button
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DividerDefaults.color
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

var p1points : Int = 0
var p2points : Int = 0
var cp : Int = 1
var counter : Int = 2

@Composable
fun tictac(){
    val board = remember{
        mutableStateOf(Array(5){ Array<Int>(5){(0)} })
    }
    val currentplayer = remember {
        mutableStateOf("Player1")
    }

    val tilecolor = remember {
        mutableStateOf(Array(5){ Array(5){Color.LightGray} })
    }


    var boxColor by remember {
        mutableStateOf(Color.Blue)
    }

    //val onClickFunction = remember { { expansion() } }

    var isClicked by remember { mutableStateOf(false) }
    var isbutton by remember { mutableStateOf(false) }
    var cell : Int = 0


    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)){

        Box(modifier = Modifier
            .fillMaxSize()
            .background(boxColor)){

            Column (modifier = Modifier.padding(top = 170.dp)){
                for (row in 0 .. 4){
                    Row(modifier = Modifier.padding(start = 30.dp)){
                        for (col in 0..4 ){
                            Button(modifier = Modifier
                                .padding(top = 20.dp, start = 3.dp, end = 10.dp, bottom = 10.dp)
                                .size(60.dp)
                                .border(
                                    width = 2.dp,
                                    color = Color.White,
                                    shape = RoundedCornerShape(8.dp)
                                ),
                                shape = RoundedCornerShape(8.dp),
                                onClick = { isClicked = true
                                    if (counter==1 || counter==2){
                                        if (board.value[row][col]==0 && cp==1){
                                            board.value[row][col]=3
                                            cp=2
                                            tilecolor.value[row][col] = Color.Blue
                                            boxColor = if(boxColor==Color.Blue) Color.Red else Color.Blue
                                        }
                                        else if (board.value[row][col] == 0 && cp==2){
                                            board.value[row][col]=3
                                            cp=1
                                            tilecolor.value[row][col] = Color.Red
                                            boxColor = if(boxColor==Color.Blue) Color.Red else Color.Blue
                                        }
                                        counter-- }
                                    else {
                                        isClicked=true
                                        if (cp==1 && tilecolor.value[row][col] == Color.Blue) {
                                            if (board.value[0][0] == 3) {
                                                board.value[0][1] += 1
                                                board.value[1][0] += 1
                                                board.value[row][col] = 0
                                            } else if (board.value[0][4] == 3) {
                                                board.value[1][4] += 1
                                                board.value[0][3] += 1
                                                board.value[row][col] = 0
                                            } else if (board.value[4][0] == 3) {
                                                board.value[3][0] += 1
                                                board.value[4][1] += 1
                                                board.value[row][col] = 0
                                            } else if (board.value[row][0] == 3 || board.value[row][4] == 3 && row != 0 && row != 4) {
                                                board.value[row + 1][col] += 1
                                                board.value[row - 1][col] += 1
                                                board.value[row][col] = 0
                                            } else if (board.value[0][col] == 3 || board.value[4][col] == 3 && col != 0 && col != 4) {
                                                board.value[row][col - 1] += 1
                                                board.value[row][col + 1] += 1
                                                board.value[row - 1][col] += 1
                                                board.value[row][col] = 0 }
                                            if (board.value[row][col] == 3 || board.value[row][col] == 4) {
                                                board.value[row][col - 1] += 1
                                                tilecolor.value[row][col-1] = Color.Blue
                                                board.value[row][col + 1] += 1
                                                tilecolor.value[row][col+1] = Color.Blue
                                                board.value[row - 1][col] += 1
                                                tilecolor.value[row-1][col] = Color.Blue
                                                board.value[row + 1][col] += 1
                                                board.value[row][col] = 0
                                                tilecolor.value[row+1][col] = Color.Blue
                                            } else if (board.value[row][col] == 1 || board.value[row][col] == 2) {
                                                board.value[row][col] += 1
                                                board.value[row][col] = 0
                                            }
                                            tilecolor.value[row][col] = Color.Blue
                                            boxColor = if(boxColor==Color.Blue) Color.Red else Color.Blue
                                        }

                                        else if (cp==2 && tilecolor.value[row][col] == Color.Red){
                                            if (board.value[0][0] == 3) {
                                                board.value[0][1] += 1
                                                board.value[1][0] += 1
                                                board.value[row][col] = 0
                                            } else if (board.value[0][4] == 3) {
                                                board.value[1][4] += 1
                                                board.value[0][3] += 1
                                                board.value[row][col] = 0
                                            } else if (board.value[4][0] == 3) {
                                                board.value[3][0] += 1
                                                board.value[4][1] += 1
                                                board.value[row][col] = 0
                                            } else if (board.value[row][0] == 3 || board.value[row][4] == 3 && row != 0 && row != 4) {
                                                board.value[row + 1][col] += 1
                                                board.value[row - 1][col] += 1
                                                board.value[row][col] = 0
                                            } else if (board.value[0][col] == 3 || board.value[4][col] == 3 && col != 0 && col != 4) {
                                                board.value[row][col - 1] += 1
                                                board.value[row][col + 1] += 1
                                                board.value[row - 1][col] += 1
                                                board.value[row][col] = 0 }
                                            if (board.value[row][col] == 3 || board.value[row][col] == 4) {
                                                board.value[row][col - 1] += 1
                                                tilecolor.value[row][col-1] = Color.Red
                                                board.value[row][col + 1] += 1
                                                tilecolor.value[row][col+1] = Color.Red
                                                board.value[row - 1][col] += 1
                                                tilecolor.value[row-1][col] = Color.Red
                                                board.value[row + 1][col] += 1
                                                board.value[row][col] = 0
                                                tilecolor.value[row+1][col] = Color.Red
                                            } else if (board.value[row][col] == 1 || board.value[row][col] == 2) {
                                                board.value[row][col] += 1
                                                board.value[row][col] = 0
                                            }
                                            tilecolor.value[row][col] = Color.Red
                                            boxColor = if(boxColor==Color.Blue) Color.Red else Color.Blue
                                        }
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(Color.LightGray)
                            ){
                                if (isClicked && cp==2  && boxColor==Color.Red){
                                    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(40.dp)){
                                        Canvas(modifier = Modifier.fillMaxSize()){
                                            drawCircle(color = tilecolor.value[row][col], radius = size.height.times(0.5f))
                                        }
                                        Text(text = if (board.value[row][col]>0) "${board.value[row][col]}" else "", color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                                else if (isClicked && cp==1 && boxColor==Color.Blue){
                                    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(40.dp)){
                                        Canvas(modifier = Modifier.fillMaxSize()){
                                            drawCircle(color = tilecolor.value[row][col], radius = size.height.times(0.5f))
                                        }
                                        Text(text = if (board.value[row][col]>0) "${board.value[row][col]}" else "", color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Bold)
                                    }
                                }

                                currentplayer.value = if (currentplayer.value == "Player1") "Player2" else "Player1"

                            }


                        }
                    }
                }
            }
            p1bar()
            p2bar()
        }
    }
}


/*fun expansion(board: Array<Array<Int>>, row: Int, col: Int){
    if (board.value[row][col]==0){
        board.value[row][col]=3}
    else if (board.value[0][0]==3 ){
        board.value[0][1]+=1
        board.value[1][0]+=1
        board.value[row][col]=0}
    else if (board.value[0][4]==3 ){
        board.value[1][4]+=1
        board.value[0][3]+=1
        board.value[row][col]=0}
    else if (board.value[4][0]==3 ){
        board.value[3][0]+=1
        board.value[4][1]+=1
        board.value[row][col]=0}
    else if (board.value[row][0]==3 || board.value[row][4]==3 && row!=0 && row!=4) {
        board.value[row+1][col]+=1
        board.value[row-1][col]+=1
        board.value[row][col]=0}
    else if (board.value[0][col]==3 || board.value[4][col]==3 && col!=0 && col!=4) {
        board.value[row][col-1]+=1
        board.value[row][col+1]+=1
        board.value[row-1][col]+=1
        board.value[row][col]=0}
    else if (board.value[row][col]==3 || board.value[row][col]==4){
        board.value[row][col-1]+=1
        board.value[row][col+1]+=1
        board.value[row-1][col]+=1
        board.value[row+1][col]+=1
        board.value[row][col]=0}
    else if (board.value[row][col]==1 || board.value[row][col]==2){
        board.value[row][col]+=1
        board.value[row][col]=0
    }
}*/


@Composable
fun p1bar(){
    Row(modifier = Modifier.padding(top=40.dp, start = 15.dp)) {
        Box(modifier = Modifier
            .size(height = 40.dp, width = 80.dp)
            .clip(RoundedCornerShape(size = 20.dp))
            .background(Color(0xFF2fb6f0))
            .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center)
        {
            Text(text = p1points.toString(), color = Color.White, fontSize = 25.sp, modifier = Modifier.align(Alignment.Center) )
        }

        Spacer(modifier = Modifier.width(20.dp))

        Box(modifier = Modifier
            .size(height = 40.dp, width = 220.dp)
            .clip(RoundedCornerShape(size = 20.dp))
            .background(Color(0xFF2fb6f0))
            .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center)
        {
            Text(text = "Player 1", color = Color.White, fontSize = 25.sp, modifier = Modifier.align(Alignment.Center))
        }

    }
}

@Composable
fun p2bar(){
    Row(
        modifier = Modifier.padding(bottom = 40.dp, end = 15.dp)
    ) {
        Box(
            modifier = Modifier
                .size(height = 40.dp, width = 220.dp)
                .clip(RoundedCornerShape(size = 20.dp))
                .background(Color(0xFFff5f57))
                .border(
                    width = 2.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(20.dp)
                ),
            contentAlignment = Alignment.Center
        )
        {
            Text(
                text = "Player 2",
                color = Color.White,
                fontSize = 25.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.width(20.dp))

        Box(
            modifier = Modifier
                .size(height = 40.dp, width = 80.dp)
                .clip(RoundedCornerShape(size = 20.dp))
                .background(Color(0xFFff5f57))
                .border(
                    width = 2.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(20.dp)
                ),
            contentAlignment = Alignment.Center
        )
        {
            Text(
                text = p2points.toString(),
                color = Color.White,
                fontSize = 25.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}



/*fun expansion(row: Int, col:Int, board: MutableState<Array<Array<Int>>>, currentplayer:String) {
    TODO("Not yet implemented")

    if (board.value[row][col]==0){
        board.value[row][col]=3
        currentplayer = if (currentplayer=="Player1") "Player2" else "Player1" }
    else if (board.value[0][0]==3 ){
        board.value[0][1]+=1
        board.value[1][0]+=1
        board.value[row][col]=0
        currentplayer = if (currentplayer=="Player1") "Player2" else "Player1"}
    else if (board.value[0][4]==3 ){
        board.value[1][4]+=1
        board.value[0][3]+=1
        board.value[row][col]=0
        currentplayer = if (currentplayer=="Player1") "Player2" else "Player1"}
    else if (board.value[4][0]==3 ){
        board.value[3][0]+=1
        board.value[4][1]+=1
        board.value[row][col]=0
        currentplayer = if (currentplayer=="Player1") "Player2" else "Player1"}
    else if (board.value[row][0]==3 || board.value[row][4]==3 && row!=0 && row!=4) {
        board.value[row+1][col]+=1
        board.value[row-1][col]+=1
        board.value[row][col]=0
        currentplayer = if (currentplayer=="Player1") "Player2" else "Player1"}
    else if (board.value[0][col]==3 || board.value[4][col]==3 && col!=0 && col!=4) {
        board.value[row][col-1]+=1
        board.value[row][col+1]+=1
        board.value[row-1][col]+=1
        board.value[row][col]=0
        currentplayer = if (currentplayer=="Player1") "Player2" else "Player1"}
    else if (board.value[row][col]==3 || board.value[row][col]==4){
        board.value[row][col-1]+=1
        board.value[row][col+1]+=1
        board.value[row-1][col]+=1
        board.value[row+1][col]+=1
        board.value[row][col]=0
        currentplayer = if (currentplayer=="Player1") "Player2" else "Player1"}
    else if (board.value[row][col]==1 || board.value[row][col]==2){
        board.value[row][col]+=1
        board.value[row][col]=0
        currentplayer = if (currentplayer=="Player1") "Player2" else "Player1"} }*/


@Composable
fun colorcircle(boxColor : Color){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(30.dp)
            .clip(CircleShape)
            .background(boxColor)
    ) {
    }
}


@Composable
fun normalbuttons(){
    Button(modifier = Modifier
        .padding(4.dp)
        .size(48.dp),
        shape= RoundedCornerShape(8.dp),
        onClick = {},
        colors = ButtonDefaults.buttonColors(Color(0xFFFFE5B4))
    ){}
}

@Composable
fun condition(row : Int, col : Int, grids : IntArray){
    //grids[row][col-1]=1

}


