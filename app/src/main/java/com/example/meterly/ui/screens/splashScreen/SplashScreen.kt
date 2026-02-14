package com.example.meterly.ui.screens.splashScreen

import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import android.widget.VideoView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.viewinterop.AndroidView
import com.example.meterly.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToNextScreen: () -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        delay(3000) // Тривалість показу (3 сек)
        onNavigateToNextScreen()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white)),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            factory = { context ->
                VideoView(context).apply {
                    setVideoURI(Uri.parse("android.resource://" + context.packageName + "/" + R.raw.splash_animation))
                    setOnPreparedListener { mediaPlayer ->
                        mediaPlayer.isLooping = true
                        mediaPlayer.setVolume(0f, 0f)
                        // Розтягнути відео, щоб заповнити весь екран
                        mediaPlayer.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING)
                    }
                    start()
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}