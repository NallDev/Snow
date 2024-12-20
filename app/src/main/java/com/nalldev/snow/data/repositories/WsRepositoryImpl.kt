package com.nalldev.snow.data.repositories

import com.nalldev.snow.data.network.NetworkDataSource
import com.nalldev.snow.domain.repositories.WsRepository

class WsRepositoryImpl(
    private val networkDataSource : NetworkDataSource
) : WsRepository {
    override fun connect() = networkDataSource.connect()

    override fun close() {
        networkDataSource.close()
    }
}