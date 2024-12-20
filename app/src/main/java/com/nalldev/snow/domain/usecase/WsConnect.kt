package com.nalldev.snow.domain.usecase

import com.nalldev.snow.domain.repositories.WsRepository
import kotlinx.coroutines.flow.Flow

class WsConnect(
    private val repository: WsRepository
) {
    operator fun invoke() : Flow<Int> = repository.connect()
}