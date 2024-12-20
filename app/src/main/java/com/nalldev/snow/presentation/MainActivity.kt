package com.nalldev.snow.presentation

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.nalldev.snow.theme.SnowTheme
import org.koin.compose.viewmodel.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)

        setContent {
            WearApp()
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WearApp() {
    val viewModel = koinViewModel<MainViewModel>()
    val notificationPermissionState = rememberPermissionState(
        Manifest.permission.POST_NOTIFICATIONS
    )

    LaunchedEffect(Unit) {
        viewModel.doWsConnect()
    }

    LaunchedEffect(notificationPermissionState.status.isGranted) {
        viewModel.updatePermissionStatus(notificationPermissionState.status.isGranted)
    }

    SnowTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            contentAlignment = Alignment.Center
        ) {
            if (notificationPermissionState.status.isGranted) {
                if (viewModel.isWsConnected) {
                    Button(modifier = Modifier.fillMaxWidth(), onClick = {
                        viewModel.doWsClose()
                    }) {
                        Text("Disconnect")
                    }
                } else {
                    Button(modifier = Modifier.fillMaxWidth(), onClick = {
                        viewModel.doWsConnect()
                    }) {
                        Text("Connect")
                    }
                }
            } else {
                Button(modifier = Modifier.fillMaxWidth(), onClick = {
                    notificationPermissionState.launchPermissionRequest()
                }) {
                    Text("Izinkan Notifikasi")
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp()
}