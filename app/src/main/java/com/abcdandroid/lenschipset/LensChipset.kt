package com.abcdandroid.lenschipset

import androidx.compose.animation.core.tween
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.SnapperLayoutInfo
import dev.chrisbanes.snapper.SnapperLayoutItemInfo
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior

//https://medium.com/tech-takeaways/how-to-snap-scrollable-items-to-the-center-of-the-screen-in-jetpack-compose-with-snapper-82a4cc70aebe
@ExperimentalSnapperApi
@ExperimentalPagerApi
@Composable
fun LensChipset(
    chipList: List<String> = listOf(
        "1",
        "2",
        "3",
        "4",
        "5",
        "6",
        "ar",
        "bcd",
        "efghi",
        "efghiefghief",
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
        "efghi" /**/
    )
) {
    Box(modifier = Modifier.height(500.dp)) {

        val state = rememberLazyListState()
        var paddingValue by remember {
            mutableStateOf(0.dp)
        }

        LazyRow(
            Modifier,
            flingBehavior = rememberSnapperFlingBehavior(
                lazyListState = state,
                snapOffsetForItem = { snapperLayoutInfo:SnapperLayoutInfo, snapLayoutItemInfo:SnapperLayoutItemInfo ->
                    println(snapLayoutItemInfo.toString())
                    println(snapperLayoutInfo.toString())
                    println("===")
                    paddingValue.value.toInt()
                },
                endContentPadding = (0).dp,
                decayAnimationSpec = rememberSplineBasedDecay(),
                springAnimationSpec = tween(durationMillis = 100),
            ),
            state = state
        ) {

            itemsIndexed(items = chipList) {index,it->
                BoxWithConstraints {
                    ChipItem(it = it, isFirst = index==0, paddingValue = 80.dp, boxWithConstraintsScope = {
                        paddingValue=it.minWidth
                    })
                }

            }
        }

        Canvas(modifier = Modifier.fillMaxWidth(), onDraw = {
            drawLine(Color.Yellow, start = Offset(0f,120f), end = Offset(paddingValue.value,120f), strokeWidth = 50f)
            drawLine(Color.Yellow, start = Offset(0f,190f), end = Offset(200.dp.toPx(),190f), strokeWidth = 50f)
        })


    }
}

@Composable
private fun ChipItem(modifier: Modifier = Modifier, it: String,isFirst: Boolean =false,boxWithConstraintsScope: (BoxWithConstraintsScope)->Unit,paddingValue : Dp) {
    val fontSize = 20.sp
    BoxWithConstraints {
        boxWithConstraintsScope(this)
        Text(
            text = it,
            fontSize = fontSize,
            color = Color.White,
            modifier = modifier
                .padding(start = if (isFirst) paddingValue else 20.dp)
                .background(
                    shape = RoundedCornerShape(size = 20.dp),
                    color = Color.Black
                )
                .padding(vertical = 10.dp, horizontal = 30.dp)
        )
    }
}
