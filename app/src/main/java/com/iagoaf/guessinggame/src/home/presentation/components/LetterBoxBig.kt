package com.iagoaf.guessinggame.src.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iagoaf.guessinggame.core.ui.theme.AppColors
import com.iagoaf.guessinggame.core.ui.theme.notoSansFont

enum class LetterBoxBigEnum(
    val backgroundColor: Color,
    val textColor:Color,
){
    CORRECT(
        backgroundColor = AppColors.greenLight,
        textColor = AppColors.green
    ),
    IDLE(
        backgroundColor = AppColors.gray200,
        textColor = AppColors.white
    ),
}

@Composable
fun LetterBoxBig(
    letter: String?,
    state: LetterBoxBigEnum,
) {
    Box(
        modifier = Modifier
            .size(46.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(state.backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            if(state == LetterBoxBigEnum.CORRECT) letter?.uppercase() ?: "" else "",
            fontFamily = notoSansFont,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = state.textColor
        )
    }
}

@Preview
@Composable
private fun PreviewLetterBox() {
    LetterBoxBig(
        letter = null,
        state = LetterBoxBigEnum.IDLE
    )
}