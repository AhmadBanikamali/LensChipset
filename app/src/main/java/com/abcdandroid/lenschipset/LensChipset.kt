package com.abcdandroid.lenschipset

import androidx.compose.animation.core.tween
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior

//https://medium.com/tech-takeaways/how-to-snap-scrollable-items-to-the-center-of-the-screen-in-jetpack-compose-with-snapper-82a4cc70aebe
@ExperimentalSnapperApi
@ExperimentalPagerApi
@Composable
fun LensChipset(
    chipList: List<String> = listOf(
        "jklmnop",
        "ar",
        "bcd",
        "efghi",
        "ab",
        "bcd",
        "efghi",
        "bcd",
        "efghi",
        "ab",
        "bcd",
        "efghi",
        "bcd",
        "efghi",
        "ab",
        "bcd",
        "efghi"
    )
) {
    Column(modifier = Modifier.height(500.dp)) {

        val state = rememberLazyListState()
        LazyRow(
            Modifier,
            flingBehavior = rememberSnapperFlingBehavior(
                lazyListState = state,
                snapOffsetForItem = { _, _ -> 100 },
                endContentPadding = 20.dp,
                decayAnimationSpec = rememberSplineBasedDecay(),
                springAnimationSpec = tween(durationMillis = 100),
                maximumFlingDistance = { a -> 50f }
            ),
            state = state
        ) {

            items(items = chipList) {
                BoxWithConstraints {
                    ChipItem(it = it)
                }

            }
        }
        ChipItem(it = "efghi", modifier = Modifier)
    }
}

@Composable
private fun ChipItem(modifier: Modifier = Modifier, it: String) {
    val fontSize = 20.sp
    Text(
        text = it,
        fontSize = fontSize,
        color = Color.White,
        modifier = modifier
            .padding(30.dp)
            .background(
                shape = RoundedCornerShape(size = 20.dp),
                color = Color.Black
            )
            .padding(vertical = 10.dp, horizontal = 30.dp)
    )
}
