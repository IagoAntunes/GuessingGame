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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iagoaf.guessinggame.ui.theme.AppColors
import com.iagoaf.guessinggame.ui.theme.notoSansFont

@Composable
fun LetterBoxBig() {
    Box(
        modifier = Modifier
            .size(46.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(AppColors.greenLight),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "R",
            fontFamily = notoSansFont,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = AppColors.green
        )
    }
}

@Preview
@Composable
private fun PreviewLetterBox() {
    LetterBoxBig()
}