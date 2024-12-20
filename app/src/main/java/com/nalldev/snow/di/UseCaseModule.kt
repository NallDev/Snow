package com.nalldev.snow.di

import com.nalldev.snow.domain.usecase.WsClose
import com.nalldev.snow.domain.usecase.WsConnect
import com.nalldev.snow.domain.usecase.WsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { WsConnect(get()) }
    single { WsClose(get()) }
    single { WsUseCase(get(), get()) }
}