package com.ddaypunk.fetchrewardsexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ddaypunk.fetchrewardsexercise.hiring.presentation.screen.MainScreen
import com.ddaypunk.fetchrewardsexercise.ui.theme.FetchRewardsExerciseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FetchRewardsExerciseTheme {
                MainScreen()
            }
        }
    }
}