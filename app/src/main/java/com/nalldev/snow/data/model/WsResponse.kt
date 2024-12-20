package com.nalldev.snow.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class WsResponse(
	@SerialName("jumlah_data")
	val jumlahData: Int
)