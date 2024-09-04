package com.semnan.semnanuniversity.screens

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState


@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewScreen(navController: NavController, url: String?) {

    val state = rememberWebViewState(url.toString())
    var webView by remember { mutableStateOf<WebView?>(null) }

    WebView(
        state = state,
        modifier = Modifier.fillMaxSize(),
        onCreated = {
            webView = it // Capture the WebView instance
            it.settings.javaScriptEnabled = true
            it.webChromeClient = WebChromeClient()
            it.webViewClient = WebViewClient()
        }
    )

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            webView?.destroy()
        }
    }

}