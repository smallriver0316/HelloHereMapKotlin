package com.example.helloheremapkotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.here.sdk.core.engine.AuthenticationMode
import com.here.sdk.core.engine.SDKNativeEngine
import com.here.sdk.core.engine.SDKOptions
import com.here.sdk.core.errors.InstantiationErrorException
import com.here.sdk.mapview.MapScheme
import com.here.sdk.mapview.MapView
import com.example.helloheremapkotlin.ui.theme.HelloHereMapKotlinTheme

class MainActivity : ComponentActivity() {

    private var mapView: MapView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initHereSDK()
        enableEdgeToEdge()
        setContent {
            HelloHereMapKotlinTheme {
                HereMapView(savedInstanceState)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
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

    private fun setupMapView(savedInstanceState: Bundle?, mapView: MapView) {
        this.mapView = mapView
        mapView.onCreate(savedInstanceState)
        mapView.onResume()
        if (savedInstanceState == null) {
           mapView.mapScene.loadScene(MapScheme.NORMAL_DAY, null)
        }
    }

    @Composable
    fun HereMapView(savedInstanceState: Bundle?) {
        AndroidView(factory = { context ->
            MapView(context).apply {
                setupMapView(savedInstanceState, this)
            }
        })
    }
}
