package com.iagoaf.guessinggame.src.home.infra.repository

import com.iagoaf.guessinggame.src.home.domain.model.WordModel
import com.iagoaf.guessinggame.src.home.domain.repository.IWordsRepository
import com.iagoaf.guessinggame.src.home.infra.datasource.IWordsDataSource
import javax.inject.Inject

class WordsRepositoryImpl @Inject constructor(
    private val dataSource: IWordsDataSource
): IWordsRepository {
    override suspend fun getAll(): List<WordModel> {
        return dataSource.getAll()
    }
}