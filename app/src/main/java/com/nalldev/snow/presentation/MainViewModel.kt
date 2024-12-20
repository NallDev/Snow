package com.nalldev.snow.presentation

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nalldev.snow.domain.usecase.WsUseCase
import com.nalldev.snow.utils.Notification
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val application: Application,
    private val wsUseCase: WsUseCase
) : ViewModel() {
    private val _isPermissionGranted = MutableStateFlow(false)

    var isWsConnected by mutableStateOf(false)
        private set

    fun doWsConnect() = viewModelScope.launch {
        isWsConnected = true
        wsUseCase.wsConnect().combine(_isPermissionGranted) { message, isGranted ->
            Pair(message, isGranted)
        }.takeWhile { isWsConnected }.collect {
            if (it.second) {
                Notification.sendNotification(application, "TITLE : ${it.first}", "INI MESSAGE")
            }
            print("DATA : ${it.first} : ${it.second}")
        }
    }

    fun doWsClose() = viewModelScope.launch {
        isWsConnected = false
    }

    fun updatePermissionStatus(isGranted: Boolean) {
        _isPermissionGranted.update { isGranted }
    }
}