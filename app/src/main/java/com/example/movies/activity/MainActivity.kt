package com.example.movies.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movies.BottomNavigationBar
import com.example.movies.FilmItem
import com.example.movies.R
import com.example.movies.SearchBar
import com.example.movies.domain.FilmItemModel
import com.example.movies.viewModel.MainViewModel
import com.google.firebase.FirebaseApp

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)

        setContent {
          MainScreen(onItemClick = {item->
                val intent = Intent(this,DetailMovieActivity::class.java)
//              点击这个Item传送这个Item
                intent.putExtra("object",item)
                startActivity(intent)
          })
        }
    }
}

@Preview
@Composable
fun MainScreen(onItemClick:(FilmItemModel)->Unit={}){
    Scaffold  (bottomBar = {BottomNavigationBar()},
        floatingActionButton = {
            Box(
                modifier=Modifier
                    .size(60.dp)
                    .background(
                        brush= Brush.linearGradient(
                            colors = listOf(
                                colorResource(R.color.pink),
                                colorResource(R.color.green)
                            )
                        ),
                        shape = CircleShape
                    )
                    .padding(3.dp)

            ){
                FloatingActionButton(
                    onClick = {},
                    backgroundColor = colorResource(id=R.color.black3),
                    modifier =Modifier.size(58.dp),
                    contentColor = Color.White,
                    content = {
                    Icon(
                        painter = painterResource(R.drawable.float_icon),
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                    }
                )
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        backgroundColor = colorResource(R.color.blackBackground)
        ){
        //Scaffold提供的自动paddingValues,避免被系统组件遮挡
       paddingValues ->
        Box(
            modifier=Modifier
                .padding(paddingValues)
                .background(color = colorResource(R.color.blackBackground))
        )
        {
            Image(
                painter= painterResource(id=R.drawable.bg1),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )
            MainContent(onItemClick)
        }

    }
}


@Composable
fun MainContent(onItemClick: (FilmItemModel) -> Unit){

    val viewModel = MainViewModel()
//    使用 remember 创建可响应状态变化的电影列表。
//
//    mutableStateListOf 是 Compose 提供的“响应式”列表：一旦数据改变，UI 会自动刷新。
    val upcoming = remember { mutableStateListOf<FilmItemModel>() }
    val newMovies = remember { mutableStateListOf<FilmItemModel>() }


    var showUpcomingLoad by remember { mutableStateOf(true)}
    var showNewMoviesLoad by remember { mutableStateOf(true)}
//初始化
    LaunchedEffect(Unit) {

//        observeForever：观察 ViewModel 中 LiveData，拿到数据后更新 upcoming 列表。
        viewModel.loadUpcoming().observeForever{
            Log.d("FirebaseTest", "加载到了 newMovies，数量：${it.size}")
            upcoming.clear()
            upcoming.addAll(it)
            showUpcomingLoad=false
        }
    }

    LaunchedEffect(Unit) {
         viewModel.loadItems().observeForever{
             newMovies.clear()
             newMovies.addAll(it)
             showNewMoviesLoad=false
        }
    }



    Column (
        modifier =Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(top=60.dp, bottom = 100.dp)
    ){
        Text(
            text="What would you like to watch",
            style = TextStyle(color =Color.White, fontSize = 25.sp),
            modifier =Modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
        )
        SearchBar(hint="Search Movies...")

        SectionTitle("New Movies")

        if (showNewMoviesLoad){
            Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                contentAlignment = Alignment.Center)
            {
                CircularProgressIndicator()
            }
        } else {
         LazyRow(
             horizontalArrangement = Arrangement.spacedBy(8.dp),
             contentPadding = PaddingValues(horizontal = 16.dp)
         ){
            items(newMovies){ item ->
                    FilmItem(item, onItemClick)
            }
         }
        }

        SectionTitle("Upcoming Movies")

        if (showUpcomingLoad){
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
                contentAlignment = Alignment.Center)
            {
                CircularProgressIndicator()
            }
        } else {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ){
                items(upcoming){ item ->
                    FilmItem(item, onItemClick)
                }
            }
        }

    }
}

@Composable
fun SectionTitle(title:String){
    Text(
        text =title,
        style = TextStyle(color = colorResource(id=R.color.yellow), fontSize = 18.sp),
        modifier =Modifier.padding(start = 16.dp, top = 32.dp,bottom= 8.dp),
        fontWeight = FontWeight.Bold
    )
}