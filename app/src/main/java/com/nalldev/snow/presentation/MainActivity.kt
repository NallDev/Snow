package com.nalldev.snow.presentation

import android.Manifest
import android.os.Bundle
import android.util.Log
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.nalldev.snow.presentation.theme.SnowTheme
import com.nalldev.snow.presentation.utils.Notification

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
    val context = LocalContext.current

    val notificationPermissionState = rememberPermissionState(
        Manifest.permission.POST_NOTIFICATIONS
    )
    SnowTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            contentAlignment = Alignment.Center
        ) {
            if (notificationPermissionState.status.isGranted) {
                Button(modifier = Modifier.fillMaxWidth(), onClick = {
                    Notification.sendNotification(
                        context = context,
                        title = "Notifikasi Baru",
                        message = "Ini adalah contoh notifikasi"
                    )
                    Log.e("HELLO", "BERHASIL HARUSNYA")
                }) {
                    Text("Kirim Notifikasi")
                }
            } else {
                Button(modifier = Modifier.fillMaxWidth(), onClick = {
                    notificationPermissionState.launchPermissionRequest()
                }) {
                    Text("Izinkan Notifikasi")
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Izin diperlukan untuk menampilkan notifikasi."
                )
            }
        }
    }
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp()
}