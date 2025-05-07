package com.example.movies.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.movies.VideoPlayer



class TrailerActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val trailerUrl = intent.getStringExtra("trailer_url")

        setContent {
            MaterialTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    if (trailerUrl.isNullOrEmpty()) {
                        Text(
                            text = "No Trailer Available",
                            color = Color.White
                        )
                    } else {
                        VideoPlayer(trailerUrl,{finish()})
                    }
                }
            }
        }
    }
}



