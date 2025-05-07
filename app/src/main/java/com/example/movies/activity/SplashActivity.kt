package com.example.movies.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movies.R


class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
            IntroScreen(onGetInClick={
                    startActivity(Intent(this,MainActivity::class.java))
            })
        }
    }
}

@Composable
@Preview
fun IntroScreenPreview(){
    IntroScreen(onGetInClick = {})
}


@Composable
fun IntroScreen(onGetInClick: ()->Unit){
    Box(
        modifier= Modifier
            .fillMaxSize()
            .background(color= colorResource(id=R.color.blackBackground))
    ){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        HeaderSection()
        FooterSection(onGetInClick)
      }
     }
}

@Composable
fun FooterSection(onGetInClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg2),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        Button(
            modifier = Modifier.size(200.dp, 50.dp).align(Alignment.Center),
            onClick = onGetInClick,
            shape = RoundedCornerShape(50.dp),
            border = BorderStroke(
                width = 4.dp,
                brush = Brush.linearGradient(
                    colors = listOf(colorResource(R.color.pink), colorResource(R.color.green))
                )
            ),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
                contentColor = Color.White
            )
        ) {
            Text(text="Get in", fontSize = 18.sp)
        }
    }
}

@Composable
fun HeaderSection(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(650.dp)
    ){
        Image(
            painter= painterResource(id= R.drawable.bg1),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        Column (
            horizontalAlignment=Alignment.CenterHorizontally,
            verticalArrangement =  Arrangement.Center,
            modifier = Modifier.matchParentSize()
        ){
            Image(
                painter= painterResource(id= R.drawable.woman),
                contentDescription = null,
                modifier=Modifier.size(360.dp)
            )
            Spacer(modifier=Modifier.height(32.dp))
            Text(text="Watch Videos in \nVirtual Reality",
            style=TextStyle(
                color= Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            )
            Spacer(modifier = Modifier.height(25.dp))
            Text(text="Download and Watch offline\n Wherever you are",
                style=TextStyle(
                    color=Color.White,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                ))
        }
    }
}