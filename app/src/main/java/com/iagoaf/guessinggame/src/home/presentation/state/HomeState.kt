package com.iagoaf.guessinggame.src.home.presentation.state

import com.iagoaf.guessinggame.src.home.domain.model.WordModel

sealed class HomeState {
    object Loading : HomeState()
    object Error : HomeState()
    object Idle : HomeState()
    data class Success(
        val words: List<WordModel>,
        var gameEnded: Boolean = false
    ) : HomeState()
}