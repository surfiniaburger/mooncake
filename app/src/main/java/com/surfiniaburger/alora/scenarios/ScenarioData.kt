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

val scenarios =
    listOf(
        createScenario(
            name = "race_strategy",
            titleId = R.string.scenarios_race_strategy,
            initialState = "mode=satellite;camera=lat=33.5325,lng=-86.6189,alt=1000,hdg=0,tilt=45,range=2000",
            animationString = "waitUntilTheMapIsSteady;delay=dur=1000",
        )
    ).associateBy { it.name }
