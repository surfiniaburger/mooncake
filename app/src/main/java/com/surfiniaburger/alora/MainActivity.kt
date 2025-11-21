/*
 * Copyright (c) 2025 Alora
 *
 * Licensed under the MIT License. See LICENSE for details.
 */

package com.surfiniaburger.alora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.surfiniaburger.alora.scenarios.ScenarioScreen
import com.surfiniaburger.alora.scenarios.ScenariosViewModel
import com.surfiniaburger.alora.ui.RaceHud
import com.surfiniaburger.alora.ui.theme.AloraTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: ScenariosViewModel = hiltViewModel()
            val viewState by viewModel.viewState.collectAsStateWithLifecycle()
            val strategyResult by viewModel.strategyResult.collectAsStateWithLifecycle()

            // Set the scenario to "race_strategy" when the app starts
            LaunchedEffect(Unit) {
                viewModel.setScenario("race_strategy")
            }

            AloraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
                        viewState.scenario?.let { scenario ->
                            ScenarioScreen(
                                modifier = Modifier.fillMaxSize(),
                                scenario = scenario,
                                viewModel = viewModel,
                            )
                            RaceHud(
                                strategyText = strategyResult,
                                onRunSimulation = {
                                    viewModel.flyToBarber()
                                    viewModel.runSimulation()
                                },
                                modifier = Modifier.align(Alignment.TopStart)
                            )
                        }
                    }
                }
            }
        }
    }
}