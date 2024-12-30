package com.iagoaf.guessinggame.src.home.presentation.viewmodel

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagoaf.guessinggame.src.home.domain.model.WordModel
import com.iagoaf.guessinggame.src.home.domain.repository.IWordsRepository
import com.iagoaf.guessinggame.src.home.presentation.state.HomeListener
import com.iagoaf.guessinggame.src.home.presentation.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: IWordsRepository
) : ViewModel() {

    val state = mutableStateOf<HomeState>(HomeState.Idle)
    val listener = mutableStateOf<HomeListener>(HomeListener.Idle)

    var selectedWord = mutableStateOf<WordModel?>(null)
        private set
    val indexWordGuess = mutableIntStateOf(0)

    val lettersTried = mutableStateListOf<Map<String, Any>>()


    val wrongAttempts = mutableIntStateOf(0)
    val maximumAttempts = mutableIntStateOf(0)

    fun getAll() {
        state.value = HomeState.Loading
        viewModelScope.launch {
            delay(5000)
            val words = repository.getAll()
            selectRandomWord(words)
            state.value = HomeState.Success(words)
        }
    }

    fun resetWordGame() {
        selectRandomWord((state.value as HomeState.Success).words)
        indexWordGuess.intValue = 0
        lettersTried.clear()
        wrongAttempts.intValue = 0
        listener.value = HomeListener.Idle
        (state.value as HomeState.Success).gameEnded = false
    }

    fun tryGuess(
        letter: String
    ) {
        selectedWord.value?.name?.let { name ->
            if (name[indexWordGuess.intValue].toString().equals(letter, ignoreCase = true)) {
                indexWordGuess.intValue++
                lettersTried.add(mapOf("letter" to letter, "isCorrect" to true))
            } else {
                lettersTried.add(mapOf("letter" to letter, "isCorrect" to false))
                wrongAttempts.intValue++
                if (wrongAttempts.intValue == maximumAttempts.intValue) {
                    listener.value = HomeListener.GameOver
                    (state.value as HomeState.Success).gameEnded = true
                }
            }
            if (indexWordGuess.intValue == name.length) {
                (state.value as HomeState.Success).gameEnded = true
                listener.value = HomeListener.SuccessGuess
            }
        }
    }

    private fun selectRandomWord(words: List<WordModel>) {
        selectedWord.value = if (words.isNotEmpty()) {
            words.random()
        } else {
            null
        }
        selectedWord.value?.name?.length?.let { maximumAttempts.intValue = (it * 2) }
    }

}