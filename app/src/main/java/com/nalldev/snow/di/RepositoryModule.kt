package com.nalldev.snow.di

import com.nalldev.snow.data.network.NetworkDataSource
import com.nalldev.snow.data.repositories.WsRepositoryImpl
import com.nalldev.snow.domain.repositories.WsRepository
import org.koin.dsl.module

fun provideWsRepository(networkDataSource: NetworkDataSource) : WsRepository {
    return WsRepositoryImpl(networkDataSource)
}

val repositoryModule = module {
    single { provideWsRepository(get()) }
}