# Alora: Race Strategy Companion (Android)

**Alora** is the Android client for the Alora Racing Strategy system. It visualizes real-time race strategy predictions for Barber Motorsports Park, helping users understand optimal pit windows and race dynamics.

## Key Features

*   **Real-Time Strategy Visualization**: Displays 1-stop, 2-stop, and 3-stop strategies on a dynamic Heads-Up Display (HUD).
*   **3D Track Map**: Features an interactive 3D map of Barber Motorsports Park (using Sceneform/OpenGL) that visualizes car positions and strategy overlays.
*   **Live Server Connection**: Connects to the Alora MCP Backend via **Server-Sent Events (SSE)** for low-latency updates.
*   **Parallel Simulation**: Triggers concurrent simulation requests to the backend to ensure strategy data is available within ~2 minutes.
*   **Smart Animations**: Camera transitions and HUD animations are synchronized with server data arrival.

## Technical Stack

*   **Language**: Kotlin
*   **Architecture**: MVVM (Model-View-ViewModel)
*   **Networking**: OkHttp (with SSE support)
*   **Build System**: Gradle (Kotlin DSL)
*   **CI/CD**: GitHub Actions (Automated Release Builds)

## CI/CD Pipeline

This repository uses **GitHub Actions** to automate the release process.
*   **Trigger**: Pushes to the `fix-recommendations` branch.
*   **Process**:
    1.  Decodes the secure keystore from GitHub Secrets.
    2.  Builds the signed Release APK (`./gradlew assembleRelease`).
    3.  Uploads the APK as a build artifact.

## Setup & Build

1.  **Prerequisites**: Android Studio Ladybug or newer, JDK 17.
2.  **Environment Variables**:
    *   `MAPS_API_KEY`: Google Maps SDK API Key.
    *   `ALORA_KEYSTORE_PASSWORD`: Keystore password.
    *   `ALORA_KEY_PASSWORD`: Key alias password.
3.  **Build**:
    ```bash
    ./gradlew assembleDebug
    ```

## Backend

This app communicates with the backend hosted in the `vigilant-journey` repository. Ensure the backend is deployed and the `BASE_URL` in `RaceStrategyRepository.kt` points to the correct Cloud Run instance.
