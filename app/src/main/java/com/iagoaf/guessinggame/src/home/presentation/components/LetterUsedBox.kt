package com.iagoaf.guessinggame.src.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iagoaf.guessinggame.core.ui.theme.AppColors
import com.iagoaf.guessinggame.core.ui.theme.notoSansFont

enum class LetterUsedBoxState {
    SUCCESS,
    FAILED,
}

@Composable
fun LetterUsedBox(
    state: LetterUsedBoxState,
    letter: String,
) {
    Box(
        modifier = Modifier
            .size(44.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(
                width = 1.dp,
                color = when (state) {
                    LetterUsedBoxState.SUCCESS -> AppColors.green
                    LetterUsedBoxState.FAILED -> AppColors.black
                },
                shape = RoundedCornerShape(10.dp)
            )
            .background(
                color = when (state) {
                    LetterUsedBoxState.SUCCESS -> AppColors.greenLight
                    LetterUsedBoxState.FAILED -> AppColors.orange
                }
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            letter.uppercase(),
            fontFamily = notoSansFont,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = when (state) {
                LetterUsedBoxState.SUCCESS -> AppColors.green
                LetterUsedBoxState.FAILED -> AppColors.black
            },
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun PreviewLetterBox() {
    LetterUsedBox(LetterUsedBoxState.FAILED, letter = "L")
}