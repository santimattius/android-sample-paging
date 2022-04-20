package com.santimattius.paging.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.santimattius.paging.R

private val gradient = listOf(
    Color.Gray.copy(alpha = 0.6f), //darker grey (90% opacity)
    Color.Gray.copy(alpha = 0.3f), //lighter grey (30% opacity)
    Color.Gray.copy(alpha = 0.6f)
)

@Composable
fun LoadingImageEffect() {
    val transition = rememberInfiniteTransition()
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutLinearInEasing
            )
        )
    )
    val brush = linearGradient(
        colors = gradient,
        start = Offset(200f, 200f),
        end = Offset(
            x = translateAnimation.value,
            y = translateAnimation.value
        )
    )
    ShimmerContainer(brush)
}

@Composable
private fun ShimmerContainer(brush: Brush) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .background(brush)
            .aspectRatio(ratio = 0.67f),
    )
}

@Composable
fun LoadingShimmerEffect(modifier: Modifier = Modifier) {
    Card(modifier = modifier.padding(dimensionResource(R.dimen.small))) {
        LoadingImageEffect()
    }
}

@Composable
@Preview(showBackground = true)
fun ShimmerPreview() {
    ShimmerContainer(
        brush = linearGradient(
            listOf(
                Color.LightGray.copy(alpha = 0.9f),
                Color.LightGray.copy(alpha = 0.4f),
                Color.LightGray.copy(alpha = 0.9f)
            )
        )
    )
}
