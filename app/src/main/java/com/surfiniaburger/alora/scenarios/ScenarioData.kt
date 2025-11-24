// Copyright 2025 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.surfiniaburger.alora.scenarios

import com.surfiniaburger.alora.R
import com.google.android.gms.maps3d.model.latLngAltitude


val scenarios =
    listOf(
        // Google's exact mountain trail example from documentation
        createScenario(
            name = "mountain_trail",
            titleId = R.string.scenarios_polyline,
            initialState = "mode=hybrid;camera=lat=40.029349,lng=-105.300354,alt=1833.9,hdg=326,tilt=75,range=3757",
            animationString = "waitUntilTheMapIsSteady;delay=dur=1000",
            // Google's exact trail coordinates from documentation
            polylines = """
                40.0201040,-105.2976640
                40.0201080,-105.2976450
                40.0201640,-105.2975120
                40.0202200,-105.2973740
                40.0202500,-105.2972760
                40.0202960,-105.2971410
                40.0203080,-105.2970990
                40.0203320,-105.2970070
                40.0203640,-105.2969400
                40.0203710,-105.2969250
                40.0203770,-105.2969220
                40.0203910,-105.2969130
                40.0203940,-105.2969120
                40.0204200,-105.2969130
                40.0204630,-105.2968910
                40.0205270,-105.2968280
                40.0206030,-105.2967570
                40.0206590,-105.2966100
                40.0206990,-105.2964870
            """.trimIndent()
        ),
        // Simple test polyline - straight line in Chicago to verify polylines work
        createScenario(
            name = "polyline_test",
            titleId = R.string.scenarios_polyline,
            initialState = "mode=satellite;camera=lat=41.8781,lng=-87.6298,alt=500,hdg=0,tilt=60,range=1000",
            animationString = "waitUntilTheMapIsSteady;delay=dur=1000",
            // Simple straight line polyline in Chicago (encoded)
            // This is a short, simple line that should definitely be visible
            polylines = "_p~iF~ps|U_ulLnnqC_mqNvxq`@"
        ),
        createScenario(
            name = "race_strategy",
            titleId = R.string.scenarios_race_strategy,
            // Barber Motorsports Park
            initialState = "mode=satellite;camera=lat=33.5325,lng=-86.6189,alt=800,hdg=0,tilt=60,range=1500",
            animationString = "waitUntilTheMapIsSteady;delay=dur=1000",
            // Test Square around the track (Confirmed working)
            polylines = """
                33.5300, -86.6210
                33.5350, -86.6210
                33.5350, -86.6160
                33.5300, -86.6160
                33.5300, -86.6210
            """.trimIndent(),

        )
    ).associateBy { it.name }

