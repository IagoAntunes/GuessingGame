package com.iagoaf.guessinggame.core.inject

import com.iagoaf.guessinggame.src.home.domain.repository.IWordsRepository
import com.iagoaf.guessinggame.src.home.external.datasource.WordsDataSourceImpl
import com.iagoaf.guessinggame.src.home.infra.datasource.IWordsDataSource
import com.iagoaf.guessinggame.src.home.infra.repository.WordsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideWordsRepository(
        repository: WordsRepositoryImpl
    ): IWordsRepository

    @Binds
    abstract fun provideWordsDataSource(
        dataSource: WordsDataSourceImpl
    ): IWordsDataSource
}