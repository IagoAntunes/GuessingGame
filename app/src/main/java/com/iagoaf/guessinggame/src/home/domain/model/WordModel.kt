package com.iagoaf.guessinggame.src.home.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WordModel(
    @SerialName("word")
    val name: String,
    @SerialName("hint")
    val hint: String
)