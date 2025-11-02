package com.example.helloheremapkotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.here.sdk.core.engine.AuthenticationMode
import com.here.sdk.core.engine.SDKNativeEngine
import com.here.sdk.core.engine.SDKOptions
import com.example.helloheremapkotlin.ui.theme.HelloHereMapKotlinTheme
import com.here.sdk.core.errors.InstantiationErrorException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initHereSDK()
        enableEdgeToEdge()
        setContent {
            HelloHereMapKotlinTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposeHereSDK()
    }

    private fun initHereSDK() {
        val accessKeyId = BuildConfig.HERE_ACCESS_KEY_ID
        val accessKeySecret = BuildConfig.HERE_ACCESS_KEY_SECRET
        val authenticationMode = AuthenticationMode.withKeySecret(accessKeyId, accessKeySecret)
        val options = SDKOptions(authenticationMode)
        try {
            SDKNativeEngine.makeSharedInstance(this, options)
        } catch (e: InstantiationErrorException) {
            throw RuntimeException("Initialization of HERE SDK failed:" + e.error.name)
        }
    }

    private fun disposeHereSDK() {
        SDKNativeEngine.getSharedInstance()?.dispose()
        SDKNativeEngine.setSharedInstance(null)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HelloHereMapKotlinTheme {
        Greeting("Android")
    }
}