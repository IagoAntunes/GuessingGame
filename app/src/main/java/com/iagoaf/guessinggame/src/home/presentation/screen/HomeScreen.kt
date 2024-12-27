package com.iagoaf.guessinggame.src.home.presentation.screen

import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iagoaf.guessinggame.R
import com.iagoaf.guessinggame.ui.theme.AppColors
import com.iagoaf.guessinggame.ui.theme.notoSansFont

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    val guessState = remember { mutableStateOf("") }

    Scaffold(
        content = { paddingValues ->
            // Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                Image(
                    painter = painterResource(
                        R.drawable.guessing_game_logo,
                    ),
                    contentDescription = "Guessing Game Logo",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = AnnotatedString.Builder().apply {
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 16.sp,
                                    color = AppColors.orange
                                )
                            ) {
                                append("5")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 16.sp,
                                    color = AppColors.gray500
                                )
                            ) {
                                append(" de ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 16.sp,
                                    color = AppColors.gray500
                                )
                            ) {
                                append("10")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 16.sp,
                                    color = AppColors.gray500
                                )
                            ) {
                                append(" tentativas")
                            }
                        }.toAnnotatedString(),
                        fontFamily = notoSansFont
                    )
                    Image(
                        painter = painterResource(R.drawable.ic_refresh),
                        contentDescription = "Refresh",
                        colorFilter = ColorFilter.tint(AppColors.purpleL),
                        modifier = Modifier.size(32.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(AppColors.gray200)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_lighthub),
                            contentDescription = "Light Hub",
                            colorFilter = ColorFilter.tint(AppColors.purpleM),
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Column {
                            Text(
                                "Dica",
                                fontFamily = notoSansFont,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = AppColors.purpleM
                            )
                            Text(
                                "Biblioteca para criar interfaces Web com Javascript.",
                                fontFamily = notoSansFont,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                color = AppColors.purpleM
                            )
                        }
                    }
                }
                Spacer(Modifier.height(32.dp))
                Text(
                    "Palpite",
                    fontFamily = notoSansFont,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = AppColors.black
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    TextField(
                        value = guessState.value,
                        onValueChange = { },
                        modifier = Modifier
                            .size(46.dp)
                            .clip(RoundedCornerShape(7.dp))
                            .border(
                                width = 2.dp,
                                color = AppColors.purpleM,
                                shape = RoundedCornerShape(7.dp)
                            )
                            .background(AppColors.gray200)
                    )
                    Spacer(Modifier.width(16.dp))
                    Button(
                        onClick = {

                        },
                        shape = RoundedCornerShape(7.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = AppColors.purpleL,
                            containerColor = AppColors.purpleL
                        ),
                        border = BorderStroke(
                            width = 2.dp,
                            color = AppColors.purpleM
                        ),
                        modifier = Modifier.height(46.dp)
                    ) {
                        Text(
                            "Confirmar",
                            fontFamily = notoSansFont,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = AppColors.white
                        )
                    }
                }
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                Text(
                    "Letras utilizadas",
                    fontFamily = notoSansFont,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = AppColors.black
                )
            }
        }
    )
}

@Preview
@Composable
private fun PreviewHomeScreen() {
    HomeScreen()
}