package com.michona.fitify.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.michona.fitify.R
import com.michona.fitify.ui.theme.FitifyTheme

class FitifyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitifyTheme {
                FitifyNavGraph()
            }
        }
        window.decorView.setBackgroundColor(applicationContext.getColor(R.color.blue))
    }
}
