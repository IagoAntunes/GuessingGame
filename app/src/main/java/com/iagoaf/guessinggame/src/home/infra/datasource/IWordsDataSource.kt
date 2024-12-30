package com.iagoaf.guessinggame.src.home.infra.datasource

import com.iagoaf.guessinggame.src.home.domain.model.WordModel

interface IWordsDataSource {
    suspend fun getAll(): List<WordModel>
}