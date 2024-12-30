package com.iagoaf.guessinggame.src.home.presentation.screen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iagoaf.guessinggame.R
import com.iagoaf.guessinggame.core.ui.theme.AppColors
import com.iagoaf.guessinggame.core.ui.theme.notoSansFont
import com.iagoaf.guessinggame.src.home.presentation.components.LetterBoxBig
import com.iagoaf.guessinggame.src.home.presentation.components.LetterBoxBigEnum
import com.iagoaf.guessinggame.src.home.presentation.components.LetterUsedBox
import com.iagoaf.guessinggame.src.home.presentation.components.LetterUsedBoxState
import com.iagoaf.guessinggame.src.home.presentation.state.HomeState
import com.iagoaf.guessinggame.src.home.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    val viewModel: HomeViewModel = hiltViewModel()

    val guessState = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.getAll()
    }

    var snackBarState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarState,
            )
        },
        content = { paddingValues ->

            // Content
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.fillMaxSize()
                    .padding(vertical = 16.dp),
            ) {
                Image(
                    painter = painterResource(
                        R.drawable.guessing_game_logo,
                    ),
                    contentDescription = "Guessing Game Logo",

                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)

                )
                Log.i("HomeScreen", "State: ${viewModel.state.value}")
                when (viewModel.state.value) {
                    is HomeState.Loading -> {
                        CircularProgressIndicator(
                            color = AppColors.purpleL,
                            modifier = Modifier
                                .size(48.dp)
                        )
                    }

                    is HomeState.Error -> {

                    }

                    is HomeState.Idle -> {

                    }

                    is HomeState.Success -> {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                                .padding(horizontal = 24.dp)
                        ) {
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
                                                fontSize = 20.sp,
                                                color = AppColors.orange,
                                                fontWeight = FontWeight.Bold
                                            )
                                        ) {
                                            append(viewModel.wrongAttempts.intValue.toString())
                                        }
                                        withStyle(
                                            style = SpanStyle(
                                                fontSize = 18.sp,
                                                color = AppColors.gray500,
                                                fontWeight = FontWeight.Medium
                                            )
                                        ) {
                                            append(" de ")
                                        }
                                        withStyle(
                                            style = SpanStyle(
                                                fontSize = 20.sp,
                                                color = AppColors.gray500,
                                                fontWeight = FontWeight.Medium
                                            )
                                        ) {
                                            append(viewModel.maximumAttempts.intValue.toString())
                                        }
                                        withStyle(
                                            style = SpanStyle(
                                                fontSize = 18.sp,
                                                color = AppColors.gray500,
                                                fontWeight = FontWeight.Medium
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
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clickable {
                                            viewModel.resetWordGame()
                                            guessState.value = ""
                                        }
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Box(
                                modifier = Modifier
                                    .clip(shape = RoundedCornerShape(10.dp))
                                    .background(AppColors.gray200)
                                    .fillMaxWidth()
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
                                        viewModel.selectedWord?.let {
                                            Text(
                                                it.value?.hint ?: "NAO",
                                                fontFamily = notoSansFont,
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Medium,
                                                color = AppColors.purpleM
                                            )
                                        }
                                    }
                                }
                            }
                            Spacer(Modifier.height(16.dp))
                            LazyRow {
                                items(viewModel.selectedWord.value?.name?.length ?: 0) { index ->
                                    val letter =
                                        viewModel.selectedWord.value?.name?.get(index).toString()
                                    LetterBoxBig(
                                        letter,
                                        if (index < viewModel.indexWordGuess.intValue) LetterBoxBigEnum.CORRECT else LetterBoxBigEnum.IDLE
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                }
                            }
                            Spacer(Modifier.height(16.dp))
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
                                BasicTextField(
                                    value = guessState.value,
                                    onValueChange = { item ->
                                        if (guessState.value.isEmpty()) {
                                            guessState.value = item
                                        } else if (item == "") {
                                            guessState.value = ""
                                        }
                                    },
                                    textStyle = TextStyle(
                                        fontFamily = notoSansFont,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = AppColors.black,
                                        textAlign = TextAlign.Center,
                                    ),
                                    modifier = Modifier
                                        .size(46.dp)
                                        .clip(RoundedCornerShape(7.dp))
                                        .border(
                                            width = 2.dp,
                                            color = AppColors.purpleM,
                                            shape = RoundedCornerShape(7.dp)
                                        )
                                        .background(AppColors.gray200)
                                        .padding(0.dp),
                                    decorationBox = { innerTextField ->
                                        Box(
                                            contentAlignment = Alignment.Center,
                                            modifier = Modifier.fillMaxSize(),
                                        ) {
                                            innerTextField()
                                        }
                                    }
                                )
                                Spacer(Modifier.width(16.dp))
                                Button(
                                    onClick = {
                                        if (guessState.value.isEmpty()) {
                                            scope.launch {
                                                snackBarState.showSnackbar("Digite uma letra")
                                            }
                                        } else {
                                            viewModel.tryGuess(guessState.value)
                                            guessState.value = ""
                                        }
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
                            Spacer(Modifier.height(8.dp))
                            LazyRow(
                            ) {
                                items(viewModel.lettersTried.size) { index ->
                                    val letterTry =
                                        viewModel.lettersTried[viewModel.lettersTried.size - 1 - index]
                                    LetterUsedBox(
                                        if (letterTry["isCorrect"] as Boolean) LetterUsedBoxState.SUCCESS else LetterUsedBoxState.FAILED,
                                        letterTry["letter"] as String,
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                }
                            }
                        }
                    }
                }


            }
        }
    )
}

@Preview
@Composable
private fun PreviewHomeScreen() {
    HomeScreen()
}