package com.iagoaf.guessinggame.src.home.presentation.viewmodel

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagoaf.guessinggame.src.home.domain.model.WordModel
import com.iagoaf.guessinggame.src.home.domain.repository.IWordsRepository
import com.iagoaf.guessinggame.src.home.presentation.screen.HomeScreen
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
        state as HomeState.Success
        selectRandomWord(state.words)
        indexWordGuess.intValue = 0
        lettersTried.clear()
        wrongAttempts.intValue = 0
    }

    fun tryGuess(
        letter: String
    ) {
        state as HomeState.Success
        selectedWord.value?.name?.let { name ->
            if (name[indexWordGuess.intValue].toString().equals(letter, ignoreCase = true)) {
                indexWordGuess.intValue++
                lettersTried.add(mapOf("letter" to letter, "isCorrect" to true))
            } else {
                //TODO EMITIR LISTENER COM SNACK
                lettersTried.add(mapOf("letter" to letter, "isCorrect" to false))
                wrongAttempts.intValue++
            }
            if (indexWordGuess.intValue == name.length) {
                state.gameEnded = true
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