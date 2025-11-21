# Alora: Race Strategy AI

## Overview

Alora is an Android application that demonstrates a real-time race strategy simulation using a 3D map interface. The app connects to a backend service that runs a Monte Carlo simulation to calculate the optimal pit stop window for a race at Barber Motorsports Park. The results are then displayed on a heads-up display (HUD) overlaid on the 3D map.

This project showcases a modern Android architecture, integration with a custom backend using Server-Sent Events (SSE), and advanced map visualization with the Google Maps 3D API.

## Features

- **3D Map Visualization:** Renders a detailed 3D view of Barber Motorsports Park using the Google Maps 3D API.
- **Camera Animation:** Automatically flies the camera to the track and begins an orbiting animation upon launch.
- **Real-time Simulation:** Connects to a backend service to trigger and receive results from a Monte Carlo race simulation.
- **Heads-Up Display (HUD):** A custom Jetpack Compose UI (`RaceHud`) overlays the map to display simulation status and results from the "RACE ENGINEER AI".
- **Asynchronous Communication:** Uses Server-Sent Events (SSE) via OkHttp to maintain a persistent connection with the backend for receiving real-time updates.

## Tech Stack & Architecture

- **Language:** 100% Kotlin
- **UI:** Jetpack Compose
- **Architecture:** Follows a standard MVVM (Model-View-ViewModel) pattern.
  - **ViewModel:** `ScenariosViewModel` manages UI state and business logic.
  - **Repository:** `RaceStrategyRepository` handles communication with the backend service.
- **Dependency Injection:** Hilt is used for managing dependencies throughout the app.
- **Maps:** Google Maps 3D API for Android.
- **Networking:** OkHttp, specifically the `okhttp-sse` library for handling Server-Sent Events.

## Backend Integration

The application communicates with a backend service hosted on Hugging Face Spaces, which exposes a tool-using agent over the **Model Context Protocol (MCP)**.

- **URL:** `https://surfiniaburger-monte-carlo-sim.hf.space`
- **Protocol:** Server-Sent Events (SSE)
- **Tool:** The app calls the `find_optimal_pit_window` tool to run the simulation.

## Setup & Configuration

### Prerequisites

- Android Studio (latest stable version recommended)
- JDK 11 or higher
- A Google Cloud Platform project with the Maps SDK for Android enabled.

### Configuration

1.  **Get a Google Maps API Key:**
    - Go to the [Google Cloud Console](https://console.cloud.google.com/google/maps-apis/overview).
    - Select your project and enable the **Maps SDK for Android**.
    - Go to the **Credentials** page and copy your API key.

2.  **Add Your API Key:**
    - In the root of the project, create a file named `secrets.properties`.
    - Add your API key to this file like so:
      ```properties
      MAPS_API_KEY="YOUR_API_KEY_HERE"
      ```
    - The project is configured with the [Secrets Gradle Plugin for Android](https://github.com/google/secrets-gradle-plugin) to securely load this key at build time.

## Building and Running

### Android Studio (Recommended)

1.  Open the project in Android Studio.
2.  Let Gradle sync and download all dependencies.
3.  Select an emulator or connect a physical device with USB debugging enabled.
4.  Click the "Run 'app'" button (the green play icon) in the toolbar.

### Command Line

1.  Ensure you have an emulator running or a device connected.
2.  From the root directory of the project, run the following command:
    ```bash
    ./gradlew installDebug
    ```
3.  After the installation is complete, open the "Alora" app on your device.

## Code Overview

-   `MainActivity.kt`: The main entry point of the application. It has been refactored to directly launch the `Race Strategy` feature.
-   `scenarios/ScenariosViewModel.kt`: The core ViewModel that manages the camera, triggers the simulation, and holds the state for the UI.
-   `data/RaceStrategyRepository.kt`: Handles all network communication with the backend, including establishing the SSE connection and calling the simulation tool.
-   `ui/RaceHud.kt`: A self-contained Jetpack Compose function that renders the "RACE ENGINEER AI" heads-up display.
-   `server.py` & `gcs_utils.py`: Python scripts related to the backend server logic, including authentication and downloading assets from Google Cloud Storage.
