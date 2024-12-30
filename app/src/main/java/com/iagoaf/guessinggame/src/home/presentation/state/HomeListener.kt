package com.iagoaf.guessinggame.src.home.presentation.state

sealed class HomeListener {
    object Idle : HomeListener()
    object SuccessGuess : HomeListener()
    object GameOver : HomeListener()
}