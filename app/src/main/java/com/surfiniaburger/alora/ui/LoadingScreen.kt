package com.surfiniaburger.alora.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "shimmer")
    val shimmerTranslate by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Map placeholder with shimmer
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .aspectRatio(16f / 9f)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color.Gray.copy(alpha = 0.3f),
                                Color.Gray.copy(alpha = 0.5f),
                                Color.Gray.copy(alpha = 0.3f)
                            ),
                            start = Offset(shimmerTranslate - 1000f, 0f),
                            end = Offset(shimmerTranslate, 0f)
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Loading text
            Text(
                text = "Loading Map...",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(16.dp))

            // HUD placeholder with shimmer
            Box(
                modifier = Modifier
                    .width(200.dp)
                    .height(120.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color.Gray.copy(alpha = 0.2f),
                                Color.Gray.copy(alpha = 0.4f),
                                Color.Gray.copy(alpha = 0.2f)
                            ),
                            start = Offset(shimmerTranslate - 1000f, 0f),
                            end = Offset(shimmerTranslate, 0f)
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
            )
        }
    }
}
