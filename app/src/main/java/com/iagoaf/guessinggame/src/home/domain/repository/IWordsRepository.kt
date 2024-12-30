package com.iagoaf.guessinggame.src.home.domain.repository

import com.iagoaf.guessinggame.src.home.domain.model.WordModel

interface IWordsRepository {
    suspend fun getAll() : List<WordModel>
}