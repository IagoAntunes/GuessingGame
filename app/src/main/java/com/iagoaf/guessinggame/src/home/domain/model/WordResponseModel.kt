package com.iagoaf.guessinggame.src.home.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class WordsResponse(
    val palavras: List<WordModel>
)