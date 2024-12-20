package com.nalldev.snow.domain.usecase

import com.nalldev.snow.domain.repositories.WsRepository

class WsClose(private val repository: WsRepository) {
    operator fun invoke() {
        repository.close()
    }
}