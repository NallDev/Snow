package com.nalldev.snow.domain.repositories

import kotlinx.coroutines.flow.Flow

interface WsRepository {
    fun connect() : Flow<Int>
    fun close()
}