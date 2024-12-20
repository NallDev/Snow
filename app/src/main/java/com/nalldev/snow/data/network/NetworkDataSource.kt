package com.nalldev.snow.data.network

import com.nalldev.snow.data.model.WsResponse
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.receiveDeserialized
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.http.HttpMethod
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

class NetworkDataSource(
    private val client: HttpClient,
    private val ioDispatcher: CoroutineDispatcher
) {
    var data = 0
    fun connect() = flow {
//        client.webSocket(
//            method = HttpMethod.Get,
//            host = "",
//            port = 0,
//            path = "/"
//        ) {
//            while (true) {
//                val receiver = receiveDeserialized<WsResponse>()
//                println("SEND DATA : ${receiver.jumlahData}")
//                emit(receiver.jumlahData)
//            }
//        }
        while (true) {
            delay(10.seconds.inWholeMilliseconds)
            emit(++data)
            println("SEND DATA : $data")
        }
    }.flowOn(ioDispatcher)

    fun close() {
        client.close()
    }
}