package com.example.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.movies.domain.FilmItemModel

@Composable
fun FilmItem(item:FilmItemModel,onItemModel: (FilmItemModel)->Unit){
    Column (
        modifier = Modifier
            .padding(4.dp)
            .width(120.dp)
            .clickable { onItemModel(item) }
            .background(color= colorResource(id=R.color.black2))
    ){
        AsyncImage(
            model = item.Poster,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .size(width=120.dp, height = 180.dp)
                .background(Color.Gray)
        )

        Spacer(modifier=Modifier.height(8.dp))

        Text(
            text=item.Title,
            modifier = Modifier.padding(start = 4.dp),
            style = TextStyle(color = Color.White, fontSize = 11.sp),
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(4.dp))

        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
        ){
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint= colorResource(id=R.color.yellow)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text=item.Imdb.toString(),style= TextStyle(color = Color.White, fontSize = 12.sp))
        }
    }
}