package com.example.rockstar.common.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ehsanmsz.mszprogressindicator.progressindicator.BallTrianglePathProgressIndicator


@Composable
fun LoadingBarComponent(
    modifier: Modifier = Modifier
) {
    BallTrianglePathProgressIndicator(modifier = modifier)
}



