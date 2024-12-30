package com.iagoaf.guessinggame.src.home.presentation.viewmodel

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagoaf.guessinggame.src.home.domain.model.WordModel
import com.iagoaf.guessinggame.src.home.domain.repository.IWordsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: IWordsRepository
) : ViewModel() {

    var words: List<WordModel> = emptyList()
        private set
    var selectedWord = mutableStateOf<WordModel?>(null)
        private set
    val indexWordGuess = mutableIntStateOf(0)

    val lettersTried = mutableStateListOf<Map<String, Any>>()


    val wrongAttempts = mutableIntStateOf(0)
    val maximumAttempts = mutableIntStateOf(0)

    fun getAll() {
        viewModelScope.launch {
            words = repository.getAll()
            selectRandomWord()
        }
    }

    fun resetWordGame() {
        selectRandomWord()
        indexWordGuess.value = 0
        lettersTried.clear()
        wrongAttempts.value = 0
    }

    fun letterIsInWord(letter: String): Boolean {
        return lettersTried.any { it["letter"] == letter }
    }

    fun tryGuess(
        letter: String
    ) {
        selectedWord.value?.name?.let { name ->
            if (name[indexWordGuess.intValue].toString().equals(letter, ignoreCase = true)) {
                indexWordGuess.intValue++
                lettersTried.add(mapOf("letter" to letter, "isCorrect" to true))
            } else {
                //TODO EMITIR LISTENER COM SNACK
                lettersTried.add(mapOf("letter" to letter, "isCorrect" to false))
                wrongAttempts.value++
            }
        }
    }

    private fun selectRandomWord() {
        selectedWord.value = if (words.isNotEmpty()) {
            words.random()
        } else {
            null
        }
        selectedWord.value?.name?.length?.let { maximumAttempts.intValue = (it * 2) }
    }

}