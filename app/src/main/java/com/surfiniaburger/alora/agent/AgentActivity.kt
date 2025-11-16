package com.surfiniaburger.alora.agent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.surfiniaburger.alora.ui.theme.AloraTheme

class AgentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AloraTheme {
                AgentScreen()
            }
        }
    }
}
