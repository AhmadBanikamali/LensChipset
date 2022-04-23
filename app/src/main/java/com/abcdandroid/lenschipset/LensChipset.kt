package com.abcdandroid.lenschipset

import androidx.compose.animation.core.tween
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.SnapperLayoutInfo
import dev.chrisbanes.snapper.SnapperLayoutItemInfo
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior

//https://medium.com/tech-takeaways/how-to-snap-scrollable-items-to-the-center-of-the-screen-in-jetpack-compose-with-snapper-82a4cc70aebe
@ExperimentalComposeUiApi
@ExperimentalSnapperApi
@ExperimentalPagerApi
@Composable
fun LensChipset(
    chipList: List<String> = listOf(
        "1",
        "2a",
        "3aa",
        "4aaaa",
        "5aaaaa",
        "6aaaaaa",
        "7aaaaaa",
        "8aaaaaaa",
        "9aaaaaaaa",
        "10aaaaaaaa",
        "11aaaaaaaaa",
        "12aaaaaaaaaa",
        "13aaaaaaaaaaaaa",
        "14aaaaaaaaaaaaaq",
        "15aaaaaaaaaaaaa",
        "16aaaaaaaaaaaaa",
        "17aaaaaaaaaaaaa",
        "18aaaaaaaaaaaaa",
        "19aaaaaaaaaaaaa",
        "20aaaaaaaaaaaaa",
        "21aaaaaaaaaaaaa",
        "22aaaaaaaaaaaaa",
        "23aaa"
    )
) {
    Box(modifier = Modifier.height(500.dp)) {

        val state = rememberLazyListState()

        val paddingValueInPx = 500f
        val paddingValueInDp = paddingValueInPx.toDp()

        val itemsSize = mutableListOf<Int>()


        LazyRow(
            Modifier.onPlaced {
                println("itemsSize:$itemsSize")
            },
            flingBehavior = rememberSnapperFlingBehavior(
                lazyListState = state,
                snapOffsetForItem = { snapperLayoutInfo: SnapperLayoutInfo, snapLayoutItemInfo: SnapperLayoutItemInfo ->
                    snapperLayoutInfo.visibleItems.forEach {
                        println(it.index)
                    }
                    paddingValueInPx.toInt()
                },
                endContentPadding = (0).dp,
                decayAnimationSpec = rememberSplineBasedDecay(),
                springAnimationSpec = tween(durationMillis = 100),
                maximumFlingDistance = { 5f }
            ),
            state = state,

            ) {
            itemsIndexed(items = chipList) { index, it ->
                BoxWithConstraints {
                    Text(
                        text = it,
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier
                            .onPlaced {
                                try {
                                    itemsSize[index]
                                } catch (e: IndexOutOfBoundsException) {
                                    itemsSize.add(index, it.size.width)
                                }
                            }
                            .padding(start = if (index == 0) paddingValueInDp.dp else 0.dp)
                            .background(
                                shape = RoundedCornerShape(size = 20.dp),
                                color = Color.Black
                            )
                            .padding(vertical = 10.dp, horizontal = 30.dp)
                            .drawWithContent {
                                this.drawContent()
                            }
                    )
                }
            }
        }

        Canvas(modifier = Modifier.fillMaxWidth(), onDraw = {
            drawLine(
                Color.Yellow,
                start = Offset(0f, 120f),
                end = Offset(paddingValueInPx, 120f),
                strokeWidth = 50f
            )
            drawLine(
                Color.Yellow,
                start = Offset(0f, 190f),
                end = Offset(100.dp.toPx(), 190f),
                strokeWidth = 50f
            )
        })
    }
}

@Composable
fun Float.toDp() = this / LocalContext.current.resources.displayMetrics.density

@Composable
fun Float.toPx() = this * LocalContext.current.resources.displayMetrics.density
