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
import com.surfiniaburger.alora.common.ErrorType
import com.surfiniaburger.alora.common.ResultState
import com.surfiniaburger.alora.scenarios.ScenarioScreen
import com.surfiniaburger.alora.scenarios.ScenariosViewModel
import com.surfiniaburger.alora.ui.ErrorScreen
import com.surfiniaburger.alora.ui.LoadingScreen
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
            val isOnline by viewModel.isOnline.collectAsStateWithLifecycle(initialValue = true)

            // Set the scenario to "race_strategy" when the app starts
            LaunchedEffect(Unit) {
                viewModel.setScenario("race_strategy")
            }

            AloraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
                        // Determine what to show based on state
                        when {
                            // Show error screen if offline
                            !isOnline -> {
                                ErrorScreen(
                                    errorType = ErrorType.NETWORK_ERROR,
                                    message = "No internet connection",
                                    onRetry = null // Network will auto-reconnect
                                )
                            }
                            // Show error screen if map failed to load
                            viewState.mapError != null -> {
                                ErrorScreen(
                                    errorType = ErrorType.GENERIC,
                                    message = viewState.mapError,
                                    onRetry = { viewModel.setScenario("race_strategy") }
                                )
                            }
                            // Show loading screen while map is loading
                            !viewState.isMapLoaded -> {
                                LoadingScreen()
                            }
                            // Show normal content when everything is ready
                            else -> {
                                viewState.scenario?.let { scenario ->
                                    ScenarioScreen(
                                        modifier = Modifier.fillMaxSize(),
                                        scenario = scenario,
                                        viewModel = viewModel,
                                    )
                                    RaceHud(
                                        strategyState = strategyResult,
                                        onRunSimulation = {
                                            viewModel.flyToBarber()
                                            viewModel.runSimulation()
                                        },
                                        modifier = Modifier.align(Alignment.TopStart)
                                    )
                                }

                                // Show error overlay for SSE connection errors
                                if (strategyResult is ResultState.Error) {
                                    val error = strategyResult as ResultState.Error
                                    ErrorScreen(
                                        errorType = error.type,
                                        message = error.message,
                                        onRetry = { viewModel.retryConnection() }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}