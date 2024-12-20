package com.nalldev.snow.di

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import kotlinx.serialization.json.Json
import org.koin.dsl.module

fun provideWebsocketClient() : HttpClient {
    return HttpClient(CIO) {
        install(WebSockets) {
            pingIntervalMillis = 20_000
            contentConverter = KotlinxWebsocketSerializationConverter(Json)
        }
        install(Logging) {
            logger = CustomHttpLogger()
            level = LogLevel.BODY
        }
    }
}

class CustomHttpLogger(): Logger {
    override fun log(message: String) {
        Log.e("loggerTag", message)
    }
}

val networkModule = module {
    single { provideWebsocketClient() }
}