package com.iagoaf.guessinggame.src.home.external.datasource

import com.iagoaf.guessinggame.src.home.domain.model.WordModel
import com.iagoaf.guessinggame.src.home.domain.model.WordsResponse
import com.iagoaf.guessinggame.src.home.infra.datasource.IWordsDataSource
import kotlinx.serialization.json.Json
import java.io.InputStream
import javax.inject.Inject

class WordsDataSourceImpl @Inject constructor(): IWordsDataSource{
    override suspend fun getAll(): List<WordModel> {
        val jsonString = readJsonFile("assets/mockdata/words.json")
        return parseWords(jsonString)
    }
    private fun readJsonFile(fileName: String): String {
        val inputStream: InputStream = this::class.java.classLoader.getResourceAsStream(fileName)
            ?: throw IllegalArgumentException("File not found: $fileName")
        return inputStream.bufferedReader().use { it.readText() }
    }
    fun parseWords(jsonString: String): List<WordModel> {
        val wordsResponse = Json.decodeFromString<WordsResponse>(jsonString)
        return wordsResponse.palavras
    }
}